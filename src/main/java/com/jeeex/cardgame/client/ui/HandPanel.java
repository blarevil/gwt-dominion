package com.jeeex.cardgame.client.ui;

import static com.jeeex.cardgame.client.ui.generic.AnimationBuilder.aniBuilder;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jeeex.cardgame.client.data.CardImpl;
import com.jeeex.cardgame.client.data.Card;
import com.jeeex.cardgame.client.res.DefaultResource.MyStyle;
import com.jeeex.cardgame.client.ui.CardWidget.Factory;

@Singleton
public class HandPanel extends Composite {
	List<CardWidget> cards = new ArrayList<CardWidget>();

	// RANDOM coordinate-related variables.
	int width = 500;
	int height = 60;
	int cardwidth = 40;
	int cardheight = 60;

	int gap = 10;

	// number of cards in the panel.
	AbsolutePanel panel = new AbsolutePanel();

	final MyStyle style;

	private Factory factory;

	@Inject
	public HandPanel(MyStyle style, CardWidget.Factory factory) {
		this.factory = factory;
		this.style = style;

		// addCard();

		initWidget(panel);
	}

	public void addCard() {
		CardWidget wgt = makeCard("FOO");
		int cardcount = cards.size();

		panel.add(wgt);
		panel.setWidgetPosition(wgt, 0, 0);
		final int endx = width - cardwidth * (cardcount + 1) - gap * cardcount;
		Animation a = aniBuilder().start(0, 0).end(endx, 0).panel(panel)
				.widget(wgt).opacity(0, 1).build();
		a.run(1000);

		cards.add(wgt);
	}

	public void removeCard() {
		{
			CardWidget card = cards.remove(0);
			panel.remove(card);
		}

		for (CardWidget wgt : cards) {
			aniBuilder().widget(wgt).panel(panel).start(
					panel.getWidgetLeft(wgt), panel.getWidgetTop(wgt)).shift(
					cardwidth + gap, 0).build().run(1000);
		}
	}

	public CardWidget makeCard(String name) {
		Card card = new CardImpl(name, "", 0);
		CardWidget wgt = factory.get(card);
		style.ensureInjected();
		return wgt;
	}
}
