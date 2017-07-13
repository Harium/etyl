package com.harium.etyl.core;

import static org.junit.Assert.assertEquals;

import java.awt.Frame;

import org.junit.Before;
import org.junit.Test;

import com.harium.etyl.awt.core.AWTCore;

public class SharedCoreTest {

	private int INITIAL_WIDTH = 20;
	private int INITIAL_HEIGHT = 20;
	
	private AWTCore core;
	
	@Before
	public void setUp() {
		core = new AWTCore(new Frame(),INITIAL_WIDTH,INITIAL_HEIGHT);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(INITIAL_WIDTH,core.getW());
		assertEquals(INITIAL_HEIGHT,core.getH());
	}

}
