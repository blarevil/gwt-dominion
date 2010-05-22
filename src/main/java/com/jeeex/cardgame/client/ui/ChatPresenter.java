package com.jeeex.cardgame.client.ui;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.generic.Presenter;
import com.jeeex.cardgame.client.util.EmptyCallback;
import com.jeeex.cardgame.shared.remote.message.MessageServiceAsync;
import com.jeeex.cardgame.shared.remote.message.SendChatMessageRequest;
import com.jeeex.cardgame.shared.remote.message.SendChatMessageResponse;

public class ChatPresenter implements Presenter<ChatView> {

	@Inject
	public ChatPresenter(ChatView view, MyEventBus ebus,
			MessageServiceAsync msgSvc) {
		this.view = view;
		this.ebus = ebus;
		this.msgSvc = msgSvc;
	}

	@Inject
	private AuthTokenManager tknMgr;
	private final MessageServiceAsync msgSvc;
	private final ChatView view;
	private final MyEventBus ebus;

	@Override
	public ChatView getView() {
		return view;
	}

	@Override
	public void init() {
		view.getChatBox().addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					event.preventDefault();
					String msg = view.getChatBox().getText();
					view.getChatBox().setText("");
					SendChatMessageRequest req = new SendChatMessageRequest();
					req.setAuthToken(tknMgr.get());
					msgSvc.sendChatMessage(req,
							new EmptyCallback<SendChatMessageResponse>());
				}
			}
		});
	}
}
