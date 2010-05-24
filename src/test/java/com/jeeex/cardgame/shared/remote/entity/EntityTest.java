package com.jeeex.cardgame.shared.remote.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.jeeex.cardgame.server.init.DummyPersistenceModule;

public class EntityTest {

	@Inject
	EntityManager em;
	
	@Inject
	EntityManagerFactory emf;

	@Before
	public void init() {
		Guice.createInjector(new DummyPersistenceModule()).injectMembers(this);
	}

	@Test
	public void test() {
		// do nothing.
	}

	@After
	public void cleanup() {
		em.close();
		emf.close();
	}
}
