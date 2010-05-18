package com.jeeex.cardgame.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.console.Console;
import com.jeeex.cardgame.client.data.msgloop.MessageLoop;
import com.jeeex.cardgame.client.event.BusHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.event.TypeConstants;
import com.jeeex.cardgame.client.ui.MainPresenter;
import com.jeeex.cardgame.client.util.EmptyCallback;
import com.jeeex.cardgame.shared.remote.MessageServiceAsync;
import com.jeeex.cardgame.shared.remote.SendMessageRequest;
import com.jeeex.cardgame.shared.remote.SendMessageResponse;
import com.jeeex.cardgame.shared.remote.entity.ChatMessage;

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

	@Override
	public void run() {
		mp.init();		
		ebus.getHandlerManager().addHandler(TypeConstants.MESSAGE, new BusHandler<String>() {
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
