package com.jeeex.cardgame.client.ui.chat;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class ChatView extends Composite {
	private static final Binder binder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ChatView> {
	}

	@UiField
	TextArea chatBox;

	@UiField
	TextArea chatHistory;

	public ChatView() {
		initWidget(binder.createAndBindUi(this));
	}

	public TextArea getChatHistory() {
		return chatHistory;
	}

	public TextArea getChatBox() {
		return chatBox;
	}
}
