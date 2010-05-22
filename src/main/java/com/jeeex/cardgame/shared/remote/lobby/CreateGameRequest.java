package com.jeeex.cardgame.shared.remote.lobby;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.remote.Authenticated;

public class CreateGameRequest implements IsSerializable, Authenticated {

	private String name;

	private AuthToken tkn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public AuthToken getAuthToken() {
		return tkn;
	}

	@Override
	public void setAuthToken(AuthToken token) {
		tkn = token;
	}
}
