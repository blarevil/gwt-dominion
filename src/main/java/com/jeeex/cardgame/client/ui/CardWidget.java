package com.jeeex.cardgame.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.jeeex.cardgame.client.data.Card;
import com.jeeex.cardgame.client.res.DefaultResource.Style;

public class CardWidget extends Composite {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, CardWidget> {
	}

	Card card;

	@Inject
	public CardWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public static class Factory {
		private final Provider<CardWidget> provider;
		private final Style style;

		@Inject
		public Factory(Provider<CardWidget> provider, Style style) {
			this.provider = provider;
			this.style = style;
		}

		public CardWidget get(Card card) {
			CardWidget wgt = provider.get();
			wgt.setCard(card);
			wgt.addStyleName(style.card());
			return wgt;
		}
	}
}
