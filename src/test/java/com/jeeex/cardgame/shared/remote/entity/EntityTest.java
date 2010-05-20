package com.jeeex.cardgame.shared.remote.entity;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.jeeex.cardgame.server.init.TestPersistenceModule;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.entity.User;

public class EntityTest {

	@Inject
	EntityManager em;

	@Before
	public void init() {
		Guice.createInjector(new TestPersistenceModule()).injectMembers(this);
	}

	@Test
	public void test() {
		AuthToken token = new AuthToken();
		token.setTokenName("tokenName");
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT user from User user where user.username = :name");
		q.setParameter("name", "foo");
		User user = (User) q.getSingleResult();
		token.setUser(user);
		em.persist(token);
		em.getTransaction().commit();
	}

	@After
	public void cleanup() {
		em.close();
	}
}
