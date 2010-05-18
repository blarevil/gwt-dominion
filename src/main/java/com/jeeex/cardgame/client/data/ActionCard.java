package com.jeeex.cardgame.client.data;

public interface ActionCard extends Card {

	/** Action being executed. Action cards should override this method. */
	public void action();

	public int additionalAction();

	public int additionalBuy();

	public int additionalCards();

	public int additionalGold();
}
