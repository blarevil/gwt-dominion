package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.ui.generic.Presenter;
import com.jeeex.cardgame.client.util.EmptyCallback;
import com.jeeex.cardgame.shared.remote.entity.GameRoom;
import com.jeeex.cardgame.shared.remote.lobby.GetGameListRequest;
import com.jeeex.cardgame.shared.remote.lobby.GetGameListResponse;
import com.jeeex.cardgame.shared.remote.lobby.LobbyServiceAsync;

public class LobbyPresenter implements Presenter<LobbyView> {

	LobbyView view;

	LobbyServiceAsync lobbySvc;

	@Inject
	public LobbyPresenter(LobbyView view,
			LobbyServiceAsync lobbySvc) {
		this.view = view;
		this.lobbySvc = lobbySvc;
	}

	@Override
	public LobbyView getView() {
		return view;
	}

	@Override
	public void init() {
		lobbySvc.getGameList(new GetGameListRequest(),
				new EmptyCallback<GetGameListResponse>() {
					@Override
					public void onSuccess(GetGameListResponse result) {
						for (GameRoom gr : result.getRooms()) {
							view.gamelist.add(new Label(gr.getName()));
						}
					}
				});
	}
}
