package com.jeeex.cardgame.client.ui.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.jeeex.cardgame.client.res.DefaultResource.GameListStyle;

public class GameListView extends Composite {
	/** Underlying table object. */
	// TODO(Jeeyoung Kim) change the visibility of this object - encapsulate
	// the logic, so the presenter doesn't need direct access to this object.
	final FlexTable table;

	/** TODO(Jeeyoung Kim) Figure out what I'm going to do with this. */
	@SuppressWarnings("unused")
	private final GameListStyle style;

	private DecoratedPopupPanel popup;

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
	}

	/** Initialzes the popup panel for this widget. */
	public void setPopupPanel(Widget popupInternals) {
		popup = new DecoratedPopupPanel(true);
		popup.add(popupInternals);
	}

	/** Show the popup panel at some random place. */
	public void showPopupPanelAt(int left, int top) {
		popup.setPopupPosition(left, top);
		popup.show();
	}

	public void hidePopup() {
		popup.hide();
	}
}
