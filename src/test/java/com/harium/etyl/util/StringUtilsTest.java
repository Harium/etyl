package com.harium.etyl.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testCountOccurrences() {
		String text = "123456789";
		char match = '2';
		
		Assert.assertEquals(1, StringUtils.countOccurrences(text, match));
		Assert.assertFalse(StringUtils.countOccurrences(text, match) == 2);
	}
	
}
