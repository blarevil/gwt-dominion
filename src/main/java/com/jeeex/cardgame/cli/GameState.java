package com.jeeex.cardgame.cli;

import java.util.List;
import java.util.Map;

import com.jeeex.cardgame.client.data.Card;
import com.jeeex.cardgame.client.data.CardPile;

/**
 * Object that represents the state of a game of Dominion.
 * <p>
 * This object, and player object, should stay "dumb" as possible.
 */
public class GameState {
	public enum State {
		INIT,
		// player related.
		BEGIN, ACTION, REACTION, BUY, CLEANUP, END,
		// end of the game.
		ENDGAME;
	}

	public Player getPlayer(long id) {
		for (Player p : players) {
			if (p.getUserid() == id) {
				return p;
			}
		}
		return null;
	}

	// Players in the game.
	List<Player> players;

	// Cards in BUY pile - drafted.
	Map<Card, CardPile> buyPile;

	/** Index of the current player */
	private int currentPlayer = 0;

	/** Returns the player id of the current player. */
	public long getCurrentPlayerId() {
		return players.get(currentPlayer).getUserid();
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	private State state = State.INIT;

	public void setState(final State newState) {
		this.state = newState;
	}

	public State getState() {
		return state;
	}

	public void nextPlayer() {
		currentPlayer = (currentPlayer + 1) % players.size();
	}

	/** Totally for debuggging. */
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("Players:     ").append(players);
		b.append('\n');
		b.append("State:       ").append(state);
		b.append('\n');
		b.append("BuyPile:     ").append(buyPile.values());
		b.append('\n');
		b.append("CurPlayer:   ").append(
				currentPlayer + ":" + players.get(currentPlayer).getName());
		b.append('\n');
		return b.toString();
	}
}
