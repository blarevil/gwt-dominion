package com.jeeex.cardgame.server.init;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class TestPersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
	}
	
	@Provides
	public EntityManagerFactory getEMF() {
		return Persistence.createEntityManagerFactory("testdb");
	}

	@Provides
	public EntityManager getEntityManager(EntityManagerFactory emf) {
		return emf.createEntityManager();
	}
}
