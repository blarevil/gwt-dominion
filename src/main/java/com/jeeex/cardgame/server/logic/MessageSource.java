package com.jeeex.cardgame.server.logic;

import java.util.Map;

import com.jeeex.cardgame.shared.remote.msg.Message;

public class MessageSource {
	Map<Long, Message> map;

	public Message getMessage(long counter) {
		return map.get(counter);
	}
}
