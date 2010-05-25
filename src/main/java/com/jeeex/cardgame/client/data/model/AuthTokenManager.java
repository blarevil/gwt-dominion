package com.jeeex.cardgame.client.data.model;

import com.google.inject.Singleton;
import com.jeeex.cardgame.shared.entity.AuthToken;

/**
 * Singleton manager for Authentication Token.
 * <p>
 * TODO(Jeeyoung Kim): Make this class generic, for generic event registration.
 * DONE - created {@link Binded}.
 */
@Singleton
public class AuthTokenManager extends Binded<AuthToken> {
}
