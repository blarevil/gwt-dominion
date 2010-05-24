package com.jeeex.cardgame.server.remote;

import static com.jeeex.cardgame.server.init.TestUtil.injector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.entity.GameRoom;
import com.jeeex.cardgame.shared.entity.User;
import com.jeeex.cardgame.shared.remote.lobby.JoinGameRequest;
import com.jeeex.cardgame.shared.remote.lobby.LeaveGameRequest;

public class LobbyServiceImplTest {
	@Inject
	EntityManagerFactory emf;

	@Inject
	LobbyServiceImpl lsi;

	// variables for test
	User user;
	GameRoom room;
	AuthToken token;

	@Before
	public void init() {
		injector().injectMembers(this);
	}

	public void setup() {
		EntityManager em = emf.createEntityManager();
		// variables
		user = new User();
		room = new GameRoom();
		token = new AuthToken();

		// setup
		em.getTransaction().begin();

		// user
		user.setUsername("jeeyoung_kim");
		em.persist(user);

		// room
		room.setName("Test game");
		room.setCreatedBy(user);
		em.persist(room);

		// token
		token.setUser(user);
		token.setTokenName("authToken");
		em.persist(token);

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void testJoinRoom() throws Exception {
		setup();

		// test
		JoinGameRequest req = new JoinGameRequest();
		req.setAuthToken(token);
		req.setGameRoom(room);
		lsi.joinGame(req);

		// NEW EM FOR THE CONFIRMATION.
		EntityManager newEm = emf.createEntityManager();

		newEm.getTransaction().begin();
		// check
		{
			GameRoom result = newEm.getReference(GameRoom.class, room.getId());
			assertEquals(1, result.getParticipatingUsers().size());
		}
		{
			User result = newEm.getReference(User.class, user.getId());
			assertEquals(1, result.getJoinedRooms().size());
		}
		newEm.getTransaction().commit();
		newEm.close();
	}

	@Test
	public void testLeaveRoom() throws Exception {
		setup();
		
		// more setup.
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		room = em.merge(room);
		user = em.merge(user);
		em.refresh(room);
		em.refresh(user);
		user.joinGame(room);
		em.persist(user);
		em.getTransaction().commit();
		em.close();

		// join the room
		{
			JoinGameRequest req = new JoinGameRequest();
			req.setAuthToken(token);
			req.setGameRoom(room);
			lsi.joinGame(req);
		}

		LeaveGameRequest req = new LeaveGameRequest();
		req.setAuthToken(token);
		lsi.leaveGame(req);

		// new EM
		em = emf.createEntityManager();
		em.getTransaction().begin();
		GameRoom result = em.getReference(GameRoom.class, room.getId());
		assertTrue(result.getParticipatingUsers().isEmpty());
		em.getTransaction().commit();
		em.close();
	}

	@After
	public void cleanup() {
	}
}
