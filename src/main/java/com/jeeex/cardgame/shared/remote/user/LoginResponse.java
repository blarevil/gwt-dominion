package com.jeeex.cardgame.shared.remote.user;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.annotation.GwtConstructor;

public class LoginResponse implements IsSerializable {

	boolean successful;

	String authToken;

	@SuppressWarnings("unused")
	@GwtConstructor
	private LoginResponse() {
	}

	public LoginResponse(boolean successful, String authToken) {
		this.successful = successful;
		this.authToken = authToken;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public String getAuthToken() {
		return authToken;
	}
}
