package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.HasText;
import com.jeeex.cardgame.client.data.model.HandlerEndpoint;
import com.jeeex.cardgame.client.ui.chat.ChatHandler;

public interface ChatView {

	public void appendChatMessage(String message);

	public HandlerEndpoint<ChatHandler> chatEndpoint();

	public HasText getChatMessage();
}
