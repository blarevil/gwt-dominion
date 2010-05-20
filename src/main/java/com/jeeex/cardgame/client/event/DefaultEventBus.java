package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.GwtEvent.Type;

public class DefaultEventBus implements EventBus {

	private final HandlerManager mgr;

	public DefaultEventBus(HandlerManager mgr) {
		assert mgr != null;
		this.mgr = mgr;
	}

	@Override
	public <E> void fire(Type<GenericHandler<E>> type, E e) {
		getHandlerManager().fireEvent(new GenericEvent<E>(type, e));
	}

	public HandlerManager getHandlerManager() {
		return mgr;
	}
}
