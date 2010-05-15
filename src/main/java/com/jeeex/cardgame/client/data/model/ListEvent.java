package com.jeeex.cardgame.client.data.model;

import com.google.gwt.event.shared.GwtEvent;

public class ListEvent extends GwtEvent<ListHandler> {

	enum Type {
		ADD, REMOVE
	}

	public static final GwtEvent.Type<ListHandler> TYPE = new GwtEvent.Type<ListHandler>();

	private final Type eventType;

	private final int index;

	public ListEvent(Type eventType, int index) {
		this.eventType = eventType;
		this.index = index;
	}

	@Override
	protected void dispatch(ListHandler handler) {
		handler.onChange(this);
	}

	@Override
	public GwtEvent.Type<ListHandler> getAssociatedType() {
		return TYPE;
	}

	public Type getEventType() {
		return eventType;
	}

	public int getIndex() {
		return index;
	}
}
