package com.jeeex.cardgame.client.ui;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.TextArea;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.data.model.HandlerEndpoint;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.chat.ChatEvent;
import com.jeeex.cardgame.client.ui.chat.ChatHandler;
import com.jeeex.cardgame.client.ui.generic.Presenter;

public class MainPresenter implements Presenter<MainView> {

	private MainView view;
	private MyEventBus ebus;

	private HandlerManager mgr = new HandlerManager(this);

	@Inject
	public MainPresenter(MainView view, MyEventBus ebus) {
		this.view = view;
		this.ebus = ebus;
	}

	public void init() {
		ebus.onPrintln(new GenericHandler<String>() {
			@Override
			public void onEvent(String event) {
				TextArea wgt = view.getChatHistoryArea();
				String txt = wgt.getText();
				wgt.setText(txt + event + '\n');
			}
		});

		// initialze chat endpoint
		view.getChatMessageArea().addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					mgr.fireEvent(new ChatEvent(view.getChatMessageArea()
							.getText()));
					event.preventDefault();
				}
			}
		});

		chatEndpoint().addHandler(new ChatHandler() {
			@Override
			public void onChatEvent(ChatEvent event) {
				getView().getChatMessageArea().setText("");
			}
		});

		chatEndpoint().addHandler(new ChatHandler() {
			@Override
			public void onChatEvent(ChatEvent event) {
				ebus.println(event.getChatMessage());
			}
		});
	}

	public MainView getView() {
		return view;
	}

	public HandlerEndpoint<ChatHandler> chatEndpoint() {
		return new HandlerEndpoint<ChatHandler>(mgr, ChatEvent.TYPE);
	}
}
