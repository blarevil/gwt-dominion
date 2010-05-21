package com.jeeex.cardgame.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.ui.generic.Presenter;
import com.jeeex.cardgame.client.util.EmptyCallback;
import com.jeeex.cardgame.shared.remote.lobby.CreateGameRequest;
import com.jeeex.cardgame.shared.remote.lobby.CreateGameResponse;
import com.jeeex.cardgame.shared.remote.lobby.GetGameListRequest;
import com.jeeex.cardgame.shared.remote.lobby.GetGameListResponse;
import com.jeeex.cardgame.shared.remote.lobby.LobbyServiceAsync;
import com.jeeex.cardgame.shared.remote.user.CreateUserRequest;
import com.jeeex.cardgame.shared.remote.user.CreateUserResponse;
import com.jeeex.cardgame.shared.remote.user.LoginRequest;
import com.jeeex.cardgame.shared.remote.user.LoginResponse;
import com.jeeex.cardgame.shared.remote.user.LogoutRequest;
import com.jeeex.cardgame.shared.remote.user.LogoutResponse;
import com.jeeex.cardgame.shared.remote.user.UserServiceAsync;

public class LobbyPresenter implements Presenter<AbstractLobbyView> {

	private final AuthTokenManager tknMgr = new AuthTokenManager();

	private final class GameListCallback extends
			EmptyCallback<GetGameListResponse> {
		@Override
		public void onSuccess(GetGameListResponse result) {
			view.getGamelist().update(result.getRooms());
		}
	}

	private final class LoginCallback extends EmptyCallback<LoginResponse> {
		@Override
		public void onSuccess(LoginResponse result) {
			if (result.isSuccessful()) {
				tknMgr.setAuthToken(result.getAuthToken());
				Window.alert("Login completed. Token:" + result.getAuthToken());
			} else {
				Window.alert("Login failed.");
			}
		}
	}

	private final class CreateUserCallback extends
			EmptyCallback<CreateUserResponse> {
		@Override
		public void onSuccess(CreateUserResponse result) {
			if (result.isSuccess()) {
				Window.alert("New user successfully created.");
			} else {
				Window.alert("FAILED.");
			}
		}
	}

	final LobbyView view;

	final LobbyServiceAsync lobbySvc;

	final UserServiceAsync userSvc;

	@Inject
	public LobbyPresenter(LobbyView view, LobbyServiceAsync lobbySvc,
			UserServiceAsync userSvc) {
		this.view = view;
		this.lobbySvc = lobbySvc;
		this.userSvc = userSvc;
	}

	@Override
	public AbstractLobbyView getView() {
		return view;
	}

	@Override
	public void init() {
		view.getLogoutButton().setEnabled(false);

		// initialize 'Refresh game' handler.
		view.getRefreshButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				lobbySvc.getGameList(new GetGameListRequest(),
						new GameListCallback());
			}
		});

		// initialize 'create game' handler.
		view.getCreateUserButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO:(Jeeyoung Kim)
				// Window.prompt is a Bad UI.
				String newUserName = Window.prompt("Name of the new user", "");
				if (newUserName != null) {
					userSvc.createUser(new CreateUserRequest(newUserName),
							new CreateUserCallback());
				}
			}
		});

		// login button handler.
		view.getLoginButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String loginUsername = Window.prompt(
						"Log in with the following user:", "");
				LoginRequest request = new LoginRequest(loginUsername);
				userSvc.login(request, new LoginCallback());
			}
		});

		// logout button handler.
		view.getLogoutButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String authToken = tknMgr.getAuthToken();
				tknMgr.setAuthToken("");
				userSvc.logout(new LogoutRequest(authToken),
						new EmptyCallback<LogoutResponse>());
			}
		});

		view.getCreateGameButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String gameName = Window.prompt("Enter the game name:", "");
				if (gameName != null) {
					CreateGameRequest req = new CreateGameRequest();
					req.setName(gameName);
					lobbySvc.createGame(req,
							new EmptyCallback<CreateGameResponse>());
				}
			}
		});

		tknMgr.addHandler(new GenericHandler<String>() {
			@Override
			public void onEvent(String token) {
				view.getAuthTokenLabel().setText(token);
				boolean loggedIn = !token.equals("");
				view.getLoginButton().setEnabled(!loggedIn);
				view.getLogoutButton().setEnabled(loggedIn);
			}
		});
	}
}
