package com.jeeex.cardgame.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class GameRoomList extends Composite {

	/** Underlying table object. */
	private FlexTable table;

	public GameRoomList() {
		table = new FlexTable();
		{
			table.setWidget(0, 0, new Label("Hello, world"));
			table.setBorderWidth(1);
		}
		initWidget(table);
	}
}
