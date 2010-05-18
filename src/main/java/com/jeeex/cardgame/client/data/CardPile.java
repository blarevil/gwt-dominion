package com.jeeex.cardgame.client.data;

public class CardPile {

	private final Card card;

	public int number;

	public CardPile(Card card, int number) {
		assert card != null;
		assert number >= 0;
		this.card = card;
		this.number = number;
	}

	public Card getCard() {
		return card;
	}

	@Override
	public String toString() {
		return card.getName() + ":" + number;
	}

	public void modifySize(int diff) {
		number += diff;
	}
}
