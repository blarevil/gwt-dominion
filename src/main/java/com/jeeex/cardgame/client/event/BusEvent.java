/**
 * 
 */
package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class BusEvent<E> extends GwtEvent<BusHandler<E>> {

	/** Type of the BusHandler. */
	private Type<BusHandler<E>> type;
	/** Internal event wrapped by BusEvent. */
	private E e;

	public BusEvent(Type<BusHandler<E>> type, E e) {
		this.type = type;
		this.e = e;
	}

	public E get() {
		return e;
	}

	@Override
	protected void dispatch(BusHandler<E> handler) {
		handler.onEvent(this.get());
	}

	@Override
	public Type<BusHandler<E>> getAssociatedType() {
		return type;
	}
}