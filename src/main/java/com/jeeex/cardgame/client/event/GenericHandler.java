package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface GenericHandler<E> extends EventHandler {
	public void onEvent(E event);
}