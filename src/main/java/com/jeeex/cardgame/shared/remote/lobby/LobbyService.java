package com.jeeex.cardgame.shared.remote.lobby;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwt.rpc")
public interface LobbyService extends RemoteService{ 
	public GetGameListResponse getGameList(GetGameListRequest request);
	
	public CreateGameResponse createGame(CreateGameRequest request);
}
