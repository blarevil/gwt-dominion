package com.jeeex.cardgame.client.ui.widget;

import com.jeeex.cardgame.client.ui.generic.Presenter;

// TODO - refactor from GameListView.
public class GameListPresenter implements Presenter<GameListView> {

	private GameListView view;

	@Override
	public GameListView getView() {
		return view;
	}

	@Override
	public void init() {
		// do something...
	}

}
