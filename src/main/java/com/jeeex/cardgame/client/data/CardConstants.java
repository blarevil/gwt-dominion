package com.jeeex.cardgame.client.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.jeeex.cardgame.client.data.TreasureCard.ConcreteTreasureCard;
import com.jeeex.cardgame.client.data.VictoryCard.ConcreteVictoryPointCard;

public class CardConstants {

	// Treasure cards.
	public static TreasureCard COPPER = new ConcreteTreasureCard("Copper", 0, 1);
	public static TreasureCard SILVER = new ConcreteTreasureCard("Silver", 3, 2);
	public static TreasureCard GOLD = new ConcreteTreasureCard("Gold", 6, 3);

	// Victory point cards.
	public static final VictoryCard ESTATE = new ConcreteVictoryPointCard(
			"Estate", 2, 1);
	public static final VictoryCard DUCHY = new ConcreteVictoryPointCard(
			"Duchy", 5, 3);
	public static final VictoryCard PROVINCE = new ConcreteVictoryPointCard(
			"Province", 8, 6);

	// Action cards.
	public static final ActionCard VILLAGE = new ConcreteActionCard("Village",
			3, 2, 0, 1, 0);
	public static final ActionCard MARKET = new ConcreteActionCard("Market", 5,
			1, 1, 1, 1);
	public static final ActionCard LABORATORY = new ConcreteActionCard(
			"Laboratory", 5, 1, 0, 2, 0);
	public static final ActionCard FESTIVAL = new ConcreteActionCard(
			"Festival", 5, 2, 1, 0, 2);

	public static final Map<String, Card> NAME_TO_CARD;
	static {
		Map<String, Card> map = new HashMap<String, Card>();
		toMap(map, COPPER, SILVER, GOLD, ESTATE, DUCHY, PROVINCE, VILLAGE,
				MARKET, LABORATORY, FESTIVAL);
		NAME_TO_CARD = Collections.unmodifiableMap(map);
	}

	private static void toMap(Map<String, Card> map, Card... cards) {
		for (Card card : cards) {
			map.put(card.getName(), card);
		}
	}

	private CardConstants() {
	}
}
