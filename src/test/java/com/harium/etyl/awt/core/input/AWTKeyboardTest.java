package com.harium.etyl.awt.core.input;

import java.awt.Component;
import java.awt.Panel;
import java.awt.event.KeyEvent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AWTKeyboardTest {

	AWTKeyboard keyboard;
	
	@Before
	public void setUp() {
		keyboard = new AWTKeyboard(null);
	}

	@Test
	public void testUpdate() {
		keyboard.update(0);
		Assert.assertFalse(keyboard.hasPendingEvent());
	}
	
	@Test
	public void testHasPendingEvents() {
		Component frame = new Panel();
				
		keyboard.keyPressed(new KeyEvent(frame, 1, System.currentTimeMillis(), 0, KeyEvent.VK_0, '0'));
		Assert.assertTrue(keyboard.hasPendingEvent());
	}
	
}
