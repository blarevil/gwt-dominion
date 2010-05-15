package com.jeeex.cardgame.client.ui.generic;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

public class AnimationBuilder {

	// coord related
	int endTop;
	int endLeft;
	int startLeft;
	int startTop;

	int deltaLeft;
	int deltaTop;
	boolean deltaSet = false;

	// opacity related
	double startOpacity;
	double endOpacity;
	boolean opacitySet = false;

	// widget related
	Widget widget;
	AbsolutePanel panel;

	public AnimationBuilder panel(AbsolutePanel panel) {
		this.panel = panel;
		return this;
	}

	public AnimationBuilder widget(Widget widget) {
		this.widget = widget;
		return this;
	}

	public AnimationBuilder start(int left, int top) {
		startLeft = left;
		startTop = top;
		return this;
	}

	public AnimationBuilder end(int left, int top) {
		endLeft = left;
		endTop = top;
		return this;
	}

	public AnimationBuilder shift(int deltaLeft, int deltaTop) {
		this.deltaLeft = deltaLeft;
		this.deltaTop = deltaTop;
		deltaSet = true;
		return this;
	}

	public AnimationBuilder opacity(double start, double end) {
		startOpacity = start;
		endOpacity = end;
		opacitySet = true;
		return this;
	}

	public Animation build() {
		if (deltaSet) {
			endLeft = startLeft + deltaLeft;
			endTop = startTop + deltaTop;
		}
		return new MoveAnimation(panel, widget, startLeft, startTop, endLeft,
				endTop, startOpacity, endOpacity, opacitySet);
	}

	public static AnimationBuilder aniBuilder() {
		return new AnimationBuilder();
	}
}
