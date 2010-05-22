package com.jeeex.cardgame.shared.remote.lobby;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jeeex.cardgame.shared.remote.InvalidTokenException;

@RemoteServiceRelativePath("gwt.rpc")
public interface LobbyService extends RemoteService {
	public GetGameListResponse getGameList(GetGameListRequest request);

	/**
	 * @throws InvalidTokenException
	 *             If the provided authtoken is invalid.
	 */
	public CreateGameResponse createGame(CreateGameRequest request)
			throws InvalidTokenException;

	// join game!
	public JoinGameResponse joinGame(JoinGameRequest request)
			throws InvalidTokenException;
}
