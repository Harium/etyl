package com.harium.etyl.core.context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.harium.etyl.commons.context.Session;

public class SessionTest {
	
	private Session session;
	
	@Before
	public void setUp() {
		session = new Session();
		
		session.put("string", "text");
		session.put("number", 1);
		session.put("selected", false);
	}
	
	@Test
	public void testGetAsBoolean() {
		boolean value = session.getAsBoolean("selected");
		Assert.assertEquals(false, value);
	}
	
	@Test
	public void testGetAsInt() {
		int number = session.getAsInt("number");
		Assert.assertEquals(1, number);
	}
	
	@Test
	public void testGetAsString() {
		String text = session.getAsString("string");
		Assert.assertEquals("text", text);
	}
}