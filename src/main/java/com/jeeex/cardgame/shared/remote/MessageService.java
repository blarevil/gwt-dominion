package com.jeeex.cardgame.shared.remote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwt.rpc")
public interface MessageService extends RemoteService {
	public WaitForMessageResponse waitForMessage(WaitForMessageRequest request);

	public SendMessageResponse sendMessage(SendMessageRequest request);
}
