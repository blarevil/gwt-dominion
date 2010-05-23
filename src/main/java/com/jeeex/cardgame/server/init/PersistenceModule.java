package com.jeeex.cardgame.server.init;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.server.entitymanager.EntityManagerProvider;

public class PersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EntityManager.class).toProvider(EntityManagerProvider.class).in(
				RequestScoped.class);
		// disable it for now.
		/*
		MethodInterceptor underTransaction = new UnderTransactionImpl();
		bindInterceptor(Matchers.any(), Matchers
				.annotatedWith(UnderTransaction.class), underTransaction);
		requestInjection(underTransaction);
		*/
	}

	@Provides
	@Singleton
	public EntityManagerFactory getEMF() {
		return Persistence.createEntityManagerFactory("testdb");
	}
}
