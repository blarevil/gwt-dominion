package com.jeeex.cardgame.cli;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Scanner;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.jeeex.cardgame.cli.Command.BuyCommand;
import com.jeeex.cardgame.client.data.CardConstants;

public class CommandLineInterface implements Runnable {

	@Inject
	GameLogic gl;

	public static void main(String[] args) {
		Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(GameState.class).toProvider(GameStateFactory.class);
			}
		}).getInstance(CommandLineInterface.class).run();
	}

	private Scanner s;

	@Override
	public void run() {
		GameState gs = gl.getGameState();
		s = new Scanner(System.in);
		println(gs);
		while (true) {
			println("Enter commands:");
			String input = s.nextLine();
			if (input.equals("exit")) {
				println("exit - terminating.");
				return;
			} else if (input.equals("run")) {
				println("running");
				gl.run(null);
			} else if (input.startsWith("action")) {
				println("Running action.");
				dispatchAction(input);
			} else if (input.equals("show")) {
				println(gs);
			} else if (input.equals("show curplayer")) {
				println(gs.getCurrentPlayer());
			} else {
				println("Unrecognized command [" + input + "].");
			}
		}
	}

	private void dispatchAction(String input) {
		String[] tkn = input.split(" ");
		// first token is always "action"
		checkArgument(tkn[0].equals("action"));
		// second token denotes the source user.
		String user = tkn[1];
		// third token denotes the action
		if (tkn[2].equals("buy")) {
			println("BUY action");
			String target = tkn[3];
			BuyCommand bcmd = new BuyCommand(Long.valueOf(user),
					CardConstants.NAME_TO_CARD.get(target));
			gl.run(bcmd);
		}
	}

	public void println(Object obj) {
		System.out.println(String.valueOf(obj));
	}
}
