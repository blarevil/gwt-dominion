package com.jeeex.cardgame.server.logic;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.shared.entity.Message;

@RequestScoped
public class MessageSource {

	@Inject
	Logger logger;

	@Inject
	EntityManager em;

	public Message getMessage(long counter) {
		Query q = em.createQuery("SELECT msg FROM Message msg WHERE msg.id = :counter");
		q.setParameter("counter", counter);
		return (Message) q.getSingleResult();
	}
}
