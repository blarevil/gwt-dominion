package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * Contains all the GwtEvent types used by {@link MyEventBus}.
 */
public class TypeConstants {
	public static final Type<BusHandler<String>> MESSAGE = new Type<BusHandler<String>>();
}
