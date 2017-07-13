package com.harium.etyl.util;

import org.junit.Assert;
import org.junit.Test;

public class PathHelperTest {

	@Test
	public void testUpperDirectory() {
		
		String path = "/a/b/c";
		
		Assert.assertEquals("/a/b/", PathHelper.upperDirectory(path));
	}
	
	@Test
	public void testClearPath() {
		String path = "a/b/c/../d/e/../";
		
		Assert.assertEquals("a/b/d/", PathHelper.clearPath(path));
	}
	
}
