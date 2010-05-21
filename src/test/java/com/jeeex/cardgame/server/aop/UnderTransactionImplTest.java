package com.jeeex.cardgame.server.aop;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.jeeex.cardgame.server.init.DummyPersistenceModule;

public class UnderTransactionImplTest {

	public static class DummyClass {
		@UnderTransaction
		public void underTransaction() {
			// no-op method.
		}
	}

	@Inject
	DummyClass dummy;

	@Inject
	EntityManager em;
	@Inject
	EntityManagerFactory emf;

	@Before
	public void init() {
		Guice.createInjector(new DummyPersistenceModule()).injectMembers(this);
	}

	@Test
	public void testFailTransaction() {
		try {
			dummy.underTransaction();
		} catch (AssertionError error) {
			assertEquals("Transaction is not active.", error.getMessage());
		}
	}

	@Test
	public void testPass() {
		em.getTransaction().begin();
		dummy.underTransaction();
	}

	@Test
	public void testFailEntityClosed() {
		em.close();
		try {
			dummy.underTransaction();
		} catch (AssertionError error) {
			assertEquals("EntityManager is closed.", error.getMessage());
		}
	}

	@After
	public void cleanup() {
		if (em.isOpen()) {
			em.close();
		}
		emf.close();
	}
}
