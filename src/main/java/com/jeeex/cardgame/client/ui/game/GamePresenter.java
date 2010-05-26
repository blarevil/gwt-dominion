package com.jeeex.cardgame.client.ui.game;

import static com.jeeex.cardgame.client.data.model.UserState.IN_GAME;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jeeex.cardgame.client.data.model.Binded;
import com.jeeex.cardgame.client.data.model.UserState;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.WidgetTool;
import com.jeeex.cardgame.client.ui.generic.Presenter;

@Singleton
public class GamePresenter implements Presenter<GameView> {

	@Inject
	private GameView view;

	@Inject
	private MyEventBus ebus;

	@Inject
	private Binded<UserState> userState;
	
	@Inject
	private WidgetTool wt;

	@Inject
	public GamePresenter() {
	}

	@Override
	public GameView getView() {
		return view;
	}

	@Override
	public void init() {
		wt.showCenterWidgetOnState(IN_GAME, this);

		userState.addHandler(new GenericHandler<UserState>() {
			@Override
			public void onEvent(UserState event) {
				if (event == UserState.IN_GAME) {
					ebus.setCenterWidget(getView());
				}
			}
		});
	}
}
