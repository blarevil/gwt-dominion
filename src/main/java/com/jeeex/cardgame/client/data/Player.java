package com.jeeex.cardgame.client.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	List<Card> hand;
	List<Card> deck;
	List<Card> discardPile;
	List<Card> playedCard;

	public Player() {
		hand = new ArrayList<Card>();
		deck = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
	}

	public void draw(int num) {
		for (int i = 0; i < num; i++) {
			draw();
		}
	}

	/** true if draw succeeded. */
	public boolean draw() {
		if (deck.isEmpty()) {
			if (discardPile.isEmpty())
				return false; // draw failed
			deck.addAll(discardPile);
			Collections.shuffle(deck);
		}
		hand.add(takeFromTop());
		return true;
	}

	public Card takeFromTop() {
		return deck.remove(0);
	}

	public void cleanup() {
		discardPile.addAll(playedCard);
		playedCard.clear();
		draw(5);
	}
	
	public void start()
	{
	}
}
