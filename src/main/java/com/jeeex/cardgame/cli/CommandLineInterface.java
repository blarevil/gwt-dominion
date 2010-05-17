package com.jeeex.cardgame.cli;

import java.util.Scanner;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.data.GameState;
import com.jeeex.cardgame.client.data.GameStateFactory;

public class CommandLineInterface implements Runnable {

	@Inject
	GameState gs;

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
				gs.run();
				println("New game state:");
				println(gs);
			}
			// last resort.
			else {
				println("Unrecognized command [" + input + "].");
			}
		}

	}

	public void println(Object obj) {
		System.out.println(String.valueOf(obj));
	}
}
