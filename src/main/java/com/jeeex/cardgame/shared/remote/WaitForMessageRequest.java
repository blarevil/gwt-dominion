package com.jeeex.cardgame.shared.remote;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.annotation.GwtConstructor;

public class WaitForMessageRequest implements IsSerializable {

	/** Id of the next message. */
	private long nextMessage;

	@SuppressWarnings("unused")
	@GwtConstructor
	private WaitForMessageRequest() {
	}

	public WaitForMessageRequest(long prevcounter) {
		this.nextMessage = prevcounter;
	}

	public long getNextMessage() {
		return nextMessage;
	}
}
