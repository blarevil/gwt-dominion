package com.jeeex.cardgame.client.data;

import com.jeeex.cardgame.client.data.TreasureCard.ConcreteTreasureCard;
import com.jeeex.cardgame.client.data.VictoryCard.ConcreteVictoryPointCard;

public class CardConstants {

	public static TreasureCard COPPER =
		new ConcreteTreasureCard("Copper", 0, 1);
	public static TreasureCard SILVER =
		new ConcreteTreasureCard("Silver", 3, 2);
	public static TreasureCard GOLD =
		new ConcreteTreasureCard("Gold", 6, 3);

	public static final VictoryCard ESTATE =
		new ConcreteVictoryPointCard("Estate", 2, 1);
	public static final VictoryCard DUCHY = 
		new ConcreteVictoryPointCard("Duchy", 5, 3);
	public static final VictoryCard PROVINCE = 
		new ConcreteVictoryPointCard("Province", 8, 6);

	private CardConstants() {
	}
}
