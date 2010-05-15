package com.jeeex.cardgame.server.init;

import com.google.inject.servlet.ServletModule;
import com.jeeex.cardgame.server.servlet.GuiceRemoteServlet;

public class UrlModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/com.jeeex.cardgame.Application/gwt.rpc").with(
				GuiceRemoteServlet.class);
	}
}