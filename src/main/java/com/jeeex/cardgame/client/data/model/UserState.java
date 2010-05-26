package com.jeeex.cardgame.client.data.model;

public enum UserState {
	/**
	 * Logged out (in the lobby screen).
	 * <p>
	 * This sounds like a horrible idea - login state should not be included in
	 * {@link UserState}. But that means I should figure out what this class
	 * does firsi t...
	 * */
	IN_LOBBY,
	/** Inside the game, the game didn't start yet. */
	IN_GAME,
	/** Inside the game, the game is currently being played. */
	PLAYING_GAME,
}
