package com.jeeex.cardgame.server.init;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestUtil {

	public static Injector injector() {
		return Guice.createInjector(new DummyPersistenceModule());
	}
}
