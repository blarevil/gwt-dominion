package com.jeeex.cardgame.shared.remote.message;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.annotation.GwtConstructor;
import com.jeeex.cardgame.shared.remote.entity.Message;

public class WaitForMessageResponse implements IsSerializable {

	@GwtConstructor
	@SuppressWarnings("unused")
	private WaitForMessageResponse() {
	}

	public WaitForMessageResponse(ArrayList<Message> msgs) {
		this.msgs = msgs;
	}

	ArrayList<Message> msgs;

	@SuppressWarnings("unchecked")
	public <T extends Message> T getMessage(Class<T> type) {
		for (Message msg : msgs) {
			if (msg.getClass().getName().equals(type.getName()))
				return (T) msg;
		}
		return null;
	}

	public ArrayList<Message> getMessages(){
		return msgs;
	}
}
