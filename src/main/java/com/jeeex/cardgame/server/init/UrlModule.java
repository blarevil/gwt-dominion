package com.jeeex.cardgame.server.init;

import com.google.inject.servlet.ServletModule;
import com.jeeex.cardgame.server.entitymanager.TeardownFilter;
import com.jeeex.cardgame.server.servlet.GuiceRemoteServlet;

public class UrlModule extends ServletModule {

	@Override
	protected void configureServlets() {		
		serve("/com.jeeex.cardgame.Application/gwt.rpc").with(
				GuiceRemoteServlet.class);
		
		
		// This is suppose to be the last filter.
		filter("/*").through(TeardownFilter.class);
	}
}