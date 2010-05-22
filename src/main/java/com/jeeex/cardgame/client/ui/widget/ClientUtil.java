package com.jeeex.cardgame.client.ui.widget;

import com.google.gwt.user.client.ui.IndexedPanel;

public class ClientUtil {

	public static void removeAllChildren(IndexedPanel iPanel) {
		for (int i = iPanel.getWidgetCount() - 1; i >= 0; i--) {
			iPanel.remove(i);
		}
	}

	private ClientUtil() {
	}
}
