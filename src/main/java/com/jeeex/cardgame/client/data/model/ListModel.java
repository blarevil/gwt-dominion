package com.jeeex.cardgame.client.data.model;

import java.util.List;

public interface ListModel<E> {

	public List<E> readOnlyView();

	public void add(E e);

	public void remove(int index);

	public HandlerEndpoint<ListHandler> handlers();
}
