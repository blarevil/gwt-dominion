package com.jeeex.cardgame.client.ui.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GameMenuView extends Composite {

	private static GameMenuViewUiBinder uiBinder = GWT
			.create(GameMenuViewUiBinder.class);

	interface GameMenuViewUiBinder extends UiBinder<Widget, GameMenuView> {
	}

	@UiField
	Button leaveGameBtn;

	public GameMenuView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Button getLeaveGameButton() {
		return leaveGameBtn;
	}
}
