package com.jeeex.cardgame.cli.event;

import com.jeeex.cardgame.cli.GameState;
import com.jeeex.cardgame.cli.Player;
import com.jeeex.cardgame.cli.GameLogic.GSEvent;

public class CleanupEvent implements GSEvent {

	private long pid;

	public CleanupEvent(long pid) {
		this.pid = pid;
	}

	@Override
	public void update(GameState gs) {
		// to cleanup.
		// 0. empty out buy / action / etc.
		// 1. hand to discard.
		// 2. play to discard.
		Player p = gs.getPlayer(pid);
		p.setActionCount(0);
		p.setBuyCount(0);
		p.setGold(0);
		p.getDiscardPile().addAll(p.getPlayedCard());
		p.getDiscardPile().addAll(p.getHand());
		p.getPlayedCard().clear();
		p.getHand().clear();
	}
}
