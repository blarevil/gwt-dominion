package com.jeeex.cardgame.shared.remote;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.annotation.GwtConstructor;
import com.jeeex.cardgame.shared.remote.entity.Message;

public class SendMessageRequest implements IsSerializable {
	private Message msg;

	@GwtConstructor
	@SuppressWarnings("unused")
	private SendMessageRequest() {
	}

	public SendMessageRequest(Message msg) {
		this.msg = msg;
	}

	public Message getMessage() {
		return msg;
	}
}
