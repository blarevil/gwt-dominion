package com.jeeex.cardgame.cli;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

import com.jeeex.cardgame.client.data.Card;
import com.jeeex.cardgame.client.data.Util;

public class Player {
	/** Name of the player. */
	String name;

	/** User id of the player. */
	long userid;

	List<Card> hand;

	List<Card> deck;

	List<Card> discardPile;

	/** Cards currently played this round. */
	List<Card> playedCard;

	/** Number of actions this player can play. */
	private int actionCount = 0;
	/** Number of buys this player can play. */
	private int buyCount = 0;

	private int gold = 0;

	public Player() {
		hand = new ArrayList<Card>();
		deck = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
		playedCard = new ArrayList<Card>();
	}

	public void decrementAction() {
		checkState(actionCount > 0);
		actionCount--;
	}

	public void decrementBuy() {
		checkState(buyCount > 0);
		buyCount--;
	}

	public void decrementGold(int amount) {
		checkState(gold >= 0);
		checkArgument(gold - amount >= 0);
		gold -= amount;
	}

	public int getActionCount() {
		return actionCount;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public int getGold() {
		return gold;
	}

	public String getName() {
		return name;
	}

	public long getUserid() {
		return userid;
	}

	public boolean hasAction() {
		return actionCount > 0;
	}

	public boolean hasBuy() {
		return buyCount > 0;
	}

	public boolean isDeckEmpty() {
		return deck.isEmpty();
	}

	public void setActionCount(int actionCount) {
		checkArgument(actionCount >= 0);
		this.actionCount = actionCount;
	}

	public void setBuyCount(int buyCount) {
		checkArgument(buyCount >= 0);
		this.buyCount = buyCount;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	/** For debugging. */
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("user:        ").append(name + ":" + userid);
		sb.append('\n');
		sb.append("Act/Buy/Gold:").append(
				"(" + actionCount + "," + buyCount + "," + gold + ")");
		sb.append('\n');
		sb.append("hand:        ").append(
				Util.aggregate(hand).values() + ":" + hand.size());
		sb.append('\n');
		sb.append("play:        ").append(playedCard + ":" + playedCard.size());
		sb.append('\n');
		sb.append("deck:        ").append(deck + ":" + deck.size());
		sb.append('\n');
		sb.append("discard:     ")
				.append(
						Util.aggregate(discardPile).values() + ":"
								+ discardPile.size());
		sb.append('\n');
		return sb.toString();
	}

	public List<Card> getHand() {
		return hand;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public List<Card> getDiscardPile() {
		return discardPile;
	}

	public List<Card> getPlayedCard() {
		return playedCard;
	}
}
