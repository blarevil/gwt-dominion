package com.jeeex.cardgame.client.ui.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.jeeex.cardgame.client.data.Card;
import com.jeeex.cardgame.client.res.DefaultResource.MyStyle;

public class CardWidget extends Composite {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, CardWidget> {
	}

	Card card;

	@UiField
	Label cardName;

	@Inject
	public CardWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setCard(Card card) {
		this.card = card;
		this.cardName.setText(card.getName());
	}

	public static class Factory {
		private final Provider<CardWidget> provider;
		private final MyStyle style;

		@Inject
		public Factory(Provider<CardWidget> provider, MyStyle style) {
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
