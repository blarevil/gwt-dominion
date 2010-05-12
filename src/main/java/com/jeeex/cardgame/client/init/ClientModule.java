package com.jeeex.cardgame.client.init;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.jeeex.cardgame.client.res.DefaultResource;
import com.jeeex.cardgame.client.res.DefaultResource.Style;
import com.jeeex.cardgame.client.ui.MainPanel;

public class ClientModule extends AbstractGinModule {

	private static final Class<Singleton> SINGLETON = Singleton.class;

	@Override
	protected void configure() {
		bind(MainPanel.class).in(Singleton.class);
		bind(DefaultResource.class).in(SINGLETON);
	}

	@Provides
	public Style getMyStyle(DefaultResource res) {
		return res.style();
	}
}
