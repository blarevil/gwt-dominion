package com.jeeex.cardgame.shared.remote.lobby;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.remote.Authenticated;

public class LeaveGameRequest implements IsSerializable, Authenticated {

	private AuthToken tkn;

	@Override
	public AuthToken getAuthToken() {
		return tkn;
	}

	@Override
	public void setAuthToken(AuthToken token) {
		tkn = token;
	}
}
