package com.jeeex.cardgame.server.aop;

import javax.persistence.EntityManager;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class UnderTransactionImpl implements MethodInterceptor {

	private Provider<EntityManager> emProvider;

	/**
	 * This <b>must</b> be a method injection, because MethodInterceptors cannot
	 * be created by Guice. Instead, injection must occur after module has
	 * initialized via requestInjection().
	 */
	@Inject
	public void set(Provider<EntityManager> emProvider) {
		this.emProvider = emProvider;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		handle();
		return invocation.proceed();
	}

	private void handle() {
		EntityManager em = emProvider.get();
		if (!em.isOpen())
			throw new AssertionError("EntityManager is closed.");
		if (!em.getTransaction().isActive())
			throw new AssertionError("Transaction is not active.");
	}

}
