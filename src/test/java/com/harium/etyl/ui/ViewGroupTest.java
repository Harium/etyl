package com.harium.etyl.ui;

import org.junit.Assert;
import org.junit.Test;

import com.harium.etyl.ui.style.Orientation;

public class ViewGroupTest {

	@Test
	public void testAddViewInVerticalPanel() {
		Panel panel = new Panel(0,0,260,360);
		panel.setOrientation(Orientation.VERTICAL);
		
		TextView hello = new TextView("Hello");
		panel.add(hello);
		
		//Round should be zero?
		int round = 1;
		
		Assert.assertEquals(panel.getX(), hello.getX());
		Assert.assertEquals(panel.getY(), hello.getY());
		Assert.assertEquals(panel.getW()-round, hello.getW());
		Assert.assertEquals(panel.getH()-round, hello.getH());
		
		TextView hi = new TextView("Hi!");
		panel.add(hi);
		
		Assert.assertEquals(panel.getH()/2, hello.getH());
		Assert.assertEquals(panel.getH()/2-round, hi.getH());
	}
	
}
