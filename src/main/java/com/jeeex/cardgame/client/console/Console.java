package com.jeeex.cardgame.client.console;

import com.google.inject.Inject;
import com.jeeex.cardgame.client.ui.HandPanel;

/**
 * Class to add random method calls to test functionality of the widgets.
 * <p>
 * There's probably a better, non-hacky way to achieve the same thing.
 */
public class Console {

	private final HandPanel handPanel;

	@Inject
	public Console(HandPanel panel) {
		this.handPanel = panel;
	}

	public void exec(String command) {
		if (command.equals("add")) {
			handPanel.addCard();
		} else if (command.equals("remove")) {
			handPanel.removeCard();
		}
	}
}
