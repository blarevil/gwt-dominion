package com.jeeex.cardgame.client.init;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.jeeex.cardgame.client.res.DefaultResource;
import com.jeeex.cardgame.client.res.DefaultResource.GameListStyle;
import com.jeeex.cardgame.client.res.DefaultResource.MyStyle;
import com.jeeex.cardgame.client.ui.AuthTokenManager;
import com.jeeex.cardgame.client.ui.MainView;

public class ClientModule extends AbstractGinModule {

	private static final Class<Singleton> SINGLETON = Singleton.class;

	@Override
	protected void configure() {
		bind(MainView.class).in(Singleton.class);
		bind(DefaultResource.class).in(SINGLETON);
		bind(AuthTokenManager.class).in(SINGLETON);
	}

	@Provides
	public GameListStyle getGameListStyle(DefaultResource res) {
		return res.gameListStyle();
	}

	@Provides
	public MyStyle getMyStyle(DefaultResource res) {
		return res.style();
	}

	@Provides
	public HandlerManager getDefaultManager() {
		return new HandlerManager(null);
	}
}
