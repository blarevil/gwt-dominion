package com.jeeex.cardgame.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.data.model.Binded;
import com.jeeex.cardgame.client.data.model.UserState;
import com.jeeex.cardgame.client.data.msgloop.MessageLoop;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.MainPresenter;
import com.jeeex.cardgame.client.ui.lobby.LobbyPresenter;
import com.jeeex.cardgame.shared.remote.message.MessageServiceAsync;

/**
 * Delegates all the work done by {@link Application}.<br>
 * This class exists for injection.
 */
public class InjectedApplication implements Runnable {

	@Inject
	MainPresenter mp;

	@Inject
	MessageLoop loop;

	@Inject
	MessageServiceAsync async;

	@Inject
	MyEventBus ebus;

	@Inject
	LobbyPresenter lobbyPresenter;

	@Inject
	Binded<UserState> userState;

	@Override
	public void run() {
		// set base user state.
		userState.set(UserState.LOGGED_OUT);
		// register handler for debugging.
		userState.addHandler(new GenericHandler<UserState>() {
			@Override
			public void onEvent(UserState state) {
				ebus.println("new userstate:" + state);
			}
		});

		lobbyPresenter.init();
		RootPanel.get().add((Widget) lobbyPresenter.getView());
		startMessageLoop();
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

	public void runWithoutLobby() {
		mp.init();
		// register message loop.
		RootPanel.get().add(mp.getView());
		startMessageLoop();
	}
}
