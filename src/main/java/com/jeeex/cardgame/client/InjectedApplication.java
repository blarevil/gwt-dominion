package com.jeeex.cardgame.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.console.Console;
import com.jeeex.cardgame.client.data.msgloop.MessageLoop;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.MainPresenter;
import com.jeeex.cardgame.client.ui.lobby.LobbyPresenter;
import com.jeeex.cardgame.client.util.BaseCallback;
import com.jeeex.cardgame.shared.entity.ChatMessage;
import com.jeeex.cardgame.shared.remote.message.MessageServiceAsync;
import com.jeeex.cardgame.shared.remote.message.SendMessageRequest;
import com.jeeex.cardgame.shared.remote.message.SendMessageResponse;

/**
 * Delegates all the work done by {@link Application}.<br>
 * This class exists for injection.
 */
public class InjectedApplication implements Runnable {

	@Inject
	MainPresenter mp;

	@Inject
	Console console;

	@Inject
	MessageLoop loop;

	@Inject
	MessageServiceAsync async;

	@Inject
	MyEventBus ebus;

	@Inject
	LobbyPresenter lobbyPresenter;

	@Override
	public void run() {
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
		ebus.onPrintln(new GenericHandler<String>() {
			@Override
			public void onEvent(String event) {
				console.exec(event);
				ChatMessage msg = new ChatMessage();
				msg.setMessage(event);
				async.sendMessage(new SendMessageRequest(msg),
						new BaseCallback<SendMessageResponse>());
			}
		});
		// register message loop.
		RootPanel.get().add(mp.getView());

		startMessageLoop();
	}
}
