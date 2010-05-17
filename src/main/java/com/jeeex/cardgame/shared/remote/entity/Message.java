package com.jeeex.cardgame.shared.remote.entity;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import com.google.gwt.user.client.rpc.IsSerializable;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Message implements IsSerializable {
	@Id @GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
