package com.jeeex.cardgame.server.entitymanager;

import java.io.IOException;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class TeardownFilter implements Filter {

	@Inject
	Provider<EntityManager> emProvider;

	@Inject
	Logger logger;

	@Override
	public void destroy() {
		// empty.
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(req, resp);
		// doing a cleanup via filter sounds very sketchy.
		// TODO(Jeeyoung Kim) Implement teardown.
		logger.info("closing EntityManager.");
		emProvider.get().close();
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// empty.
	}
}