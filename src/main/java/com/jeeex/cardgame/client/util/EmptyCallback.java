package com.jeeex.cardgame.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class EmptyCallback<T> implements AsyncCallback<T> {
	@Override
	public void onFailure(Throwable caught) {
		Window.alert("onFailure():" + caught);
	}

	@Override
	public void onSuccess(T result) {
		GWT.log(String.valueOf(result));
	}
}
