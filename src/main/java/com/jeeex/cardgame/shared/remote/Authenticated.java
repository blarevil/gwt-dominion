package com.jeeex.cardgame.shared.remote;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.shared.entity.AuthToken;

/** Represents authenticated messages. */
public interface Authenticated extends IsSerializable {
	public AuthToken getAuthToken();

	public void setAuthToken(AuthToken token);
}
