package com.jeeex.cardgame.client.ui;

import com.google.inject.Inject;
import com.jeeex.cardgame.client.data.model.HandlerEndpoint;
import com.jeeex.cardgame.client.ui.chat.ChatEvent;
import com.jeeex.cardgame.client.ui.chat.ChatHandler;
import com.jeeex.cardgame.client.ui.generic.Presenter;

public class MainPresenter implements Presenter<Object> {

	private MainView view;

	@Inject
	public MainPresenter(MainView view) {
		this.view = view;
	}

	public void init() {
		view.chatEndpoint().addHandler(new ChatHandler() {
			@Override
			public void onChatEvent(ChatEvent event) {
				view.getChatMessage().setText("");
			}
		});
	}

	public MainView getView() {
		return view;
	}

	public HandlerEndpoint<ChatHandler> chatEndpoint() {
		return view.chatEndpoint();
	}
}
