package com.jeeex.cardgame.client.data.model;

import static com.jeeex.cardgame.client.event.GenericEvent.makeEvent;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.jeeex.cardgame.client.event.GenericHandler;

/**
 * Represents a single variable that can be listened.
 * 
 * @author Jeeyoung Kim
 * @since 2010-05-24
 * @param <T>
 *            Type of the variable managed by {@link Binded}.
 */
public class Binded<T> {
	/** Value stored in this Binded variable. */
	private T value;
	/** Type object to track handler in HandlerManager. */
	private Type<GenericHandler<T>> binded = new Type<GenericHandler<T>>();
	/** Manages all the handlers listening to the change. */
	private HandlerManager mgr = new HandlerManager(this);

	public void set(T value) {
		this.value = value;
		mgr.fireEvent(makeEvent(binded, value));
	}

	public T get() {
		return value;
	}

	public boolean isSet() {
		return value != null;
	}

	/** Add a handler to this.  */
	public void addHandler(GenericHandler<T> handler) {
		mgr.addHandler(binded, handler);
	}
}
