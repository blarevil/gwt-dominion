package com.jeeex.cardgame.shared.remote.lobby;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.entity.GameRoom;
import com.jeeex.cardgame.shared.remote.Authenticated;

public class JoinGameRequest implements IsSerializable, Authenticated{

	GameRoom gameRoom;
	private AuthToken tkn;

	public GameRoom getGameRoom() {
		return gameRoom;
	}

	public void setGameRoom(GameRoom gameRoom) {
		this.gameRoom = gameRoom;
	}

	@Override
	public AuthToken getAuthToken() {
		return tkn;
	}

	@Override
	public void setAuthToken(AuthToken token) {
		this.tkn = token;
	}
}
