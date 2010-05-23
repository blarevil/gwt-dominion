package com.jeeex.cardgame.shared.entity;

/** Marks objects that can be "cleaned" for GWT serialization. */
public interface Cleanable {
	// Honestly, I really want to use Gilead instead of this to serialize my
	// objects.
	public void clean();
}
