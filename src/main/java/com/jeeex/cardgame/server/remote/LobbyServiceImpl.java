package com.jeeex.cardgame.server.remote;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.shared.entity.GameRoom;
import com.jeeex.cardgame.shared.remote.lobby.CreateGameRequest;
import com.jeeex.cardgame.shared.remote.lobby.CreateGameResponse;
import com.jeeex.cardgame.shared.remote.lobby.GetGameListRequest;
import com.jeeex.cardgame.shared.remote.lobby.GetGameListResponse;
import com.jeeex.cardgame.shared.remote.lobby.LobbyService;

@RequestScoped
public class LobbyServiceImpl implements LobbyService {
	@Inject
	EntityManager em;

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

	@Override
	public CreateGameResponse createGame(CreateGameRequest request) {
		em.getTransaction().begin();
		GameRoom gr = new GameRoom();
		gr.setName(request.getName());
		em.persist(gr);
		em.getTransaction().commit();
		return new CreateGameResponse();
	}
}
