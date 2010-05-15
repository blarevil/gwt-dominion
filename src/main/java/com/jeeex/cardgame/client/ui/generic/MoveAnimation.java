package com.jeeex.cardgame.client.ui.generic;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

public class MoveAnimation extends Animation {

	public MoveAnimation(AbsolutePanel panel, Widget widget, int startLeft,
			int startTop, int endLeft, int endTop, double startOpacity,
			double endOpacity, boolean opacitySet) {
		this.panel = panel;
		this.widget = widget;

		// coord related.
		this.startLeft = startLeft;
		this.startTop = startTop;
		this.endLeft = endLeft;
		this.endTop = endTop;

		// opacity related
		this.startOpacity = startOpacity;
		this.endOpacity = endOpacity;
		this.opacitySet = opacitySet;
	}

	final AbsolutePanel panel;

	final Widget widget;

	final int startLeft;
	final int startTop;

	final int endLeft;
	final int endTop;

	final double startOpacity;
	final double endOpacity;
	final boolean opacitySet;

	@Override
	protected void onUpdate(double progress) {
		if (!widget.isAttached()) {
			// TODO(Jeeyoung Kim) this looks very hacky.
			return;
		} else {
			panel.setWidgetPosition(widget, midpoint(startLeft, endLeft,
					progress), midpoint(startTop, endTop, progress));
			if (opacitySet) {
				// update the style, if the opacity change is present.
				widget.getElement().getStyle().setOpacity(
						midpoint(startOpacity, endOpacity, progress));
			}
		}
	}

	public static int midpoint(int a, int b, double weight) {
		int diff = b - a;
		return ((int) (diff * weight)) + a;
	}

	public static double midpoint(double a, double b, double weight) {
		double diff = b - a;
		return (diff * weight) + a;
	}
}
