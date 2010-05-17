package com.jeeex.cardgame.shared.remote;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.annotation.GwtConstructor;

public class WaitForMessageRequest implements IsSerializable {

	private long prevcounter;

	private String queueId;

	@SuppressWarnings("unused")
	@GwtConstructor
	private WaitForMessageRequest() {
	}

	public WaitForMessageRequest(long prevcounter) {
		this.prevcounter = prevcounter;
	}

	public long getPrevcounter() {
		return prevcounter;
	}
}
