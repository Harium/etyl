package com.harium.etyl.core.linear;

import org.junit.Assert;
import org.junit.Test;

import com.harium.etyl.linear.Line2D;
import com.harium.etyl.linear.Point2D;

public class Line2DTest {
	
	@Test
	public void testInterpolate() {
		
		Point2D p1 = new Point2D(0, 0);
		
		Point2D p2 = new Point2D(3, 6);
		
		int size = 4;
		
		Point2D[] points = Line2D.interpolate(p1, p2, size);
		
		Assert.assertEquals(size, points.length);
		
		Assert.assertEquals(0, points[0].getX(), 0);
		Assert.assertEquals(1, points[1].getX(), 0);
		Assert.assertEquals(2, points[2].getX(), 0);
		Assert.assertEquals(3, points[3].getX(), 0);
		
		Assert.assertEquals(0, points[0].getY(), 0);
		Assert.assertEquals(2, points[1].getY(), 0);
		Assert.assertEquals(4, points[2].getY(), 0);
		Assert.assertEquals(6, points[3].getY(), 0);
		
	}
	
	@Test
	public void testIntersection() {
		
		Point2D p1 = new Point2D(0, 0);
		Point2D p2 = new Point2D(4, 4);
		
		Point2D p3 = new Point2D(4, 0);
		Point2D p4 = new Point2D(0, 4);
		
		Point2D point = Line2D.intersection(p1, p2, p3, p4);
				
		Assert.assertEquals(2, point.getX(), 0);
		Assert.assertEquals(2, point.getY(), 0);
		
	}
	
}
