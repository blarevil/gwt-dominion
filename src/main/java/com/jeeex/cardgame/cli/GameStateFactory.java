package com.jeeex.cardgame.cli;

import static com.jeeex.cardgame.client.data.CardConstants.FESTIVAL;
import static com.jeeex.cardgame.client.data.CardConstants.LABORATORY;
import static com.jeeex.cardgame.client.data.CardConstants.MARKET;
import static com.jeeex.cardgame.client.data.CardConstants.VILLAGE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Provider;
import com.jeeex.cardgame.client.data.Card;
import com.jeeex.cardgame.client.data.CardConstants;
import com.jeeex.cardgame.client.data.CardPile;

public class GameStateFactory implements Provider<GameState> {

	public GameState get() {
		GameState gs = new GameState();
		gs.buyPile = buyPile();
		gs.buyPile.putAll(defaultPile());
		gs.players = players();
		return gs;
	}

	private List<Player> players() {
		ArrayList<Player> ps = new ArrayList<Player>();
		String[] names = { "Woongbin", "Minsang", "Jeeyoung", "Halley" };
		for (int i = 0; i < 4; i++) {
			Player p = new Player();
			// default values.
			p.setName(names[i]);
			p.setUserid(i + 1);
			ps.add(p);
		}
		return ps;
	}

	private Map<Card, CardPile> buyPile() {
		Map<Card, CardPile> bp = new HashMap<Card, CardPile>();

		putToCardPile(bp, new CardPile(VILLAGE, 10));
		putToCardPile(bp, new CardPile(FESTIVAL, 10));
		putToCardPile(bp, new CardPile(LABORATORY, 10));
		putToCardPile(bp, new CardPile(MARKET, 10));

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
