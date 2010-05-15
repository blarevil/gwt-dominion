package com.jeeex.cardgame.client.init;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.jeeex.cardgame.client.InjectedApplication;

@GinModules(ClientModule.class)
public interface ClientInjector extends Ginjector {
	InjectedApplication application();
}
