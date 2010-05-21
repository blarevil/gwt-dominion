package com.jeeex.cardgame.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.ui.widget.GameListWidget;

public class LobbyView extends AbstractLobbyView {
	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, LobbyView> {
	}

	@UiField(provided = true)
	GameListWidget gameList;

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

	public GameListWidget getGamelist() {
		return gameList;
	}

	@Inject
	public LobbyView(GameListWidget gameList) {
		this.gameList = gameList;
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
