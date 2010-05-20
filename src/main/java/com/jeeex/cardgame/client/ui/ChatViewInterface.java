package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.TextArea;

public interface ChatViewInterface {

	public void appendChatMessage(String message);

	public TextArea getChatMessageArea();

	public TextArea getChatHistoryArea();
}