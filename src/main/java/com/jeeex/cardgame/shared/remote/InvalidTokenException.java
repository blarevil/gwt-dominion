package com.jeeex.cardgame.shared.remote;

import com.google.gwt.user.client.rpc.IsSerializable;

public class InvalidTokenException extends Exception implements
		IsSerializable {
	private static final long serialVersionUID = 1;

	public InvalidTokenException() {
	}
}
