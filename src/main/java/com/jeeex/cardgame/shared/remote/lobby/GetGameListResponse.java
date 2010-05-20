package com.jeeex.cardgame.shared.remote.lobby;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jeeex.cardgame.shared.entity.GameRoom;

public class GetGameListResponse implements IsSerializable {
	List<GameRoom> rooms;

	public GetGameListResponse() {
		// default constructor.
	}

	public List<GameRoom> getRooms() {
		return rooms;
	}

	public void setRooms(List<GameRoom> rooms) {
		this.rooms = rooms;
	}
}
