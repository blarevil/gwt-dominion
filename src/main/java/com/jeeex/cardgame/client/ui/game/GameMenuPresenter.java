package com.jeeex.cardgame.client.ui.game;

import com.google.inject.Inject;
import com.jeeex.cardgame.client.ui.generic.Presenter;

public class GameMenuPresenter implements Presenter<GameMenuView> {

	@Inject
	private GameMenuView view;

	@Override
	public GameMenuView getView() {
		return view;
	}

	@Override
	public void init() {
	}
}
