package com.jeeex.cardgame.client.data;

import java.util.List;
import java.util.Map;

/**
 * Object that represents the state of a game of Dominion.
 */
public class GameState {
	// Current turn.
	// TODO(Jeeyoung Kim) make a variable for it.
	public enum State {
		INIT,
		// player related.
		BEGIN, ACTION, REACTION, BUY, CLEANUP, END,
		// end of the game.
		ENDGAME;
	}

	// Players in the game.
	List<Player> players;

	// Cards in BUY pile - drafted.
	Map<Card, CardPile> buyPile;

	// Cards in BUY pile - default.
	Map<Card, CardPile> defaultPile;

	private int currentPlayer = 0;

	private State state = State.INIT;

	private void action() {
		// TODO Auto-generated method stub

	}

	public void begin() {
	}

	private void init() {
		assert state == State.INIT;
		for (Player player : players) { // dealing cards.
			for (int i = 0; i < 7; i++) {
				player
						.addToBuy(defaultPile.get(CardConstants.COPPER)
								.takeOne());
			}
			for (int i = 0; i < 3; i++) {
				player
						.addToBuy(defaultPile.get(CardConstants.ESTATE)
								.takeOne());
			}
		}

		for (Player player : players) {
			player.draw(5);
		}
	}

	public void run() {
		switch (state) {
		case INIT:
			init();
			break;
		case BEGIN:
			begin();
			break;
		case ACTION:
			action();
		case END:
			break;
		case ENDGAME:
			break;
		default:
			break;
		}
	}

	/** Totally for debuggging. */
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("Players:     ").append(players);
		b.append('\n');
		b.append("State:       ").append(state);
		b.append('\n');
		b.append("DefaultPile: ").append(defaultPile);
		b.append('\n');
		b.append("CurPlayer:   ").append(
				currentPlayer + ":" + players.get(currentPlayer).getName());
		b.append('\n');
		return b.toString();
	}
}
