package com.jeeex.cardgame.server.logic;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.server.aop.UnderTransaction;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.remote.Authenticated;
import com.jeeex.cardgame.shared.remote.InvalidTokenException;

/** All methods in this class must be under transaction. */
@RequestScoped
public class AuthenticationChecker {

	@Inject
	EntityManager em;

	public boolean isTokenValid(Authenticated authenticatedMessage) {
		if (authenticatedMessage == null) {
			return false;
		}
		return isTokenValid(authenticatedMessage.getAuthToken());
	}

	public void isValid(AuthToken token) throws InvalidTokenException {
		if (!isTokenValid(token))
			throw new InvalidTokenException();
	}

	@UnderTransaction
	public boolean isTokenValid(AuthToken token) {
		// null checks.
		if (token == null || token.getUserId() == null
				|| token.getTokenName() == null) {
			return false;
		}

		Query q = em.createQuery("SELECT tkn from AuthToken tkn "
				+ "WHERE tokenName = :name AND userId = :userId");
		q.setParameter("name", token.getTokenName());
		q.setParameter("userId", token.getUserId());
		try {
			q.getSingleResult();
		} catch (NoResultException e) {
			// no such authtoken exists - the token is invalid!
			return false;
		}
		return true;
	}
}
