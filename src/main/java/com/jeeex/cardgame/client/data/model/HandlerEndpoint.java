package com.jeeex.cardgame.client.data.model;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * Wrapper around {@link HandlerManager}, that only reveals add / remove handler
 * methods.
 * 
 * @author Jeeyoung Kim
 * 
 * @param <E>
 *            Type of the event handler managed by this {@link HandlerManager}.
 */
public class HandlerEndpoint<E extends EventHandler> {
	private final HandlerManager mgr;
	private final GwtEvent.Type<E> type;

	public HandlerEndpoint(HandlerManager mgr, Type<E> type) {
		this.mgr = mgr;
		this.type = type;
	}

	public HandlerRegistration addHandler(E handler) {
		return mgr.addHandler(type, handler);
	}

	public void removeHandler(E handler) {
		mgr.removeHandler(type, handler);
	}
}
