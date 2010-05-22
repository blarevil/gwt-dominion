package com.jeeex.cardgame.client.ui;

import static com.jeeex.cardgame.client.event.GenericEvent.makeEvent;

import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.TypeConstants;
import com.jeeex.cardgame.shared.entity.AuthToken;

public class AuthTokenManager {

	private AuthToken tkn;

	private HandlerManager mgr = new HandlerManager(this);

	/** Should be initialized by Guice. */
	@Inject
	private AuthTokenManager() {
	}

	public void setAuthToken(AuthToken token) {
		tkn = token;
		mgr.fireEvent(makeEvent(TypeConstants.AUTHTOKEN, token));
	}

	public AuthToken getAuthToken() {
		return tkn;
	}

	public boolean isAuthTokenSet() {
		return tkn != null;
	}

	public void addHandler(GenericHandler<AuthToken> handler) {
		mgr.addHandler(TypeConstants.AUTHTOKEN, handler);
	}
}
