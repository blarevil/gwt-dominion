package com.jeeex.cardgame.client.data;

/** An immutable data structure, representing the card. */
public class CardImpl implements Card {
	public CardImpl(String name, String description, int cost) {
		assert cost >= 0;
		assert name != null;
		assert description != null;
		this.name = name;
		this.description = description;
		this.cost = cost;
	}

	private final String name;

	/** Description. */
	private final String description;

	/** Cost of the card. */
	private final int cost;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	/** Returns false. other cards should override this method. */
	public boolean isAction() {
		return false;
	}

	/**
	 * Returns false. other cards should override this method.
	 * <p>
	 * If this value is true, then this class is an implementation of
	 * {@link TreasureCard}.
	 */
	public boolean isTreasure() {
		return false;
	}

	/** Returns false. other cards should override this method. */
	public boolean isVictoryPoint() {
		return false;
	}

	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardImpl other = (CardImpl) obj;
		if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int getCost() {
		return cost;
	}
}
