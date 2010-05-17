package com.jeeex.cardgame.server.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class EntityManagerProvider implements Provider<EntityManager> {
	@Inject EntityManagerFactory emf;
	@Override
	public EntityManager get() {
		return emf.createEntityManager();
	}
}
