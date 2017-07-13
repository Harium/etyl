package com.harium.etyl.util.math;

import org.junit.Assert;
import org.junit.Test;

import com.harium.etyl.commons.math.EtyllicaMath;

public class EtyllicaMathTest {

	@Test
	public void testDiffMod() {
		int a = 50;
		int b = 80;
		
		Assert.assertEquals(30, EtyllicaMath.diffMod(a, b), 0);
	}
	
	@Test
	public void testSqr() {
		Assert.assertEquals(4, EtyllicaMath.sqr(2), 0);
		Assert.assertEquals(9, EtyllicaMath.sqr(3), 0);
		Assert.assertEquals(16, EtyllicaMath.sqr(4), 0);
		Assert.assertEquals(25, EtyllicaMath.sqr(5), 0);
	}
	
	@Test
	public void testMax() {
		
		int a = 2;
		int b = 9028391;
		
		Assert.assertEquals(b, EtyllicaMath.max(a, b));
	}
	
	@Test
	public void testMaxWithThreeParams() {
		
		int a = 2;
		int b = 9028391;
		int c = 90283910;
		
		Assert.assertEquals(c, EtyllicaMath.max(a, b, c));
	}
	
	@Test
	public void testMin() {
		
		int a = 2;
		int b = 9028391;
		
		Assert.assertEquals(a, EtyllicaMath.min(a, b));
	}
	
	@Test
	public void testMinWithThreeParams() {
		
		int a = 2;
		int b = 9028391;
		int c = 1;
		
		Assert.assertEquals(c, EtyllicaMath.min(a, b, c));
	}
}
