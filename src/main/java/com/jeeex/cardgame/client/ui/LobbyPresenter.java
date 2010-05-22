package com.jeeex.cardgame.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.event.GenericHandler;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.event.TypeConstants;
import com.jeeex.cardgame.client.ui.generic.Presenter;
import com.jeeex.cardgame.client.ui.widget.GameListView;
import com.jeeex.cardgame.client.util.EmptyCallback;
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

public class LobbyPresenter implements Presenter<AbstractLobbyView> {

	private final AuthTokenManager tknMgr;

	private final class GameListCallback extends
			EmptyCallback<GetGameListResponse> {
		@Override
		public void onSuccess(GetGameListResponse result) {
			gameList.update(result.getRooms());
		}
	}

	private final class LoginCallback extends EmptyCallback<LoginResponse> {
		@Override
		public void onSuccess(LoginResponse result) {
			if (result.isSuccessful()) {
				tknMgr.set(result.getAuthToken());
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

	final ChatPresenter chatPresenter;

	private final MyEventBus ebus;

	private final GameListView gameList;

	@Inject
	public LobbyPresenter(
	// ebus
			MyEventBus ebus,
			// views
			LobbyView view,
			// TODO - inject presenter for this instead
			GameListView gameList,
			// presenters
			ChatPresenter chatPresenter,
			// services
			LobbyServiceAsync lobbySvc, UserServiceAsync userSvc,
			// authtoken
			AuthTokenManager tknMgr) {
		this.view = view;
		this.lobbySvc = lobbySvc;
		this.userSvc = userSvc;
		this.gameList = gameList;
		this.chatPresenter = chatPresenter;
		this.tknMgr = tknMgr;
		this.ebus = ebus;
	}

	@Override
	public AbstractLobbyView getView() {
		return view;
	}

	@Override
	public void init() {
		chatPresenter.init();
		// initialize
		// should this be done via eventBus?
		view.setChatView(chatPresenter.getView());
		view.init();

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
				LogoutRequest req = new LogoutRequest();
				req.setAuthToken(tknMgr.get());
				tknMgr.set(null);
				userSvc.logout(req, new EmptyCallback<LogoutResponse>());
			}
		});

		registerCreateGame();

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

	private void registerCreateGame() {
		view.getCreateGameButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String gameName = Window.prompt("Enter the game name:", "");
				if (gameName != null) {
					CreateGameRequest req = new CreateGameRequest();
					req.setAuthToken(tknMgr.get());
					req.setName(gameName);
					lobbySvc.createGame(req,
							new EmptyCallback<CreateGameResponse>());
				}
			}
		});

		GenericHandler<Widget> handler = new GenericHandler<Widget>() {
			@Override
			public void onEvent(Widget wgt) {
				view.setCenterWidget(wgt);
			}
		};
		ebus.getHandlerManager().addHandler(TypeConstants.CENTER_WIDGET,
				handler);
		ebus.setCenterWidget(gameList);
	}
}
