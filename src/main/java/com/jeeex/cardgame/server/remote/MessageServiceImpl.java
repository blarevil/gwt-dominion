package com.jeeex.cardgame.server.remote;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.jeeex.cardgame.shared.remote.MessageService;
import com.jeeex.cardgame.shared.remote.SendMessageRequest;
import com.jeeex.cardgame.shared.remote.SendMessageResponse;
import com.jeeex.cardgame.shared.remote.WaitForMessageRequest;
import com.jeeex.cardgame.shared.remote.WaitForMessageResponse;

public class MessageServiceImpl implements MessageService {
	@Override
	public SendMessageResponse sendMessage(SendMessageRequest request) {
		return new SendMessageResponse();
	}

	@Override
	public WaitForMessageResponse waitForMessage(WaitForMessageRequest request) {
		return new WaitForMessageResponse();
	}
}
