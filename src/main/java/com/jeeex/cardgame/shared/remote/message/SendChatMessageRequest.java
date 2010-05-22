package com.jeeex.cardgame.shared.remote.message;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.remote.Authenticated;

public class SendChatMessageRequest implements IsSerializable, Authenticated {

	private String message;
	private AuthToken tkn;

	@Override
	public AuthToken getAuthToken() {
		return tkn;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public void setAuthToken(AuthToken token) {
		this.tkn = token;
	}
}
