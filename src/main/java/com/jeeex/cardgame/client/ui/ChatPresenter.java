package com.jeeex.cardgame.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.generic.Presenter;
import com.jeeex.cardgame.client.util.EmptyCallback;
import com.jeeex.cardgame.shared.entity.ChatMessage;
import com.jeeex.cardgame.shared.entity.Message;
import com.jeeex.cardgame.shared.remote.message.MessageServiceAsync;
import com.jeeex.cardgame.shared.remote.message.SendChatMessageRequest;
import com.jeeex.cardgame.shared.remote.message.SendChatMessageResponse;

public class ChatPresenter implements Presenter<ChatView> {

	@Inject
	public ChatPresenter() {
	}

	@Inject
	private AuthTokenManager tknMgr;
	@Inject
	private MessageServiceAsync msgSvc;
	@Inject
	private ChatView view;
	@Inject
	private MyEventBus ebus;

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
					
					// generate and send request.
					SendChatMessageRequest req = new SendChatMessageRequest();
					req.setMessage(msg);
					req.setAuthToken(tknMgr.get());
					msgSvc.sendChatMessage(req,
							new EmptyCallback<SendChatMessageResponse>());
				}
			}
		});

		ebus.onPrintln(new GenericHandler<String>() {

			@Override
			public void onEvent(String msg) {
				String output = view.getChatHistory().getText() + msg + '\n';
				view.getChatHistory().setText(output);
			}
		});

		ebus.onMessageReceived(new GenericHandler<List<Message>>() {

			@Override
			public void onEvent(List<Message> lists) {
				for (Message msg : lists) {
					if (msg instanceof ChatMessage) {
						ebus.println(((ChatMessage)msg).getMessage());
					}
				}
			}
		});
	}
}
