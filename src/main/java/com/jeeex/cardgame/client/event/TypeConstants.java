package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.jeeex.cardgame.shared.entity.AuthToken;

/**
 * Contains all the GwtEvent types.
 */
public class TypeConstants {
	public static final Type<GenericHandler<AuthToken>> AUTHTOKEN = new Type<GenericHandler<AuthToken>>();
}
