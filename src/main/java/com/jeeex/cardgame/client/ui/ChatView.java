package com.jeeex.cardgame.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ChatView extends Composite {
	private static final Binder binder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ChatView> {
	}

	public ChatView() {
		initWidget(binder.createAndBindUi(this));
	}
}
