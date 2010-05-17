/**
 * 
 */
package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BusHandler<E> extends EventHandler {
	public void onEvent(E event);
}