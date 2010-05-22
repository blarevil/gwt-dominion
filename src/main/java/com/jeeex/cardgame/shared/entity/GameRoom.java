package com.jeeex.cardgame.shared.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.google.gwt.user.client.rpc.IsSerializable;

@Entity
public class GameRoom implements IsSerializable {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToOne(optional = false, fetch=FetchType.EAGER)
	private User createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
}
