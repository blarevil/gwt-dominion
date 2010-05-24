package com.jeeex.cardgame.client.event;

import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jeeex.cardgame.shared.entity.Message;

/**
 * The main event bus.
 * <p>
 * Event bus is a design patter, where all the interactions between the
 * components in the system interacts via the (singleton) event bus. Compared to
 * the other approach - registering event listeners on each other - this
 * approach results in decoupled dependency between the components.
 * <p>
 * One drawback of this approach is that the event bus can become complex,
 * single nexus of all events in the system.
 * 
 * @author Jeeyoung Kim
 * @since 2010-05-24
 */
@Singleton
public class MyEventBus extends DefaultEventBus {
	{
		/*
		 * Steps to add new events to this class.
		 * 
		 * 1. Create a new private static final Type object for the new event.
		 * Do not reuse the existing ones, even if their type signature are
		 * identical - object identity is used to distinguish between the
		 * registered listeners. Let the signature of this Type object be
		 * Type<GenericHandler<Foo>>
		 * 
		 * 2. Create a new public bar(Foo event) method, calling fire(FooType,
		 * event), where bar is the name of the event.
		 * 
		 * 3. Create a new public onBar(GenericHandler<Foo> handler) method,
		 * calling mgr.addHandler(FooType, handler).
		 * 
		 * This comment was written in 2010-05-25
		 * 
		 * TODO(Jeeyoung Kim) - If you learn more about GWT generators, see if
		 * you can automatically generate the content of the methods from the
		 * stub interface.
		 */
	}
	// Statically initiated type objects to distinguish the registered
	// listeners.

	private static final Type<GenericHandler<Widget>> CENTER_WIDGET = new Type<GenericHandler<Widget>>();
	private static final Type<GenericHandler<String>> PRINTLN = new Type<GenericHandler<String>>();
	private static final Type<GenericHandler<List<Message>>> MESSAGES = new Type<GenericHandler<List<Message>>>();
	private static final Type<GenericHandler<Widget>> MENU_WIDGET = new Type<GenericHandler<Widget>>();
	private static final Type<GenericHandler<IsSerializable>> RPC_RESPONSE = new Type<GenericHandler<IsSerializable>>();

	@Inject
	public MyEventBus(HandlerManager mgr) {
		super(mgr);
	}

	// ////////////////////////
	// Message firing methods
	// ////////////////////////

	/** Print a line to chat console. */
	public void println(String msg) {
		fire(PRINTLN, msg);
	}

	/** Set the center widget for the lobby. */
	public void setCenterWidget(Widget widget) {
		fire(CENTER_WIDGET, widget);
	}

	/** Set the menu widget for the lobby. */
	public void setMenuWidget(Widget widget) {
		fire(MENU_WIDGET, widget);
	}

	/** Invoked whenever a new message is received from the message loop. */
	public void messageReceived(List<Message> msg) {
		fire(MESSAGES, msg);
	}

	public void rpcReceived(IsSerializable rpcResponse) {
		fire(RPC_RESPONSE, rpcResponse);
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

	public void onSetMenuWidget(GenericHandler<Widget> handler) {
		mgr.addHandler(MENU_WIDGET, handler);
	}

	public void onMessageReceived(GenericHandler<List<Message>> handler) {
		mgr.addHandler(MESSAGES, handler);
	}

	public void onRpcReceived(GenericHandler<IsSerializable> handler) {
		mgr.addHandler(RPC_RESPONSE, handler);
	}
}
