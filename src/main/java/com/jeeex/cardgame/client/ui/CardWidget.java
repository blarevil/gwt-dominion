package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.jeeex.cardgame.client.data.Card;

public class CardWidget extends Composite {
	HTMLPanel panel;

	Card card;

	@Inject
	public CardWidget(HTMLPanel panel) {
		this.panel = panel;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public static class Factory {
		private final Provider<CardWidget> provider;

		@Inject
		public Factory(Provider<CardWidget> provider) {
			this.provider = provider;
		}

		public CardWidget get(Card card) {
			CardWidget wgt = provider.get();
			wgt.setCard(card);
			return wgt;
		}
	}
}
