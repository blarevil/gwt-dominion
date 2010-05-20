package com.jeeex.cardgame.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LobbyView extends AbstractLobbyView {
	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, LobbyView> {
	}

	@UiField
	FlowPanel gamelist;

	@UiField
	Button createUserBtn;

	@UiField
	Button loginBtn;

	@UiField
	Button logoutBtn;

	@UiField
	Button createGameBtn;

	@UiField
	Label debugAuthTokenLbl;

	@UiField
	Button refreshBtn;

	public FlowPanel getGamelist() {
		return gamelist;
	}

	public LobbyView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Button getCreateUserButton() {
		return createUserBtn;
	}

	public Button getLogoutButton() {
		return logoutBtn;
	}

	public Button getLoginButton() {
		return loginBtn;
	}

	public Label getAuthTokenLabel() {
		return debugAuthTokenLbl;
	}

	public Button getCreateGameButton() {
		return createGameBtn;
	}

	public Button getRefreshButton() {
		return refreshBtn;
	}
}
