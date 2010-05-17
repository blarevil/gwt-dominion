package com.jeeex.cardgame.client.data;

import java.util.List;
import java.util.Map;

/**
 * Object that represents the state of a game of Dominion.
 */
public class GameState {
	// Players in the game.
	List<Player> players;

	// Cards in BUY pile - drafted.
	Map<Card, CardPile> buyPile;

	// Cards in BUY pile - default.
	Map<Card, CardPile> defaultPile;

	// Current turn.
	// TODO(Jeeyoung Kim) make a variable for it.
	public enum State{
		
	}

	public void init() {
	}
}
