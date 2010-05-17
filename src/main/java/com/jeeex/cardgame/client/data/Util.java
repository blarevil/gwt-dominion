package com.jeeex.cardgame.client.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Util {

	private Util() {
	}

	public static Map<Card, CardPile> aggregate(Collection<Card>... cards) {
		Map<Card, CardPile> map = new HashMap<Card, CardPile>();
		for (Collection<Card> col : cards) {
			for (Card card : col) {
				CardPile cardPile = map.get(card);
				if (cardPile == null) {
					cardPile = new CardPile(card, 1);
					map.put(card, cardPile);
				} else {
					cardPile.addCard();
				}
			}
		}
		return map;
	}
}
