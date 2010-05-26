package com.jeeex.cardgame.cli;

import static com.jeeex.cardgame.client.data.CardConstants.COPPER;
import static com.jeeex.cardgame.client.data.CardConstants.ESTATE;
import static java.text.MessageFormat.format;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.jeeex.cardgame.cli.Command.BuyCommand;
import com.jeeex.cardgame.cli.GameState.State;
import com.jeeex.cardgame.cli.event.CleanupEvent;
import com.jeeex.cardgame.client.data.Card;
import com.jeeex.cardgame.client.data.CardPile;
import com.jeeex.cardgame.client.data.TreasureCard;

/** Contains methods to mutate {@link GameState}. */
public class GameLogic {
	static class BeginTurnEvent implements GSEvent {
		private final long pid;

		public BeginTurnEvent(long pid) {
			this.pid = pid;
		}

		@Override
		public String toString() {
			return format("Player {0} begins his turn.", pid);
		}

		@Override
		public void update(GameState gs) {
			Player p = gs.getPlayer(pid);
			p.setActionCount(1);
			p.setBuyCount(1);
		}
	}

	/**
	 * Map of actions that get executed when you enter this state.
	 */
	Map<State, Runnable> stateEnteringActions;

	/**
	 * Map of actions that get executed when you exit this state.
	 */
	Map<State, Runnable> stateExitingActings;

	static class BuyEvent implements GSEvent {

		private final long pid;

		private final Card card;

		public BuyEvent(long pid, Card card) {
			this.pid = pid;
			this.card = card;
		}

		@Override
		public void update(GameState gs) {
			Player p = gs.getPlayer(pid);
			CardPile pile = gs.buyPile.get(card);
			pile.modifySize(-1);
			p.decrementGold(card.getCost());
			p.decrementBuy();
			p.discardPile.add(card);
		}
	}

	static class CoinToGoldEvent implements GSEvent {
		private int cardindex;

		public CoinToGoldEvent(int cardindex) {
			this.cardindex = cardindex;
		}

		@Override
		public void update(GameState gs) {
			Player p = gs.getCurrentPlayer();
			Card goldCard = p.hand.remove(cardindex);
			int goldValue = ((TreasureCard) goldCard).getGold();
			p.setGold(p.getGold() + goldValue);
			p.discardPile.add(goldCard);
		}
	}

	/** Represents dispatching copper/estate cards in the beginning of the game. */
	static class DistEvent implements GSEvent {
		private final long pid;

		private final Card card;

		public DistEvent(long playerId, Card card) {
			pid = playerId;
			this.card = card;
		}

		@Override
		public String toString() {
			return format("Player {0} gains a {1}.", pid, card.getName());
		}

		@Override
		public void update(GameState gs) {
			// init variables.
			CardPile from = gs.buyPile.get(card);
			Player player = gs.getPlayer(pid);

			// modify.
			from.modifySize(1);
			player.discardPile.add(from.getCard());
		}
	}

	/** Represents a player drawing a card. */
	static class DrawEvent implements GSEvent {

		private final long pid;

		private final boolean failed;

		public DrawEvent(long pid) {
			this.pid = pid;
			this.failed = false;
		}

		public DrawEvent(long pid, boolean failed) {
			this.pid = pid;
			this.failed = failed;
		}

		public String toString() {
			if (failed) {
				return format("Player {0} tries to draw, but he fails.", pid);
			}
			return format("Player {0} draws a card.", pid);
		}

		@Override
		public void update(GameState gs) {
			if (failed) {
				// TODO(Jeeyoung Kim) What do we do when a draw fails?
			} else {
				Player player = gs.getPlayer(pid);
				Card card = player.deck.remove(0);
				player.hand.add(card);
			}
		}

	}

	/**
	 * Events that mutates GameState. All Events must be very "primitive". That
	 * is, if it combines alot of actions inside a single event, then it should
	 * be partitioned into multiple events.
	 * */
	public interface GSEvent {
		public void update(GameState gs);
	}

	private static class ShuffleEvent implements GSEvent {
		private final long pid;

		public ShuffleEvent(long pid) {
			this.pid = pid;
		}

