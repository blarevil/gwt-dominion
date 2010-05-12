package com.jeeex.cardgame.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MainPanel extends Composite {
	private static Binder uiBinder = GWT.create(Binder.class);
	@UiField(provided = true)
	final Composite handPnl;

	interface Binder extends UiBinder<Widget, MainPanel> {
	}

	@Inject
	public MainPanel(HandPanel handPanel) {
		handPnl = handPanel;
		initWidget(uiBinder.createAndBindUi(this));
	}
}
