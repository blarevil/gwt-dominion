package com.jeeex.cardgame.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.console.Console;
import com.jeeex.cardgame.client.data.msgloop.MessageLoop;
import com.jeeex.cardgame.client.ui.MainPresenter;
import com.jeeex.cardgame.client.ui.chat.ChatEvent;
import com.jeeex.cardgame.client.ui.chat.ChatHandler;

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

	@Override
	public void run() {
		mp.init();

		// console stuff - for debugging.
		mp.chatEndpoint().addHandler(new ChatHandler() {
			@Override
			public void onChatEvent(ChatEvent event) {
				console.exec(event.getChatMessage());
			}
		});

		// register message loop.
		RootPanel.get().add(mp.getView());

		Timer timer = new Timer() {
			@Override
			public void run() {
				loop.setCounter(0);
				// loop.run();
			}
		};

		// run the XHR in timer.
		timer.schedule(1);
	}
}
