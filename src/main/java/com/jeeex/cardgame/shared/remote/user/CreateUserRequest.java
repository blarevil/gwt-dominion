package com.jeeex.cardgame.shared.remote.user;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.annotation.GwtConstructor;

public class CreateUserRequest implements IsSerializable {
	String newUserName;

	@SuppressWarnings("unused")
	@GwtConstructor
	private CreateUserRequest() {
	}

	public CreateUserRequest(String newUserName) {
		this.newUserName = newUserName;
	}

	public String getNewUserName() {
		return newUserName;
	}
}
