package com.jeeex.cardgame.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MainView extends AbstractChatView {
	private static Binder uiBinder = GWT.create(Binder.class);
	@UiField(provided = true)
	final Composite handPnl;

	@UiField
	TextArea chatTypeWgt;

	@UiField
	TextArea chatHistoryWgt;

	interface Binder extends UiBinder<Widget, MainView> {
	}

	@Inject
	public MainView(HandPanel handPanel) {
		handPnl = handPanel;

		// call this method at the end
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void appendChatMessage(String message) {
		String old = chatHistoryWgt.getText();
		chatHistoryWgt.setText(old + "\n" + message);
	}

	@Override
	public TextArea getChatMessageArea() {
		return chatTypeWgt;
	}

	@Override
	public TextArea getChatHistoryArea() {
		return chatHistoryWgt;
	}
}