		@Override
		public String toString() {
			return format("Player {0} shuffles his deck.", pid);
		}

		@Override
		public void update(GameState gs) {
			Player player = gs.getPlayer(pid);
			player.deck.addAll(player.discardPile);
			player.discardPile.clear();
			Collections.shuffle(player.deck);
		}
	}

	@Inject
	Logger logger;

	/** Internal state. */
	private final GameState gs;

	{
		// Stuff that can occur.

		// 1. Buy (Add a card to the discard pile).
		// BuyEvent

		// 2. Cleanup (All the cards in play goes to discard).
		// CleanupEvent

		// 3. Shuffle (All the cards in discardPile goes to deck, shuffled).

		// 4. Draw (Move the top of the deck to hand).

		// 5. Play (Move a card from your hand to playedCard).

		// 6. Discard from hand.

		// 7. Trash a card (from hand / deck / blah).

		// 8. Look at top X card of the deck?
	}

	{
		/*
		 * How do I handle actions associated with change of state?
		 * 
		 * 1. Entering the state.
		 * 
		 * 2. Leaving the state.
		 */
	}

	@Inject
	public GameLogic(GameState gs) {
		this.gs = gs;
	}

	private void action(Command command) {

		// Transition to BUY.
		{
			// PERFORMS COIN TO GOLD.
			Player p = gs.getCurrentPlayer();
			for (int i = p.hand.size() - 1; i >= 0; i--) {
				Card c = p.hand.get(i);
				if (c.isTreasure()) {
					apply(new CoinToGoldEvent(i));
				}
			}
			gs.setState(State.BUY);
		}
	}

	public void apply(GSEvent event) {
		logger.info("EVENT:" + event.toString());
		event.update(gs);
	}

	public void begin(Command command) {
		apply(new BeginTurnEvent(gs.getCurrentPlayerId()));

		gs.setState(State.ACTION);
	}

	private void buy(Command command) {
		if (command instanceof BuyCommand) {
			BuyCommand bcmd = (BuyCommand) command;
			// TODO(Jeeyoung Kim) add checks on arguments.
			apply(new BuyEvent(gs.getCurrentPlayerId(), bcmd.getCard()));
		} else {
			gs.setState(State.CLEANUP);
		}
	}

	private void cleanup(Command command) {
		// to cleanup.
		// 1. hand to discard.
		// 2. play to discard.
		apply(new CleanupEvent(gs.getCurrentPlayerId()));

		// 3. draw 5 cards.
		for (int i = 0; i < 5; i++) {
			apply(new DrawEvent(gs.getCurrentPlayerId()));
		}

		gs.nextPlayer();
		gs.setState(State.BEGIN);
	}

	/** Given player draws a card. */
	public void drawCard(Player player) {
		if (player.isDeckEmpty() && !player.discardPile.isEmpty()) {
			// shuffle the deck.
			apply(new ShuffleEvent(player.getUserid()));
		}
		if (!player.isDeckEmpty())
			apply(new DrawEvent(player.getUserid()));
		// TODO(Jeeyoung Kim) What occurs when the deck is empty?
	}

	public GameState getGameState() {
		return gs;
	}

	private void init(Command command) {
		// deal the cards.
		for (Player player : gs.players) { // dealing cards.
			for (int i = 0; i < 7; i++) {
				apply(new DistEvent(player.getUserid(), COPPER));
			}
			for (int i = 0; i < 3; i++) {
				apply(new DistEvent(player.getUserid(), ESTATE));
			}
		}

		// Each player draws 5 card.
		for (Player player : gs.players) {
			for (int i = 0; i < 5; i++) {
				drawCard(player);
			}
		}

		gs.setState(State.BEGIN);
	}

	public void run(Command command) {
		// TODO(Jeeyoung Kim) Add check for command != null.
		// using null commands to "proceed to next state" is error prone.
		switch (gs.getState()) {
		case INIT:
			init(command);
			break;
		case BEGIN:
			begin(command);
			break;
		case ACTION:
			action(command);
			break;
		case BUY:
			buy(command);
			break;
		case CLEANUP:
			cleanup(command);
			break;
		case END:
			break;
		case ENDGAME:
			break;
		default:
			break;
		}
	}
}
