package com.jeeex.cardgame.client.data.msgloop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
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
			// success is good.
			GWT.log("FOO");
			counter++;
			async.waitForMessage(new WaitForMessageRequest(counter), callback);
		}
	}

	private final Callback callback;

	private long counter = 1;

	@Inject
	private MessageServiceAsync async;

	public MessageLoop() {
		// initialze callback.
		this.callback = new Callback();
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	public void run() {
		// This method doesn't block!
		async.waitForMessage(new WaitForMessageRequest(counter), callback);
	}
}
