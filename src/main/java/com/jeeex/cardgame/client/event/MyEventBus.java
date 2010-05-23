package com.jeeex.cardgame.client.event;

import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jeeex.cardgame.shared.entity.Message;

/**
 * The main eventbus.
 * <p>
 * All the global events should go through this event bus.
 * 
 * Instead of depending on {@link #getHandlerManager()}, i should create helper
 * methods for handlers too.
 */
@Singleton
public class MyEventBus extends DefaultEventBus {
	// TYPE CONSTANTS.
	public static final Type<GenericHandler<Widget>> CENTER_WIDGET = new Type<GenericHandler<Widget>>();
	public static final Type<GenericHandler<String>> PRINTLN = new Type<GenericHandler<String>>();
	private static final Type<GenericHandler<List<Message>>> MESSAGES = new Type<GenericHandler<List<Message>>>();

	@Inject
	public MyEventBus(HandlerManager mgr) {
		super(mgr);
	}

	// ////////////////////////
	// Message firing methods
	// ////////////////////////

	// event firing methods.
	public void println(String msg) {
		fire(PRINTLN, msg);
	}

	// set the center widget of the lobby.
	public void setCenterWidget(Widget widget) {
		fire(CENTER_WIDGET, widget);
	}

	public void messageReceived(List<Message> msg) {
		fire(MESSAGES, msg);
	}

	// ////////////////////////
	// Handler registeration methods
	// ////////////////////////

	public void onPrintln(GenericHandler<String> handler) {
		mgr.addHandler(PRINTLN, handler);
	}

	public void onSetCenterWidget(GenericHandler<Widget> handler) {
		mgr.addHandler(CENTER_WIDGET, handler);
	}

	public void onMessageReceived(GenericHandler<List<Message>> handler) {
		mgr.addHandler(MESSAGES, handler);
	}
}
