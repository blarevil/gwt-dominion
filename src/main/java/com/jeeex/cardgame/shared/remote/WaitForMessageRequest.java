package com.jeeex.cardgame.shared.remote;

import com.google.gwt.user.client.rpc.IsSerializable;

public class WaitForMessageRequest implements IsSerializable {

	long prevcounter;

	String queueId;

	@SuppressWarnings("unused")
	private WaitForMessageRequest() {
	}

	public WaitForMessageRequest(long prevcounter) {
		this.prevcounter = prevcounter;
	}

}
