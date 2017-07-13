package com.harium.etyl.ui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.harium.etyl.ui.base.BaseButton;

public class ScrollerPanelTest {

	ScrollView panel;
	
	@Before
	public void setUp() {
		panel = new ScrollView(0,40,260,200);
	}
	
	@Test
	public void testSetComponent() {
		TextView hello = new TextView("Hello");
		panel.setComponent(hello);
				
		Assert.assertEquals(panel.getX(), hello.getX());
		Assert.assertEquals(panel.getY(), hello.getY());
		
		//Do not change the size
		Assert.assertEquals(260, panel.getW());
		Assert.assertEquals(200, panel.getH());
	}
		
	@Test
	public void testAddKnob() {
		Panel otherPanel = new Panel(0,0,260,400);
		panel.setComponent(otherPanel);

		BaseButton knob = panel.getKnob();
		Assert.assertEquals(240, knob.getX());
		Assert.assertEquals(panel.getY()+panel.getButtonSize(), knob.getY());
	}
	
	@Test
	public void testScrollDown() {
		Panel otherPanel = new Panel(0,0,260,400);
		panel.setComponent(otherPanel);
		
		Assert.assertEquals(40, otherPanel.getY());
		panel.scrollDown();
		Assert.assertEquals(30, otherPanel.getY());
		panel.scrollDown();
		Assert.assertEquals(20, otherPanel.getY());
	}
}
