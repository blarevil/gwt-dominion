package com.jeeex.cardgame.client.data;

public interface VictoryCard extends Card{
	public int victoryPoint();
	
	public static class ConcreteVictoryPointCard extends CardImpl implements VictoryCard{

		private int vp;

		public ConcreteVictoryPointCard(String name,
				int cost,
				int vp) {
			super(name, "", cost);
			this.vp = vp;
		}

		@Override
		public int victoryPoint() {
			return vp;
		}

		@Override
		public boolean isVictoryPoint() {
			return true;
		}
	}
}
