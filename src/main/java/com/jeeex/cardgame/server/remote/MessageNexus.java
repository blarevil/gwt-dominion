package com.jeeex.cardgame.server.remote;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import net.jcip.annotations.GuardedBy;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.jeeex.cardgame.server.logic.MessageSource;
import com.jeeex.cardgame.shared.entity.Message;

@Singleton
public class MessageNexus {

	private final Provider<MessageSource> msgSrcp;

	private final Provider<EntityManager> emp;

	@GuardedBy("this")
	private volatile long currentCounter = 0;

	private boolean currentCounterSet = false;

	@Inject
	private Logger logger;

	@Inject
	public MessageNexus(Provider<MessageSource> msgSource,
			Provider<EntityManager> em) {
		msgSrcp = msgSource;
		this.emp = em;
	}

	public synchronized void updateCounter(long newCounter) {
		if (!currentCounterSet) {
			currentCounter = newCounter;
			currentCounterSet = true;
		} else {
			checkArgument(newCounter > currentCounter);
			currentCounter = newCounter;
		}
		notifyAll();
	}

	public synchronized Message waitForNewMessage(long nextCounter) {
		if (!currentCounterSet) {
			initCounter();
		}
		while (currentCounter < nextCounter) {
			try {
				wait();
			} catch (InterruptedException e) {
				// don't handle interrupt here.
				Thread.interrupted();
			}
		}
		return msgSrcp.get().getMessage(nextCounter);
	}

	/**
	 * Set the internal counter of MessageNexus. TODO(Jeeyoung Kim) refactor
	 * this to a provider.
	 * */
	private void initCounter() {
		EntityManager e = emp.get();
		Query q = e
				.createQuery("SELECT msg from Message msg ORDER BY msg.id DESC");
		q.setMaxResults(1);
		try {
			Message msg = (Message) q.getSingleResult();
			logger.info("Setting counter: " + msg.getId());
			currentCounter = msg.getId();
			currentCounterSet = true;
		} catch (NoResultException exception) {
			// no result. use 0.
			logger.info("Setting counter: " + 0);
			currentCounter = 0;
			currentCounterSet = true;
		}
	}
}
