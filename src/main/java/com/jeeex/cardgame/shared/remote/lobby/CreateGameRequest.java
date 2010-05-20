package com.jeeex.cardgame.shared.remote.lobby;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CreateGameRequest implements IsSerializable {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
