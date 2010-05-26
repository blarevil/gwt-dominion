package com.jeeex.cardgame.client;

import static com.jeeex.cardgame.client.data.model.UserState.IN_LOBBY;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.data.model.Binded;
import com.jeeex.cardgame.client.data.model.UserState;
import com.jeeex.cardgame.client.data.msgloop.MessageLoop;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.WidgetTool;
import com.jeeex.cardgame.client.ui.game.GameMenuPresenter;
import com.jeeex.cardgame.client.ui.game.GamePresenter;
import com.jeeex.cardgame.client.ui.lobby.LobbyMenuPresenter;
import com.jeeex.cardgame.client.ui.lobby.LobbyPresenter;
import com.jeeex.cardgame.client.ui.widget.GameListPresenter;
import com.jeeex.cardgame.shared.entity.AuthToken;

/**
 * Delegates all the work done by {@link Application}.<br>
 * This class exists for injection.
 */
public class InjectedApplication implements Runnable {
	@Inject
	MessageLoop loop;

	@Inject
	private MyEventBus ebus;

	@Inject
	private LobbyPresenter lobbyPresenter;

	@Inject
	private LobbyMenuPresenter lobbyMenuPresenter;

	@Inject
	private GameMenuPresenter gameMenuPresenter;

	@Inject
	private GamePresenter gamePresenter;

	@Inject
	private GameListPresenter gameListPresenter;

	@Inject
	private Binded<UserState> userState;

	@Inject
	private Binded<AuthToken> tkn;

	@Inject
	private WidgetTool wt;

	@Override
	public void run() {
		registerDebugWatch();

		// initialize the presenters.
		{
			lobbyPresenter.init();
			gamePresenter.init();
			gameMenuPresenter.init();
			lobbyMenuPresenter.init();
			gameListPresenter.init();
		}
		{
			wt.showCenterWidgetOnState(IN_LOBBY, gameListPresenter);
		}

		RootPanel.get().add((Widget) lobbyPresenter.getView());
		// startMessageLoop();

		// set the states.
		tkn.clear();
		userState.set(IN_LOBBY);
	}

	private void registerDebugWatch() {
		// register handler for debugging.
		userState.addHandler(new GenericHandler<UserState>() {
			@Override
			public void onEvent(UserState state) {
				ebus.println("new userstate:" + state);
			}
		});
		tkn.addHandler(new GenericHandler<AuthToken>() {
			@Override
			public void onEvent(AuthToken token) {
				ebus.println("new token:" + token);
			}
		});
	}

	public void startMessageLoop() {
		// initialize
		loop.setCounter(1);
		Timer t = new Timer() {
			@Override
			public void run() {
				loop.run();
			}
		};
		t.schedule(1);
	}
}
