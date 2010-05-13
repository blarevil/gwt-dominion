package com.jeeex.cardgame.client.data.model;

import javax.naming.event.EventContext;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Assert;
import org.junit.Test;

public class DefaultListModelTest {
	IMocksControl ctrl = EasyMock.createControl();
	DefaultListModel<String> model = new DefaultListModel<String>();
	ListHandler mock = ctrl.createMock(ListHandler.class);
	Capture<ListEvent> evtCapture = new Capture<ListEvent>();

	@Test
	public void testAdd() {
		// setup
		model.handlers().addHandler(mock);
		mock.onChange(EasyMock.capture(evtCapture));

		// test
		ctrl.replay();
		model.add("Input");
		ctrl.verify();

		// verify
		ListEvent evt = evtCapture.getValue();
		Assert.assertEquals(evt.getIndex(), 0);
		Assert.assertEquals(evt.getEventType(), ListEvent.Type.ADD);
	}

	@Test
	public void testRemove() {
		// setup
		model.add("FOO");

		model.handlers().addHandler(mock);
		mock.onChange(EasyMock.capture(evtCapture));

		// test
		ctrl.replay();
		model.remove(0);
		ctrl.verify();

		// verify
		ListEvent evt = evtCapture.getValue();
		Assert.assertEquals(evt.getIndex(), 0);
		Assert.assertEquals(evt.getEventType(), ListEvent.Type.REMOVE);
	}
}
