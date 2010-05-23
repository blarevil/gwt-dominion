package com.jeeex.cardgame.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.generic.Presenter;
import com.jeeex.cardgame.client.ui.widget.GameListPresenter;

@Singleton
public class GamePresenter implements Presenter<GameView> {

	@Inject
	private GameView view;

	@Inject
	private MyEventBus ebus;

	// not injected, to prevent circular dependency.
	private GameListPresenter glPresenter;

	@Inject
	public GamePresenter() {
	}

	@Override
	public GameView getView() {
		return view;
	}

	@Override
	public void init() {
		// preconditions.
		assert glPresenter != null;

		view.getLeaveButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ebus.setCenterWidget(glPresenter.getView());
			}
		});
	}

	public void setGameListPresenter(GameListPresenter glp) {
		glPresenter = glp;
	}
}
