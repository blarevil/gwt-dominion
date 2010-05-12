package com.jeeex.cardgame.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.jeeex.cardgame.client.init.ClientInjector;
import com.jeeex.cardgame.client.ui.MainPanel;

/**
 * The entry point.
 * 
 * @author jeeyoung Kim
 * @since 2010-05-12
 */
public class Application implements EntryPoint {
	ClientInjector injector = GWT.create(ClientInjector.class);

	public void onModuleLoad() {
		MainPanel panel = injector.mainPanel();
		RootPanel.get().add(panel);
	}
}
