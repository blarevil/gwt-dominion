package com.jeeex.cardgame.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.data.model.HandlerEndpoint;
import com.jeeex.cardgame.client.ui.chat.ChatEvent;
import com.jeeex.cardgame.client.ui.chat.ChatHandler;

public class MainView extends Composite implements ChatView {
	private static Binder uiBinder = GWT.create(Binder.class);
	@UiField(provided = true)
	final Composite handPnl;

	HandlerManager mgr = new HandlerManager(this);

	@UiField
	TextArea chatTypeWgt;

	@UiField
	TextArea chatHistoryWgt;

	interface Binder extends UiBinder<Widget, MainView> {
	}

	@Inject
	public MainView(HandPanel handPanel) {
		handPnl = handPanel;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void appendChatMessage(String message) {
		String old = chatHistoryWgt.getText();
		chatHistoryWgt.setText(old + "\n" + message);
	}

	@Override
	public HandlerEndpoint<ChatHandler> chatEndpoint() {
		return new HandlerEndpoint<ChatHandler>(mgr, ChatEvent.TYPE);
	}

	@UiHandler("chatTypeWgt")
	public void onEnter(KeyPressEvent event) {
		// TODO(Jeeyoung Kim)
		// Figure out whether this method is suitable for View-level logic.
		// As far as I know, Views in MVP is suppose to stay very dumb.
		// but I don't know event filtering / wrapping is considered dumb.
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
			mgr.fireEvent(new ChatEvent(chatTypeWgt.getText()));
			event.preventDefault();
		}
	}

	@Override
	public HasText getChatMessage() {
		return chatTypeWgt;
	}
}
