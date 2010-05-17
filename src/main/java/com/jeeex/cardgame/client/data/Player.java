package com.jeeex.cardgame.client.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String name;

	List<Card> hand;
	List<Card> deck;
	List<Card> discardPile;
	List<Card> playedCard;

	public Player() {
		hand = new ArrayList<Card>();
		deck = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
		playedCard = new ArrayList<Card>();
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
				return false; // draw failed.
			deck.addAll(discardPile);
			discardPile.clear();
			Collections.shuffle(deck);
		}
		hand.add(takeFromTop());
		return true;
	}

	public Card takeFromTop() {
		return deck.remove(0);
	}

	public void cleanup() {
	}

	public void start() {
	}

	public void addToBuy(Card card) {
		discardPile.add(card);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("name:    ").append(name);
		sb.append('\n');
		sb.append("hand:    ").append(Util.aggregate(hand).values());
		sb.append('\n');
		sb.append("play:    ").append(playedCard);
		sb.append('\n');
		sb.append("deck:    ").append(deck);
		sb.append('\n');
		sb.append("discard: ").append(Util.aggregate(discardPile).values());
		sb.append('\n');
		return sb.toString();
	}
}
