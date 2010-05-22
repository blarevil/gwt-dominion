package com.jeeex.cardgame.client.event;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The main eventbus.
 * <p>
 * All the global events should go through this event bus.
 * 
 */
@Singleton
public class MyEventBus extends DefaultEventBus {	
	@Inject
	public MyEventBus(HandlerManager mgr) {
		super(mgr);
	}

	// event firing methods.
	public void println(String msg) {
		fire(TypeConstants.STRING, msg);
	}

	// set the center widget of the lobby.
	public void setCenterWidget(Widget widget) {
		fire(TypeConstants.CENTER_WIDGET, widget);
	}
}
