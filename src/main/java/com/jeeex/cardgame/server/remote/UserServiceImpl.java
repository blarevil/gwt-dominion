package com.jeeex.cardgame.server.remote;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.jeeex.cardgame.shared.entity.AuthToken;
import com.jeeex.cardgame.shared.entity.User;
import com.jeeex.cardgame.shared.remote.user.CreateUserRequest;
import com.jeeex.cardgame.shared.remote.user.CreateUserResponse;
import com.jeeex.cardgame.shared.remote.user.LoginRequest;
import com.jeeex.cardgame.shared.remote.user.LoginResponse;
import com.jeeex.cardgame.shared.remote.user.LogoutRequest;
import com.jeeex.cardgame.shared.remote.user.LogoutResponse;
import com.jeeex.cardgame.shared.remote.user.UserService;

@RequestScoped
public class UserServiceImpl implements UserService {

	@Inject
	EntityManager em;

	@Override
	public CreateUserResponse createUser(CreateUserRequest request) {
		String name = request.getNewUserName();
		User user = new User();
		user.setUsername(name);
		{
			// TODO(Jeeyoung Kim): try to do AOP with transaction management.
			// Persist the user.
			em.getTransaction().begin();
			try {
				em.persist(user);
				em.getTransaction().commit();
			} catch (PersistenceException ex) {
				// Failed. return false.
				em.getTransaction().rollback();
				return new CreateUserResponse(false);
			}
		}
		return new CreateUserResponse(true);
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		String username = request.getUsername();
		AuthToken token = new AuthToken();
		em.getTransaction().begin();
		{
			Query q = em
					.createQuery("SELECT u from User u where u.username = :name");
			q.setParameter("name", username);

			User user = null;
			try {
				user = (User) q.getSingleResult();
			} catch (NoResultException exception) {
				// Login failed.
				em.getTransaction().rollback();
				return new LoginResponse(false, null);
			}

			token.setUser(user);
			// TODO(Jeeyoung Kim): Need better token generation scheme.
			token.setTokenName(String.valueOf(System.currentTimeMillis()));
			em.persist(token);
		}
		em.getTransaction().commit();
		return new LoginResponse(true, token);
	}

	@Override
	public LogoutResponse logout(LogoutRequest request) {
		em.getTransaction().begin();
		{
			Query q = em
					.createQuery("DELETE from AuthToken token where token.tokenName = :tkn");
			q.setParameter("tkn", request.getAuthToken().getTokenName());
			q.executeUpdate();
		}
		em.getTransaction().commit();
		return new LogoutResponse();
	}
}
