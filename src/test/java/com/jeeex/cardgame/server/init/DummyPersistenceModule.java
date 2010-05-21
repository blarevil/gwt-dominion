package com.jeeex.cardgame.server.init;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.aopalliance.intercept.MethodInterceptor;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.jeeex.cardgame.server.aop.UnderTransaction;
import com.jeeex.cardgame.server.aop.UnderTransactionImpl;

/**
 * Both {@link EntityManager} and {@link EntityManagerFactory} are bound as
 * singletons. <br>
 * TODO(Jeeyoung Kim): We don't have use to use MySQL all the time, especially
 * when the test doesn't require it...
 */
public class DummyPersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		MethodInterceptor underTransaction = new UnderTransactionImpl();
		bindInterceptor(Matchers.any(), Matchers
				.annotatedWith(UnderTransaction.class), underTransaction);
		requestInjection(underTransaction);
	}

	@Provides
	@Singleton
	public EntityManagerFactory getEMF() {
		return Persistence.createEntityManagerFactory("testdb");
	}

	@Provides
	@Singleton
	public EntityManager getEntityManager(EntityManagerFactory emf) {
		return emf.createEntityManager();
	}
}
