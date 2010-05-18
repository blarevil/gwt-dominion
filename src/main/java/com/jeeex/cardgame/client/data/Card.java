package com.jeeex.cardgame.client.data;

public interface Card {

	/**
	 * Name of the card.
	 * <p>
	 * This is used to uniquely identify the card objects. This value is never
	 * null.
	 */
	public abstract String getName();

	/** Returns false. other cards should override this method. */
	public abstract boolean isAction();

	/**
	 * Returns false. other cards should override this method.
	 * <p>
	 * If this value is true, then this class is an implementation of
	 * {@link TreasureCard}.
	 */
	public abstract boolean isTreasure();

	/** Returns false. other cards should override this method. */
	public abstract boolean isVictoryPoint();

	/** Returns the cost of the card. */
	public int getCost();

	/**
	 * HashCode of {@link #getName()}.
	 */
	@Override
	public int hashCode();
}