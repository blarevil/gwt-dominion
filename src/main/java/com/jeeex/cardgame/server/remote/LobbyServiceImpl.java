package com.jeeex.cardgame.server.remote;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.server.aop.UnderTransaction;
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

	@Override
	public GetGameListResponse getGameList(GetGameListRequest request) {
		GetGameListResponse resp = new GetGameListResponse();
		List<GameRoom> list = getList();
		for (GameRoom gr : list) {
			gr.sanitizeForGwt();
		}
		resp.setRooms(list);
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
