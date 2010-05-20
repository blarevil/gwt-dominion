package com.jeeex.cardgame.shared.remote.user;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwt.rpc")
public interface UserService extends RemoteService {

	public CreateUserResponse createUser(CreateUserRequest request);

	public LoginResponse login(LoginRequest request);

	public LogoutResponse logout(LogoutRequest request);
}
