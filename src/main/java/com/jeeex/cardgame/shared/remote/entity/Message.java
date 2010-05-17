package com.jeeex.cardgame.shared.remote.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
	@Id
	@GeneratedValue
	Long id;
}
