package com.jeeex.cardgame.client.res;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface DefaultResource extends ClientBundle {
	public static interface MyStyle extends CssResource {
		String card();

		String cardsize();
	}

	@Source("style.css")
	public MyStyle style();
}
