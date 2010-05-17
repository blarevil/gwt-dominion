package com.jeeex.cardgame.server.init;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {

	private Injector injector;

	@Override
	protected Injector getInjector() {
		injector = Guice.createInjector(
				new UrlModule(),
				new RemoteServiceModule(),
				new PersistenceModule());
		return injector;
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		super.contextDestroyed(servletContextEvent);
		// TODO(Jeeyoung Kim) - refactor the teardown methods.
		injector.getInstance(EntityManagerFactory.class).close();
	}
}
