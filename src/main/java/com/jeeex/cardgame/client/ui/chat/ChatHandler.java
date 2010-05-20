package com.jeeex.cardgame.client.ui.chat;

import com.google.gwt.event.shared.EventHandler;

public interface ChatHandler extends EventHandler {
	public void onChatEvent(ChatEvent event);
}
