package com.jeeex.cardgame.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Base for all the callbacks.
 */
public class BaseCallback<T> implements AsyncCallback<T> {
	/**
	 * The default failure behavior is using Window.alert() to prevent silent
	 * consumption of the error messages.
	 */
	@Override
	public void onFailure(Throwable caught) {
		Window.alert("onFailure():" + caught);
	}

	@Override
	public void onSuccess(T result) {
		GWT.log(String.valueOf(result));
	}
}
