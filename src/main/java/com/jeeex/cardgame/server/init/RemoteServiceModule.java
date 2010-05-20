package com.jeeex.cardgame.server.init;

import com.google.inject.AbstractModule;
import com.jeeex.cardgame.server.remote.LobbyServiceImpl;
import com.jeeex.cardgame.server.remote.MessageServiceImpl;
import com.jeeex.cardgame.server.remote.UserServiceImpl;
import com.jeeex.cardgame.shared.remote.lobby.LobbyService;
import com.jeeex.cardgame.shared.remote.message.MessageService;
import com.jeeex.cardgame.shared.remote.user.UserService;

public class RemoteServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(MessageService.class).to(MessageServiceImpl.class);
		bind(LobbyService.class).to(LobbyServiceImpl.class);
		bind(UserService.class).to(UserServiceImpl.class);
	}
}
