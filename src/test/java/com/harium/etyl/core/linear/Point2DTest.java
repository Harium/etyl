package com.harium.etyl.core.linear;

import com.harium.etyl.linear.Point2D;
import org.junit.Assert;
import org.junit.Test;

public class Point2DTest {
	
	@Test
	public void testEquals() {
		
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(0,0);
		
		Assert.assertTrue(p1.equals(p2));
	}
	
	@Test
	public void testDistance() {
		
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(20,0);
				
		Assert.assertEquals(20, p1.distance(p2), 0.1);
	}

}
