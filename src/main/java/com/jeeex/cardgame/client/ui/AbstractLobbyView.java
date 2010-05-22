package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.jeeex.cardgame.client.ui.widget.GameListView;

public abstract class AbstractLobbyView extends Composite {
	public abstract GameListView getGamelist();

	public abstract Button getCreateUserButton();

	public abstract Button getLogoutButton();

	public abstract Button getLoginButton();

	public abstract Label getAuthTokenLabel();

	public abstract Button getCreateGameButton();

	public abstract Button getRefreshButton();
}