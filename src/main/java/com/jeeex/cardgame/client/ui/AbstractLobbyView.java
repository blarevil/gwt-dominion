package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public abstract class AbstractLobbyView extends Composite {
	public abstract Button getCreateUserButton();

	public abstract Button getLogoutButton();

	public abstract Button getLoginButton();

	public abstract Label getAuthTokenLabel();

	public abstract Button getCreateGameButton();

	public abstract Button getRefreshButton();
}