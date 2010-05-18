package com.jeeex.cardgame.cli;

import net.jcip.annotations.Immutable;

import com.jeeex.cardgame.client.data.Card;

/**
 * This class itself is immutable, but it's not declared final. All subclasses
 * should preserve immutability.
 */
@Immutable
public class Command {

	/** Command issued to "buy" the card. */
	@Immutable
	public static class BuyCommand extends Command {
		private final Card card;

		public BuyCommand(long pid, Card boughtCard) {
			super(pid);
			this.card = boughtCard;
		}

		public Card getCard() {
			return card;
		}
	}

	private final long pid;

	public long getSourcePlayerId() {
		return pid;
	}

	public Command(long pid) {
		this.pid = pid;
	}

}
