package com.jeeex.cardgame.client.ui.lobby;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LobbyMenuView extends Composite {

	private static LobbyMenuViewUiBinder uiBinder = GWT
			.create(LobbyMenuViewUiBinder.class);

	interface LobbyMenuViewUiBinder extends UiBinder<Widget, LobbyMenuView> {
	}


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

	public LobbyMenuView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	// Getters //

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
