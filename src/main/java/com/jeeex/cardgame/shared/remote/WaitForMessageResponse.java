package com.jeeex.cardgame.shared.remote;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.shared.remote.msg.Message;

public class WaitForMessageResponse implements IsSerializable {

	ArrayList<Message> msgs;

	@SuppressWarnings("unchecked")
	public <T extends Message> T getMessage(Class<T> type) {
		for (Message msg : msgs) {
			if (msg.getClass().getName().equals(type.getName()))
				return (T) msg;
		}
		return null;
	}
}
