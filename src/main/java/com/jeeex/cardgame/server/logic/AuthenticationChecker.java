package com.jeeex.cardgame.server.logic;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.server.aop.UnderTransaction;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.entity.User;
import com.jeeex.cardgame.shared.remote.Authenticated;
import com.jeeex.cardgame.shared.remote.InvalidTokenException;

/**
 * Provides utility methods to check the validity of the provided
 * {@link AuthToken}. The method {@link #getUser(AuthToken)} can be used to
 * extract the authenticated user from the provided {@link AuthToken}.
 * <p>
 * All methods in this class must be under transaction.
 * <p>
 * TODO(Jeeyoung Kim): Write tests for this class.
 * 
 * @author Jeeyoung Kim
 * @since 2010-05-25
 */
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

	/** Does this belong here? */
	@UnderTransaction
	public User getUser(AuthToken token) {
		return em.getReference(User.class, token.getUserId());
	}
}
