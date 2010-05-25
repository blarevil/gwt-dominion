package com.jeeex.cardgame.client.init;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.jeeex.cardgame.client.data.model.Binded;
import com.jeeex.cardgame.client.data.model.UserState;
import com.jeeex.cardgame.client.res.DefaultResource;
import com.jeeex.cardgame.client.res.DefaultResource.GameListStyle;
import com.jeeex.cardgame.client.res.DefaultResource.MyStyle;
import com.jeeex.cardgame.client.ui.MainView;
import com.jeeex.cardgame.shared.entity.AuthToken;

public class ClientModule extends AbstractGinModule {

	private static final Class<Singleton> SINGLETON = Singleton.class;

	@Override
	protected void configure() {
		bind(MainView.class).in(SINGLETON);
		bind(DefaultResource.class).in(SINGLETON);
		bind(new TypeLiteral<Binded<UserState>>(){}).in(SINGLETON);
		bind(new TypeLiteral<Binded<AuthToken>>(){}).in(SINGLETON);
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
