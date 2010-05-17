package com.jeeex.cardgame.client.data;

public interface Card {

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

}