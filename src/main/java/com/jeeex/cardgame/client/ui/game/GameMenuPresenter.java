package com.jeeex.cardgame.client.ui.game;

import static com.jeeex.cardgame.client.data.model.UserState.IN_GAME;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.data.model.Binded;
import com.jeeex.cardgame.client.data.model.UserState;
import com.jeeex.cardgame.client.ui.WidgetTool;
import com.jeeex.cardgame.client.ui.generic.Presenter;

public class GameMenuPresenter implements Presenter<GameMenuView> {
	
	@Inject
	private WidgetTool wt;

	@Inject
	private GameMenuView view;

	@Inject
	private Binded<UserState> userState;

	@Override
	public GameMenuView getView() {
		return view;
	}

	@Override
	public void init() {
		wt.showMenuOnState(IN_GAME, this);
		view.getLeaveGameButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				userState.set(UserState.IN_LOBBY);
			}
		});
	}
}
