package com.jeeex.cardgame.client.init;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.jeeex.cardgame.client.InjectedApplication;

@GinModules( { ClientModule.class, RemoteModule.class })
public interface ClientInjector extends Ginjector {
	/** Injected entry point to the application. */
	InjectedApplication application();
}
