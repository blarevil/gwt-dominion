package com.jeeex.cardgame.shared.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.google.gwt.user.client.rpc.IsSerializable;

@Entity
public class GameRoom implements IsSerializable {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToOne(optional = false, fetch = FetchType.EAGER)
	private User createdBy;

	// is this relation 1-many? figure out if i'm right...
	@OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
	private Set<User> participating = new HashSet<User>();

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

	public void addParticipating(User user) {
		participating.add(user);
	}

	/** This is fucking nuts. find better way to do this.*/
	public void sanitizeForGwt() {
		participating = new HashSet<User>(participating);
	}
}
