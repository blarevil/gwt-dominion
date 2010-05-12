package com.jeeex.cardgame.client.data;

/** An immutable data structure, representing the card. */
public class Card {
	/** Name of the card. */
	String name;
	/** Description. */
	String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
