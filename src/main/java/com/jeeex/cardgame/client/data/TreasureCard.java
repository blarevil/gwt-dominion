package com.jeeex.cardgame.client.data;

public interface TreasureCard extends Card{
	public int getGold();

	public static class ConcreteTreasureCard extends CardImpl implements
			TreasureCard {

		private final int goldValue;

		public ConcreteTreasureCard(String name,
				int cost,
				int goldValue) {
			super(name, "", cost);
			assert goldValue >= 0;
			this.goldValue = goldValue;
		}

		@Override public boolean isTreasure() { return true; }

		@Override
		public int getGold() {
			return goldValue;
		}
	}
}
