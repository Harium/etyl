package com.harium.etyl.layer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimatedLayerTest {

	private AnimatedLayer layer;
	
	@Before
	public void setUp() {
		layer = new AnimatedLayer(0, 0, 32, 32);
	}
	
	@Test
	public void testGetSrcMeasures() {
		Assert.assertEquals(32, layer.getW());
		Assert.assertEquals(32, layer.getH());
	}

}
