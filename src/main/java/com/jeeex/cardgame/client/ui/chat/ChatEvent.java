package com.jeeex.cardgame.client.ui.chat;

import com.google.gwt.event.shared.GwtEvent;

/** This is part of MainPresenter. */
@Deprecated
public class ChatEvent extends GwtEvent<ChatHandler> {

	public static final Type<ChatHandler> TYPE = new Type<ChatHandler>();

	final String chatMessage;

	public String getChatMessage() {
		return chatMessage;
	}

	public ChatEvent(String chatMessage) {
		super();
		if (chatMessage == null) {
			throw new NullPointerException("chatMessage");
		}
		this.chatMessage = chatMessage;
	}

	@Override
	protected void dispatch(ChatHandler handler) {
		handler.onChatEvent(this);
	}

	@Override
	public Type<ChatHandler> getAssociatedType() {
		return TYPE;
	}

}
