package com.jeeex.cardgame.client.data;

public interface ActionCard extends Card {

	public void action();

	public int additionalAction();

	public int additionalBuy();

	public int additionalCards();

	public int additionalGold();

	public abstract class ConcreteActionCard extends CardImpl implements
			ActionCard {

		private final int action;
		private final int buy;
		private final int gold;
		private final int card;

		public ConcreteActionCard(String name, int cost) {
			this(name, cost, 0, 0, 0, 0);
		}

		public ConcreteActionCard(String name,
				int cost,
				int plusAction,
				int plusBuy,
				int plusCard,
				int plusGold) {
			super(name, "", cost);
			assert plusAction >= 0;
			assert plusBuy >= 0;
			assert plusCard >= 0;
			assert plusGold >= 0;
			action = plusAction;
			buy = plusBuy;
			card = plusCard;
			gold = plusGold;
		}

		@Override
		public abstract void action();

		@Override
		public int additionalAction() {
			return action;
		}

		@Override
		public int additionalBuy() {
			return buy;
		}

		@Override
		public int additionalCards() {
			return card;
		}

		@Override
		public int additionalGold() {
			return gold;
		}
	}
}
