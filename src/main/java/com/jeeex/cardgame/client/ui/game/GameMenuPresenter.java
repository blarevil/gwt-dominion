package com.jeeex.cardgame.client.ui.game;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
		view.getLeaveGameButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			}
		});
	}
}
