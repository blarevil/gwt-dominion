package com.jeeex.cardgame.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class LobbyView extends Composite {
	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, LobbyView> {
	}
	
	@UiField
	FlowPanel gamelist;

	public FlowPanel getGamelist() {
		return gamelist;
	}

	public LobbyView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
