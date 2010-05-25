package com.jeeex.cardgame.client.ui.chat;

import com.google.gwt.event.shared.EventHandler;

/** This is part of MainPresenter. */
@Deprecated
public interface ChatHandler extends EventHandler {
	public void onChatEvent(ChatEvent event);
}
