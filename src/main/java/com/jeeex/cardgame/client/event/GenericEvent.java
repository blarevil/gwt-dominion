/**
 * 
 */
package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class GenericEvent<E> extends GwtEvent<GenericHandler<E>> {

	/** Type of the BusHandler. */
	private Type<GenericHandler<E>> type;
	/** Internal event wrapped by BusEvent. */
	private E e;

	public GenericEvent(Type<GenericHandler<E>> type, E e) {
		this.type = type;
		this.e = e;
	}

	public E get() {
		return e;
	}

	@Override
	protected void dispatch(GenericHandler<E> handler) {
		handler.onEvent(this.get());
	}

	@Override
	public Type<GenericHandler<E>> getAssociatedType() {
		return type;
	}

	public static <T> GenericEvent<?> makeEvent(Type<GenericHandler<T>> type, T t) {
		return new GenericEvent<T>(type, t);
	}
}