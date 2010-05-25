package com.jeeex.cardgame.client.data.model;

import static com.jeeex.cardgame.client.event.GenericEvent.makeEvent;

import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.TypeConstants;
import com.jeeex.cardgame.shared.entity.AuthToken;

/** Singleton manager for Authentication Token. */
@Singleton
public class AuthTokenManager {
	private AuthToken tkn;

	private HandlerManager mgr = new HandlerManager(this);

	/** Should be initialized by Guice. */
	@Inject
	private AuthTokenManager() {
	}

	public void set(AuthToken token) {
		tkn = token;
		mgr.fireEvent(makeEvent(TypeConstants.AUTHTOKEN, token));
	}

	public AuthToken get() {
		return tkn;
	}

	public boolean isSet() {
		return tkn != null;
	}

	public void addHandler(GenericHandler<AuthToken> handler) {
		mgr.addHandler(TypeConstants.AUTHTOKEN, handler);
	}
}
