package com.jeeex.cardgame.server.init;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.server.remote.LobbyServiceImpl;
import com.jeeex.cardgame.server.remote.MessageServiceImpl;
import com.jeeex.cardgame.server.remote.UserServiceImpl;
import com.jeeex.cardgame.shared.remote.lobby.LobbyService;
import com.jeeex.cardgame.shared.remote.message.MessageService;
import com.jeeex.cardgame.shared.remote.user.UserService;

/**
 * Binds all the {@link RemoteService} stubs to their implementation.
 * <p>
 * If the RemoteService implementation uses EntityManager, it should be request
 * scoped (either by explicitly stating the scope in this file, or using
 * {@link RequestScoped} annotation).
 * 
 * @author Jeeyoung Kim
 * @since 2010-05-25
 */
public class RemoteServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(MessageService.class).to(MessageServiceImpl.class);
		bind(LobbyService.class).to(LobbyServiceImpl.class);
		bind(UserService.class).to(UserServiceImpl.class);
	}
}
