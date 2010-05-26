package com.jeeex.cardgame.client.ui.lobby;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.chat.ChatPresenter;
import com.jeeex.cardgame.client.ui.generic.Presenter;

public class LobbyPresenter implements Presenter<LobbyView> {
	/** Underlying view. */
	final LobbyView view;

	private final ChatPresenter chatPresenter;

	private final MyEventBus ebus;

	@Inject
	public LobbyPresenter(
			// ebus
			MyEventBus ebus,
			// views
			LobbyView view,
			// presenters
			ChatPresenter chatPresenter) {
		this.view = view;
		this.chatPresenter = chatPresenter;
		this.ebus = ebus;
	}

	@Override
	public LobbyView getView() {
		return view;
	}

	/**
	 * This should be refactored. <br>
	 * 1. all the handlers to menu buttons should be refactored into another view.
	 * 2. no nested presenters - no presenters should be injected.
	 * */
	@Override
	public void init() {
		// cascade-init presenters.
		chatPresenter.init();

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
	}
}
