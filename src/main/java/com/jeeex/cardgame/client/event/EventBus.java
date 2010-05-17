package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.GwtEvent.Type;

public interface EventBus {
	public <E> void fire(Type<BusHandler<E>> type, E e);
}
