package com.jeeex.cardgame.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.console.Console;
import com.jeeex.cardgame.client.data.msgloop.MessageLoop;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.event.TypeConstants;
import com.jeeex.cardgame.client.ui.LobbyPresenter;
import com.jeeex.cardgame.client.ui.MainPresenter;
import com.jeeex.cardgame.client.util.EmptyCallback;
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
		RootPanel.get().add((Widget)lobbyPresenter.getView());
	}

	public void runWithoutLobby() {
		mp.init();
		ebus.getHandlerManager().addHandler(TypeConstants.STRING,
				new GenericHandler<String>() {
					@Override
					public void onEvent(String event) {
						console.exec(event);
						ChatMessage msg = new ChatMessage();
						msg.setMessage(event);
						async.sendMessage(new SendMessageRequest(msg),
								new EmptyCallback<SendMessageResponse>());
					}
				});
		// register message loop.
		RootPanel.get().add(mp.getView());

		Timer timer = new Timer() {
			@Override
			public void run() {
				loop.setCounter(1);
				loop.run();
			}
		};
		// run the XHR in timer.
		timer.schedule(1);
	}
}
