package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.data.Card;
import com.jeeex.cardgame.client.res.DefaultResource.Style;
import com.jeeex.cardgame.client.ui.CardWidget.Factory;

public class HandPanel extends Composite implements HandPanelInterface {
	AbsolutePanel panel = new AbsolutePanel();

	final Style style;

	private Factory factory;

	@Inject
	public HandPanel(Style style, CardWidget.Factory factory) {
		this.factory = factory;
		this.style = style;

		Widget card1 = makeCard();
		panel.add(card1);
		panel.setWidgetPosition(card1, 0, 0);

		Widget card2 = makeCard();
		panel.add(card2);
		panel.setWidgetPosition(card2, 50, 0);

		style.ensureInjected();
		initWidget(panel);
	}

	public Widget makeCard() {
		Widget wgt = factory.get(new Card());
		return wgt;
	}
}
