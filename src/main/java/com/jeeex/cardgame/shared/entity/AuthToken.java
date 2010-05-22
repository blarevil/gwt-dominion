package com.jeeex.cardgame.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Represents an instance of "authentication".
 */
@Entity
public class AuthToken implements IsSerializable {

	@Id
	@GeneratedValue
	@GwtTransient
	Long id;

	String tokenName;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user", referencedColumnName = "id")
	@GwtTransient
	User user;

	/** For GWT. */
	@Column(name = "user", insertable = false, updatable = false)
	Long userId;

	public AuthToken() {
	}

	public Long getId() {
		return id;
	}

	public String getTokenName() {
		return tokenName;
	}

	public User getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public void setUser(User user) {
		if (user.getId() != null) {
			this.userId = user.getId();
		}
		this.user = user;
	}

	public String toString() {
		return tokenName + ":" + userId;
	}

	public Long getUserId() {
		return userId;
	}
}
