package com.jeeex.cardgame.client.res;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface DefaultResource extends ClientBundle {
	public static interface MyStyle extends CssResource {
		String card();

		String cardsize();
	}

	public static interface GameListStyle extends CssResource{
		String background();

		String column1();
		
		String column2();

		String column_common();
	}

	@Source("style.css")
	public MyStyle style();
	
	@Source("gameliststyle.css")
	public GameListStyle gameListStyle();
}
