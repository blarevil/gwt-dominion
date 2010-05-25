package com.jeeex.cardgame.client.data.msgloop;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.shared.remote.message.MessageServiceAsync;
import com.jeeex.cardgame.shared.remote.message.WaitForMessageRequest;
import com.jeeex.cardgame.shared.remote.message.WaitForMessageResponse;

public class MessageLoop implements Runnable {

	public class Callback implements AsyncCallback<WaitForMessageResponse> {
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Failure during XHR - terminating.");
		}

		@Override
		public void onSuccess(WaitForMessageResponse result) {
			// publish the message.
			ebus.messageReceived(result.getMessages());
			// TODO(Jeeyoung Kim) Dynamically increment the counter.
			counter++;
			async.waitForMessage(new WaitForMessageRequest(counter), callback);
		}
	}

	private final Callback callback;

	private long counter = 1;

	@Inject
	private MessageServiceAsync async;
	
	@Inject
	private MyEventBus ebus;

	public MessageLoop() {
		this.callback = new Callback();
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	public void run() {
		async.waitForMessage(new WaitForMessageRequest(counter), callback);
	}
}
