package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jeeex.cardgame.client.data.model.Binded;
import com.jeeex.cardgame.client.data.model.UserState;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.generic.Presenter;

@Singleton
public class WidgetTool {
	@Inject
	private MyEventBus ebus;

	@Inject
	private Binded<UserState> var;

	@Inject
	private WidgetTool() {
	}

	public void showMenuOnState(final UserState state,
			final Presenter<? extends Widget> presenter) {
		var.addHandler(new GenericHandler<UserState>() {
			@Override
			public void onEvent(UserState event) {
				if (event == state) {
					ebus.setMenuWidget(presenter.getView());
				}
			}
		});
	}

	public void showCenterWidgetOnState(final UserState state,
			final Presenter<? extends Widget> presenter) {
		var.addHandler(new GenericHandler<UserState>() {
			@Override
			public void onEvent(UserState event) {
				if (event == state) {
					ebus.setCenterWidget(presenter.getView());
				}
			}
		});
	}
}
