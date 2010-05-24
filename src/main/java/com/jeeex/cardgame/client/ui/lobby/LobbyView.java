package com.jeeex.cardgame.client.ui.lobby;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.ui.ChatView;
import com.jeeex.cardgame.client.ui.widget.ClientUtil;

public class LobbyView extends Composite {
	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, LobbyView> {
	}

	@UiField(provided = true)
	ChatView chatView;

	@UiField
	FlowPanel centerPanel;

	@UiField
	FlowPanel menuPanel;

	@Inject
	public LobbyView() {
	}

	public void setChatView(ChatView view) {
		this.chatView = view;
	}

	public void init() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setCenterWidget(Widget widget) {
		ClientUtil.removeAllChildren(centerPanel);
		centerPanel.add(widget);
	}

	public void setMenuWidget(Widget widget) {
		// TODO(Jeeyoung Kim): Implement this.
		ClientUtil.removeAllChildren(menuPanel);
		menuPanel.add(widget);
	}
}
