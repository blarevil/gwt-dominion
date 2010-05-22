package com.jeeex.cardgame.shared.remote.user;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.annotation.GwtConstructor;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.remote.Authenticated;

public class LoginResponse implements IsSerializable, Authenticated {

	boolean successful;

	AuthToken tkn;

	@SuppressWarnings("unused")
	@GwtConstructor
	private LoginResponse() {
	}

	public LoginResponse(boolean successful, AuthToken token) {
		this.successful = successful;
		tkn = token;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public AuthToken getAuthToken() {
		return tkn;
	}

	@Override
	public void setAuthToken(AuthToken token) {
		this.tkn = token;
	}
}
