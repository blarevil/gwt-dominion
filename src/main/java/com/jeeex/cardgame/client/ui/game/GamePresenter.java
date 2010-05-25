package com.jeeex.cardgame.client.ui.game;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jeeex.cardgame.client.data.model.AuthTokenManager;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.generic.Presenter;
import com.jeeex.cardgame.client.ui.widget.GameListPresenter;
import com.jeeex.cardgame.client.util.BaseCallback;
import com.jeeex.cardgame.shared.remote.lobby.LeaveGameRequest;
import com.jeeex.cardgame.shared.remote.lobby.LeaveGameResponse;
import com.jeeex.cardgame.shared.remote.lobby.LobbyServiceAsync;

@Singleton
public class GamePresenter implements Presenter<GameView> {

	@Inject
	private GameView view;

	@Inject
	private MyEventBus ebus;
	
	@Inject
	private LobbyServiceAsync lobbySvc;
	
	@Inject
	private AuthTokenManager tknMgr;

	// not injected, to prevent circular dependency.
	private GameListPresenter glPresenter;

	@Inject
	public GamePresenter() {
	}

	@Override
	public GameView getView() {
		return view;
	}

	@Override
	public void init() {
		// preconditions.
		assert glPresenter != null;

		view.getLeaveButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				LeaveGameRequest req = new LeaveGameRequest();
				req.setAuthToken(tknMgr.get());
				lobbySvc.leaveGame(req, new BaseCallback<LeaveGameResponse>());
				ebus.setCenterWidget(glPresenter.getView());
			}
		});
	}

	public void setGameListPresenter(GameListPresenter glp) {
		glPresenter = glp;
	}
}
