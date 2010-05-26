package com.jeeex.cardgame.client.ui.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GameView extends Composite {

	private static GameViewUiBinder uiBinder = GWT
			.create(GameViewUiBinder.class);

	interface GameViewUiBinder extends UiBinder<Widget, GameView> {
	}

	public GameView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
