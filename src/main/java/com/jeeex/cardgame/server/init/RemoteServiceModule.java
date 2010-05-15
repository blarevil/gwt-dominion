package com.jeeex.cardgame.server.init;

import com.google.inject.AbstractModule;
import com.jeeex.cardgame.server.remote.MessageServiceImpl;
import com.jeeex.cardgame.shared.remote.MessageService;

public class RemoteServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MessageService.class).to(MessageServiceImpl.class);
	}

}
