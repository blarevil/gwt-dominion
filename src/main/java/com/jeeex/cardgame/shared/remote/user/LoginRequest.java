package com.jeeex.cardgame.shared.remote.user;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.annotation.GwtConstructor;

public class LoginRequest implements IsSerializable {

	private String username;

	@GwtConstructor
	@SuppressWarnings("unused")
	private LoginRequest() {
	}

	public LoginRequest(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public String toString() {
		return "LoginRequest:" + username;
	}
}
