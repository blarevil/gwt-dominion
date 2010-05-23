package com.jeeex.cardgame.client.ui.widget;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.ui.AuthTokenManager;
import com.jeeex.cardgame.client.ui.GamePresenter;
import com.jeeex.cardgame.client.ui.generic.Presenter;
import com.jeeex.cardgame.client.util.EmptyCallback;
import com.jeeex.cardgame.shared.entity.GameRoom;
import com.jeeex.cardgame.shared.remote.lobby.JoinGameRequest;
import com.jeeex.cardgame.shared.remote.lobby.JoinGameResponse;
import com.jeeex.cardgame.shared.remote.lobby.LobbyServiceAsync;

@Singleton
public class GameListPresenter implements Presenter<GameListView> {

	private class GameClickHandler implements ClickHandler {
		/** GameRoom row to attach the handler to. */
		private final GameRoom g;

		public GameClickHandler(GameRoom gameRoom) {
			this.g = gameRoom;
		}

		@Override
		public void onClick(ClickEvent event) {
			setSelected(g);

			// initialize popup panel. this can probably be refactored too.
			popupPanel.configForShowing(g.getName(),
					g.getCreatedBy().getUsername(), 
					g.getParticipatingUsers().toString());

			// location.
			int left = event.getNativeEvent().getClientX();
			int top = event.getNativeEvent().getClientY();
			view.showPopupPanelAt(left, top);
		}
	}

	class PopupWidget extends Composite {
		Label gameName = new Label();
		Label creatorName = new Label();
		Label joinedPeople = new Label();

		public PopupWidget() {
			// variables.
			Anchor joinGame = new Anchor("Join game.");

			// register widgets.
			FlowPanel fp = new FlowPanel();
			fp.add(gameName);
			fp.add(creatorName);
			fp.add(joinedPeople);
			fp.add(new Label("Delete game"));
			fp.add(joinGame);

			// register events
			joinGame.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					JoinGameRequest r = new JoinGameRequest();
					r.setAuthToken(tknMgr.get());
					r.setGameRoom(selected);
					// TODO(Jeeyoung Kim): Replace EmptyCallback with something else.
					lobbySvc.joinGame(r, new EmptyCallback<JoinGameResponse>());
					// this should be done from the CALLBACK function.
					ebus.setCenterWidget(gamePresenter.getView());
					view.hidePopup();
				}
			});
			initWidget(fp);
		}

		public void configForShowing(String name, String creator,
				String participating) {
			gameName.setText("name:" + name);
			creatorName.setText("creator:" + creator);
			joinedPeople.setText(participating);
		}
	}

	@Inject
	private AuthTokenManager tknMgr;

	@Inject
	private LobbyServiceAsync lobbySvc;

	/** Currently selected game room. */
	private GameRoom selected = null;

	private PopupWidget popupPanel = new PopupWidget();

	@Inject
	private GameListView view;
	
	@Inject
	private MyEventBus ebus;
	
	@Inject
	private GamePresenter gamePresenter;

	@Inject
	GameListPresenter() {
	}

	@Override
	public GameListView getView() {
		return view;
	}

	@Override
	public void init() {
		// cascade init sequence
		gamePresenter.init();
		view.setPopupPanel(popupPanel);
		// better way to do this via assisted inject.
		gamePresenter.setGameListPresenter(this);
	}

	public void setSelected(GameRoom g) {
		this.selected = g;
	}

	/** Update list of game rooms displayed by the view. */
	public void update(List<GameRoom> rooms) {
		view.table.removeAllRows();
		int row = 0;
		for (GameRoom gr : rooms) {
			view.table.setWidget(row, 0, new Label(String.valueOf(gr.getId())));
			Anchor a = new Anchor(gr.getName());
			a.addClickHandler(new GameClickHandler(gr));
			view.table.setWidget(row, 1, a);
			row++;
		}
	}
}
