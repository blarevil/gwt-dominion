package com.jeeex.cardgame.shared.remote.user;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.annotation.GwtConstructor;

public class CreateUserResponse implements IsSerializable {
	boolean success;

	@GwtConstructor
	@SuppressWarnings("unused")
	private CreateUserResponse() {
	}

	public CreateUserResponse(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}
}
