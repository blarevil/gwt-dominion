package com.jeeex.cardgame.shared.remote.user;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.annotation.GwtConstructor;

public class LogoutRequest implements IsSerializable {
	String authToken;

	@GwtConstructor
	@SuppressWarnings("unused")
	private LogoutRequest() {
	}

	public LogoutRequest(String authToken) {
		this.authToken = authToken;
	}

	public String getAuthToken() {
		return authToken;
	}
}
