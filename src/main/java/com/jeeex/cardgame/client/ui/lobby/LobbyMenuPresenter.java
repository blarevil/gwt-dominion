package com.jeeex.cardgame.client.ui.lobby;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.data.model.Binded;
import com.jeeex.cardgame.client.data.model.UserState;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.ui.generic.Presenter;
import com.jeeex.cardgame.client.ui.widget.GameListPresenter;
import com.jeeex.cardgame.client.util.BaseCallback;
import com.jeeex.cardgame.shared.entity.AuthToken;
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

/** Presenter for the menu bar in the lobby screen. */
public class LobbyMenuPresenter implements Presenter<Widget> {
	private final class CreateUserCallback extends
			BaseCallback<CreateUserResponse> {
		@Override
		public void onSuccess(CreateUserResponse result) {
			if (result.isSuccess()) {
				Window.alert("New user successfully created.");
			} else {
				Window.alert("New user creation failed.");
			}
		}
	}

	private final class GameListCallback extends
			BaseCallback<GetGameListResponse> {
		@Override
		public void onSuccess(GetGameListResponse result) {
			gameListPresenter.update(result.getRooms());
		}
	}

	private final class LoginCallback extends BaseCallback<LoginResponse> {
		@Override
		public void onSuccess(LoginResponse result) {
			if (result.isSuccessful()) {
				userState.set(UserState.IN_LOBBY);
				tknMgr.set(result.getAuthToken());
				Window.alert("Login completed. Token:" + result.getAuthToken());
			} else {
				Window.alert("Login failed.");
			}
		}
	}

	private final LoginCallback loginCallback = new LoginCallback();

	private final CreateUserCallback createUserCallback = new CreateUserCallback();

	private final GameListCallback gameListCallback = new GameListCallback();

	@Inject
	private Binded<UserState> userState;
	@Inject
	private LobbyMenuView view;
	@Inject
	private LobbyServiceAsync lobbySvc;
	@Inject
	private UserServiceAsync userSvc;
	@Inject
	private Binded<AuthToken> tknMgr;

	private GameListPresenter gameListPresenter;

	public void setGameListPresenter(GameListPresenter glp) {
		gameListPresenter = glp;
	}

	@Inject
	private LobbyMenuPresenter() {
	}

	@Override
	public Widget getView() {
		return view;
	}

	@Override
	public void init() {
		// Assertions
		assert gameListPresenter != null;

		view.getLogoutButton().setEnabled(false);
		// initialize refresh
		view.getRefreshButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				lobbySvc
						.getGameList(new GetGameListRequest(), gameListCallback);
			}
		});

		// initialize create
		view.getCreateUserButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String newUserName = Window.prompt("Name of the new user", "");
				if (newUserName != null) {
					userSvc.createUser(new CreateUserRequest(newUserName),
							createUserCallback);
				}
			}
		});

		// initialize login
		view.getLoginButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String loginUsername = Window.prompt(
						"Log in with the following user:", "");
				LoginRequest request = new LoginRequest(loginUsername);
				userSvc.login(request, loginCallback);
			}
		});

		// initialize logout
		view.getLogoutButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				LogoutRequest req = new LogoutRequest();
				req.setAuthToken(tknMgr.get());
				tknMgr.set(null);
				userSvc.logout(req, new BaseCallback<LogoutResponse>());
			}
		});

		// initialize creage game
		view.getCreateGameButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String gameName = Window.prompt("Enter the game name:", "");
				if (gameName != null) {
					CreateGameRequest req = new CreateGameRequest();
					req.setAuthToken(tknMgr.get());
					req.setName(gameName);
					lobbySvc.createGame(req,
							new BaseCallback<CreateGameResponse>());
				}
			}
		});

		// bind token.
		tknMgr.addHandler(new GenericHandler<AuthToken>() {
			@Override
			public void onEvent(AuthToken token) {
				view.getAuthTokenLabel().setText(String.valueOf(token));
				boolean loggedIn = token != null;
				view.getLoginButton().setEnabled(!loggedIn);
				view.getLogoutButton().setEnabled(loggedIn);
			}
		});
	}

}
