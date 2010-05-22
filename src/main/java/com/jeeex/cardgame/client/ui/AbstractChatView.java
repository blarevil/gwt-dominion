package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;

public abstract class AbstractChatView extends Composite {
	public abstract void appendChatMessage(String message);

	public abstract TextArea getChatMessageArea();

	public abstract TextArea getChatHistoryArea();
}
