package com.jeeex.cardgame.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ChatMessage extends Message {
	@Column
	public String message;

	public ChatMessage() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
