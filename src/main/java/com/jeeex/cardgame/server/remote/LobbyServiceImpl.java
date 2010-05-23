package com.jeeex.cardgame.server.remote;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.hibernate.jpa.HibernateJpaUtil;
import net.sf.gilead.core.store.stateless.StatelessProxyStore;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.server.logic.AuthenticationChecker;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.entity.GameRoom;
import com.jeeex.cardgame.shared.entity.User;
import com.jeeex.cardgame.shared.remote.InvalidTokenException;
import com.jeeex.cardgame.shared.remote.lobby.CreateGameRequest;
import com.jeeex.cardgame.shared.remote.lobby.CreateGameResponse;
import com.jeeex.cardgame.shared.remote.lobby.GetGameListRequest;
import com.jeeex.cardgame.shared.remote.lobby.GetGameListResponse;
import com.jeeex.cardgame.shared.remote.lobby.JoinGameRequest;
import com.jeeex.cardgame.shared.remote.lobby.JoinGameResponse;
import com.jeeex.cardgame.shared.remote.lobby.LobbyService;

@RequestScoped
public class LobbyServiceImpl implements LobbyService {
	@Inject
	EntityManager em;

	@Inject
	AuthenticationChecker authCheck;

	@Inject
	private EntityManagerFactory emf;

	@Override
	public GetGameListResponse getGameList(GetGameListRequest request) {
		HibernateJpaUtil util = new HibernateJpaUtil();
		util.setEntityManagerFactory(emf);
		
		PersistentBeanManager pbm = new PersistentBeanManager();
		pbm.setPersistenceUtil(util);
		pbm.setProxyStore(new StatelessProxyStore());

		GetGameListResponse resp = new GetGameListResponse();
		List<GameRoom> list = getList();
		/*
		for (GameRoom gr : list) {		
			gr.sanitize();
		}
		*/
		resp.setRooms((List<GameRoom>) pbm.clone(list));
		return resp;
	}

	/** Get the list of games. */
	@SuppressWarnings("unchecked")
	private List<GameRoom> getList() {
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT gr FROM GameRoom gr");
			return (List<GameRoom>) query.getResultList();
		} finally {
			em.getTransaction().commit();
		}
	}

	@Override
	public CreateGameResponse createGame(CreateGameRequest request)
			throws InvalidTokenException {
		em.getTransaction().begin();
		try {
			AuthToken tkn = request.getAuthToken();
			authCheck.isValid(tkn);
			GameRoom gr = new GameRoom();
			gr.setName(request.getName());
			gr.setCreatedBy(authCheck.getUser(tkn));
			em.persist(gr);
		} finally {
			em.getTransaction().commit();
		}
		return new CreateGameResponse();
	}

	@Override
	public JoinGameResponse joinGame(JoinGameRequest request)
			throws InvalidTokenException {
		em.getTransaction().begin();
		try {
			// get user.
			User user = authCheck.getUser(request.getAuthToken());
			// get gameroom.
			GameRoom gr = em.getReference(GameRoom.class, request.getGameRoom()
					.getId());
			gr.addParticipating(user);
			em.persist(gr);
		} finally {
			em.getTransaction().commit();
		}
		return new JoinGameResponse();
	}
}
