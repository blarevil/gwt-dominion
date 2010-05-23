package com.jeeex.cardgame.server.remote;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.server.logic.AuthenticationChecker;
import com.jeeex.cardgame.shared.entity.ChatMessage;
import com.jeeex.cardgame.shared.entity.Message;
import com.jeeex.cardgame.shared.remote.InvalidTokenException;
import com.jeeex.cardgame.shared.remote.message.MessageService;
import com.jeeex.cardgame.shared.remote.message.SendChatMessageRequest;
import com.jeeex.cardgame.shared.remote.message.SendChatMessageResponse;
import com.jeeex.cardgame.shared.remote.message.SendMessageRequest;
import com.jeeex.cardgame.shared.remote.message.SendMessageResponse;
import com.jeeex.cardgame.shared.remote.message.WaitForMessageRequest;
import com.jeeex.cardgame.shared.remote.message.WaitForMessageResponse;

@RequestScoped
public class MessageServiceImpl implements MessageService {

	@Inject
	EntityManager em;

	@Inject
	MessageNexus mn;

	@Inject
	AuthenticationChecker authCheck;

	@Override
	public SendMessageResponse sendMessage(SendMessageRequest request) {
		Message msg = request.getMessage();
		checkArgument(msg.getId() == null,
				"Clients should never send Messages with non-null ids.");
		em.getTransaction().begin();
		em.persist(msg);
		em.getTransaction().commit();
		mn.updateCounter(msg.getId());
		return new SendMessageResponse();
	}

	@Override
	public WaitForMessageResponse waitForMessage(WaitForMessageRequest request) {
		em.getTransaction().begin();
		Message msg = mn.waitForNewMessage(request.getNextMessage());
		WaitForMessageResponse response = new WaitForMessageResponse(
				newArrayList(msg));
		em.getTransaction().commit();
		return response;
	}

	@Override
	public SendChatMessageResponse sendChatMessage(
			SendChatMessageRequest request) throws InvalidTokenException {
		em.getTransaction().begin();
		long id = 0;
		try {
			authCheck.isValid(request.getAuthToken());
			ChatMessage cm = new ChatMessage();
			cm.setMessage(request.getMessage());
			em.persist(cm);
			id = cm.getId();
		} finally {
			em.getTransaction().commit();
		}
		// this method has to be called AFTER commit...
		// is there some better way to manage this workflow?
		mn.updateCounter(id);
		return new SendChatMessageResponse();
	}
}
