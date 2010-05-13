package com.jeeex.cardgame.client.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.event.shared.HandlerManager;

public class DefaultListModel<E> implements ListModel<E> {

	private List<E> list = new ArrayList<E>();

	private HandlerManager mgr = new HandlerManager(this);

	@Override
	public void add(E e) {
		int index = list.size();
		list.add(e);
		mgr.fireEvent(new ListEvent(ListEvent.Type.ADD, index));
	}

	@Override
	public List<E> readOnlyView() {
		return Collections.unmodifiableList(list);
	}

	@Override
	public void remove(int index) {
		list.remove(index);
		mgr.fireEvent(new ListEvent(ListEvent.Type.REMOVE, index));
	}

	@Override
	public HandlerEndpoint<ListHandler> handlers() {
		return new HandlerEndpoint<ListHandler>(mgr, ListEvent.TYPE);
	}
}
