package com.jeeex.cardgame.client.ui.generic;

public interface Presenter<E> {

	/** Initializes the presenter. */
	public void init();

	/** Returns the view for this presenter. */
	public E getView();
}
