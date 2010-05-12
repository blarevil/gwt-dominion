package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.res.DefaultResource.Style;

public class HandPanel extends Composite implements HandPanelInterface {
	AbsolutePanel panel = new AbsolutePanel();

	final Style style;

	@Inject
	public HandPanel(Style style) {
		this.style = style;

		Label card1 = makeCard();
		panel.add(card1);
		panel.setWidgetPosition(card1, 0, 0);

		Label card2 = makeCard();
		panel.add(card2);
		panel.setWidgetPosition(card2, 50, 0);

		style.ensureInjected();
		initWidget(panel);
	}

	public Label makeCard() {
		Label lbl = new Label("card");
		lbl.addStyleName(style.card());
		return lbl;
	}
}
