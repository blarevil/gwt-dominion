package com.jeeex.cardgame.client.ui.lobby;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.ChatPresenter;
import com.jeeex.cardgame.client.ui.generic.Presenter;
import com.jeeex.cardgame.client.ui.widget.GameListPresenter;

public class LobbyPresenter implements Presenter<LobbyView> {
	/** Underlying view. */
	final LobbyView view;

	private final ChatPresenter chatPresenter;
	
	private final LobbyMenuPresenter menuPresenter;

	private final MyEventBus ebus;

	private final GameListPresenter gameListPresenter;

	@Inject
	public LobbyPresenter(
			// ebus
			MyEventBus ebus,
			// views
			LobbyView view,
			// presenters
			ChatPresenter chatPresenter,
			LobbyMenuPresenter menuPresenter,
			GameListPresenter gameListPresenter) {
		this.view = view;
		this.chatPresenter = chatPresenter;
		this.menuPresenter = menuPresenter;
		this.gameListPresenter = gameListPresenter;
		this.ebus = ebus;
	}

	@Override
	public LobbyView getView() {
		return view;
	}

	/**
	 * This should be refactored. <br>
	 * 1. all the handlers to menu buttons should be refactored into another view.
	 * */
	@Override
	public void init() {
		// cascade-init presenters.
		chatPresenter.init();
		gameListPresenter.init();
		// TODO(Jeeyoung Kim) Figure out how to use assisted inject.
		menuPresenter.setGameListPresenter(gameListPresenter);
		menuPresenter.init();

		// initialize view.
		// should this be done via eventBus?
		view.setChatView(chatPresenter.getView());
		view.init();

		// wire event bus to mutate view.
		ebus.onSetCenterWidget(new GenericHandler<Widget>() {
			@Override
			public void onEvent(Widget wgt) {
				view.setCenterWidget(wgt);
			}
		});
		ebus.onSetMenuWidget(new GenericHandler<Widget>() {
			@Override
			public void onEvent(Widget wgt) {
				view.setMenuWidget(wgt);
			}
		});

		ebus.setCenterWidget(gameListPresenter.getView());
		ebus.setMenuWidget(menuPresenter.getView());
	}
}
