package com.jeeex.cardgame.client.ui.widget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class FlowTableWidget extends Composite implements HasWidgets {

	private final FlexTable table;
	private final List<Widget> widgetList;

	public FlowTableWidget() {
		table = new FlexTable();
		widgetList = new ArrayList<Widget>();
		
		initWidget(table);
	}

	@Override
	public void add(Widget w) {
		table.setWidget(0, widgetList.size(), w);
		widgetList.add(w);
	}

	@Override
	public void clear() {
		assert false : "Not implemented";
	}

	@Override
	public Iterator<Widget> iterator() {
		return widgetList.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		// TODO: Implement this.
		return false;
	}

}
