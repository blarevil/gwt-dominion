package com.jeeex.cardgame.client.init;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.jeeex.cardgame.client.ui.MainPanel;

@GinModules(ClientModule.class)
public interface ClientInjector extends Ginjector {
	/** Retrieves the main panel. */
	MainPanel mainPanel();
}
