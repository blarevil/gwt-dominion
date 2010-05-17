package com.jeeex.cardgame.client.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Provider;

public class GameStateFactory implements Provider<GameState> {

	public GameState get() {
		GameState gs = new GameState();
		gs.buyPile = buyPile();
		gs.defaultPile = defaultPile();
		gs.players = new ArrayList<Player>();

		String [] names = {"Woongbin", "Minsang", "Jeeyoung", "Halley"};
		for(int i = 0 ; i < 4; i++){
			Player p = new Player();
			p.setName(names[i]);
			gs.players.add(p);
		}
		return gs;
	}

	private Map<Card, CardPile> buyPile() {
		Map<Card, CardPile> bp = new HashMap<Card, CardPile>();
		return bp;
	}

	private Map<Card, CardPile> defaultPile() {
		Map<Card, CardPile> dp = new HashMap<Card, CardPile>();

		putToCardPile(dp, new CardPile(CardConstants.COPPER, 60));
		putToCardPile(dp, new CardPile(CardConstants.SILVER, 50));
		putToCardPile(dp, new CardPile(CardConstants.GOLD, 40));

		putToCardPile(dp, new CardPile(CardConstants.ESTATE, 24));
		putToCardPile(dp, new CardPile(CardConstants.DUCHY, 12));
		putToCardPile(dp, new CardPile(CardConstants.PROVINCE, 12));

		return dp;
	}

	private void putToCardPile(Map<Card, CardPile> piles, CardPile pile) {
		piles.put(pile.getCard(), pile);
	}
}
