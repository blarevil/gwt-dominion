package com.jeeex.cardgame.client.ui.widget;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.event.MyEventBus;
import com.jeeex.cardgame.client.res.DefaultResource.GameListStyle;
import com.jeeex.cardgame.client.ui.AuthTokenManager;
import com.jeeex.cardgame.client.util.EmptyCallback;
import com.jeeex.cardgame.shared.entity.GameRoom;
import com.jeeex.cardgame.shared.remote.lobby.JoinGameRequest;
import com.jeeex.cardgame.shared.remote.lobby.JoinGameResponse;
import com.jeeex.cardgame.shared.remote.lobby.LobbyServiceAsync;

/**
 * TODO(Jeeyoung Kim) This widget is big giant code soup. Figure out how to make
 * this better...
 */
public class GameListView extends Composite {

	class PopupWidget extends Composite {
		Label gameName = new Label();
		Label creatorName = new Label();

		public PopupWidget() {
			FlowPanel fp = new FlowPanel();
			fp.add(gameName);
			fp.add(creatorName);
			fp.add(new Label("Delete game"));
			Anchor joinGame = new Anchor("Join game.");
			joinGame.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					// hm... i think this approach is right.
					// ebus.setCenterWidget(new Label("HIIII"));
					JoinGameRequest r = new JoinGameRequest();
					r.setAuthToken(tknMgr.get());
					r.setGameRoom(selectedRoom);
					lobbySvc.joinGame(r, new EmptyCallback<JoinGameResponse>());
				}
			});
			fp.add(joinGame);
			initWidget(fp);
		}
	}

	private final class GameClickHandler implements ClickHandler {
		private GameRoom gameRoom;

		public GameClickHandler(GameRoom gameRoom) {
			this.gameRoom = gameRoom;
		}

		@Override
		public void onClick(ClickEvent event) {
			setSelected(gameRoom);
			popup.gameName.setText("NAME:" + gameRoom.getName());
			popup.creatorName.setText("CREATOR:"
					+ gameRoom.getCreatedBy().getUsername());
			int left = event.getNativeEvent().getClientX();
			int top = event.getNativeEvent().getClientY();
			p.setPopupPosition(left, top);
			p.show();
		}
	}

	private void setSelected(GameRoom gameRoom) {
		this.selectedRoom = gameRoom;
	}

	/** Underlying table object. */
	private final FlexTable table;

	/** TODO(Jeeyoung Kim) Figure out what I'm going to do with this. */
	@SuppressWarnings("unused")
	private final GameListStyle style;

	private DecoratedPopupPanel p;

	/** Selected game room. */
	private GameRoom selectedRoom;

	@Inject
	private MyEventBus ebus;

	@Inject
	private LobbyServiceAsync lobbySvc;

	@Inject
	private AuthTokenManager tknMgr;

	private PopupWidget popup = new PopupWidget();

	@Inject
	public GameListView(GameListStyle style) {
		this.style = style;

		// widget creation.
		table = new FlexTable();

		// styling.
		table.addStyleName(style.background());

		// common styling for all the columns.
		for (int i = 0; i < 2; i++) {
			table.getColumnFormatter().addStyleName(i, style.column_common());
		}
		table.getColumnFormatter().addStyleName(0, style.column1());
		table.getColumnFormatter().addStyleName(1, style.column2());
		style.ensureInjected();

		// required as specified by Composite.
		initWidget(table);

		initPopup();
	}

	private void initPopup() {
		p = new DecoratedPopupPanel(true);
		p.add(popup);
	}

	public void update(List<GameRoom> rooms) {
		// TODO: I should create a presenter for this widget...
		table.removeAllRows();
		int row = 0;
		for (GameRoom gr : rooms) {
			table.setWidget(row, 0, new Label(String.valueOf(gr.getId())));
			Anchor a = new Anchor(gr.getName());
			a.addClickHandler(new GameClickHandler(gr));
			table.setWidget(row, 1, a);
			row++;
		}
	}
}
