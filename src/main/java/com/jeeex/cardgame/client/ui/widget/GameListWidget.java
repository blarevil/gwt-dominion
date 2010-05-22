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
import com.jeeex.cardgame.client.res.DefaultResource.GameListStyle;
import com.jeeex.cardgame.shared.entity.GameRoom;

public class GameListWidget extends Composite {

	private final class GameClickHandler implements ClickHandler {
		private GameRoom gameRoom;

		public GameClickHandler(GameRoom gameRoom) {
			this.gameRoom = gameRoom;
		}

		@Override
		public void onClick(ClickEvent event) {
			setSelected(gameRoom);
			gameLabel.setText("NAME:" + gameRoom.getName());
			authorLabel.setText("CREATOR:"
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

	// temp stuff - this should be refactored at least to an inner class.
	Label gameLabel = new Label("");

	Label authorLabel = new Label("");

	@Inject
	public GameListWidget(GameListStyle style) {
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
		{
			FlowPanel fp = new FlowPanel();
			fp.add(gameLabel);
			fp.add(authorLabel);
			fp.add(new Label("Join game"));
			fp.add(new Label("Delete game"));
			p.add(fp);
		}
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
