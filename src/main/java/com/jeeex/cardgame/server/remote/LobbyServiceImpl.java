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
		resp.setRooms(getList());
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

	@UnderTransaction
	public User getUser(AuthToken token) {
		return em.getReference(User.class, token.getUserId());
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
			gr.setCreatedBy(getUser(tkn));
			em.persist(gr);
		} finally {
			em.getTransaction().commit();
		}
		return new CreateGameResponse();
	}
}
