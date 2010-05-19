package com.jeeex.cardgame.shared.remote.entity;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.jeeex.cardgame.server.init.TestPersistenceModule;

public class GenerateTestData implements Runnable {
	public static void main(String[] args) {
		Guice.createInjector(new TestPersistenceModule()).getInstance(
				GenerateTestData.class).run();
	}

	@Inject
	EntityManager em;

	@Override
	public void run() {
		try {
			em.getTransaction().begin();
			String[] names = { "Hello", "NOOB ONLY", "world" };
			for (String name : names) {
				GameRoom gr = new GameRoom();
				gr.setName(name);
				em.persist(gr);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
}
