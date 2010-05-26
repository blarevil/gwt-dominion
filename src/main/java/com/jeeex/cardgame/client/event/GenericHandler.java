package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * A generic event handler for an arbitrary change.
 * <p>
 * TODO(Jeeyoung Kim) Create another handler interface with 2 parameters,
 * before and after.
 */
public interface GenericHandler<E> extends EventHandler {
	public void onEvent(E event);
}