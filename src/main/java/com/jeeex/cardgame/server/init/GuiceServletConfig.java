package com.jeeex.cardgame.server.init;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new UrlModule(), new RemoteServiceModule(),
				new PersistenceModule());
	}
}
