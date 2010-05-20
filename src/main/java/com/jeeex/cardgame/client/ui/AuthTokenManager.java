package com.jeeex.cardgame.client.ui;

import static com.jeeex.cardgame.client.event.GenericEvent.makeEvent;

import com.google.gwt.event.shared.HandlerManager;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.TypeConstants;

public class AuthTokenManager {

	private String tkn;

	private HandlerManager mgr = new HandlerManager(this);

	public void setAuthToken(String token) {
		tkn = token;
		mgr.fireEvent(makeEvent(TypeConstants.STRING, token));
	}

	public String getAuthToken() {
		return tkn;
	}

	public boolean isAuthTokenSet() {
		return tkn != null;
	}

	public void addHandler(GenericHandler<String> handler) {
		mgr.addHandler(TypeConstants.STRING, handler);
	}
}
