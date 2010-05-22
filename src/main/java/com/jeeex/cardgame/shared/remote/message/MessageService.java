package com.jeeex.cardgame.shared.remote.message;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jeeex.cardgame.shared.remote.InvalidTokenException;

@RemoteServiceRelativePath("gwt.rpc")
public interface MessageService extends RemoteService {

	public WaitForMessageResponse waitForMessage(WaitForMessageRequest request);

	public SendMessageResponse sendMessage(SendMessageRequest request);
	
	public SendChatMessageResponse sendChatMessage(
			SendChatMessageRequest request) throws InvalidTokenException;
}
