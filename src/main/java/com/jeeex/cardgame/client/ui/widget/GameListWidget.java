package com.jeeex.cardgame.client.ui.widget;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.res.DefaultResource.GameListStyle;
import com.jeeex.cardgame.shared.entity.GameRoom;

public class GameListWidget extends Composite {

	/** Underlying table object. */
	private final FlexTable table;

	/** TODO(Jeeyoung Kim) Figure out what I'm going to do with this. */
	@SuppressWarnings("unused")
	private final GameListStyle style;

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
	}

	public void update(List<GameRoom> rooms) {
		// TODO: I should create a presenter for this widget...
		table.removeAllRows();
		int row = 0;
		for (GameRoom gr : rooms) {
			table.setWidget(row, 0, new Label(String.valueOf(gr.getId())));
			table.setWidget(row, 1, new Label(gr.getName()));
			row++;
		}
	}
}
