package com.jeeex.cardgame.server.remote;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.shared.remote.entity.Message;
import com.jeeex.cardgame.shared.remote.message.MessageService;
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
}
