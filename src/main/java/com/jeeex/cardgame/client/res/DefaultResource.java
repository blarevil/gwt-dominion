package com.jeeex.cardgame.client.res;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface DefaultResource extends ClientBundle {
	public static interface Style extends CssResource {
		String card();
	}

	@Source("style.css")
	public Style style();
}
