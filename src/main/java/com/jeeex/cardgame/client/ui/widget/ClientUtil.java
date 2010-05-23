package com.jeeex.cardgame.client.ui.widget;

import com.google.gwt.user.client.ui.IndexedPanel;

public class ClientUtil {

	/** Removes all the widgets from the given indexed panel. */
	public static void removeAllChildren(IndexedPanel iPanel) {
		for (int i = iPanel.getWidgetCount() - 1; i >= 0; i--) {
			iPanel.remove(i);
		}
	}

	private ClientUtil() {
	}
}
