package com.jeeex.cardgame.shared.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.google.gwt.user.client.rpc.IsSerializable;

@Entity
public class User implements IsSerializable {

	@Id
	@GeneratedValue
	Long id;

	@Column(unique = true)
	String username;

	@GwtTransient
	@ManyToMany
	@JoinTable(name = "User_GameRoom", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "GAMEROOM_ID"))
	List<GameRoom> joinedRooms = new ArrayList<GameRoom>();

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String toString() {
		return username;
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public List<GameRoom> getJoinedRooms() {
		return joinedRooms;
	}

	public void setJoinedRoom(List<GameRoom> room) {
		this.joinedRooms = room;
	}

	public void joinGame(GameRoom room) {
		joinedRooms.add(room);
	}
}
