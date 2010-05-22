package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Widget;
import com.jeeex.cardgame.shared.entity.AuthToken;

/**
 * Contains all the GwtEvent types used by {@link MyEventBus}.
 */
public class TypeConstants {
	public static final Type<GenericHandler<String>> STRING = new Type<GenericHandler<String>>();
	public static final Type<GenericHandler<AuthToken>> AUTHTOKEN = new Type<GenericHandler<AuthToken>>();
	public static final Type<GenericHandler<Widget>> CENTER_WIDGET = new Type<GenericHandler<Widget>>();
}
