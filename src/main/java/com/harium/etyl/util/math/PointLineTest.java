package com.harium.etyl.util.math;

import com.badlogic.gdx.math.Vector3;

/**
 * PointLineTest - simple geometry to make things easy!
 * Forked from jdt Triangulation: https://code.google.com/archive/p/jdt/
 */
public class PointLineTest {
		
	/**
	 * tests the relation between this point (as a 2D [x,y] point) and a 2D
	 * segment a,b (the Z values are ignored), returns one of the following:
	 * LEFT, RIGHT, INFRONT_OF_A, BEHIND_B, ON_SEGMENT
	 * 
	 * @param a
	 *            the first point of the segment.
	 * @param b
	 *            the second point of the segment.
	 * @return the value (flag) of the relation between this point and the a,b
	 *         line-segment.
	 */
	public static PointLinePosition pointLineTest(Vector3 a, Vector3 b, Vector3 c) {

		double dx = b.x - a.x;
		double dy = b.y - a.y;
		double res = dy * (c.x - a.x) - dx * (c.y - a.y);

		if (res < 0)
			return PointLinePosition.LEFT;
		if (res > 0)
			return PointLinePosition.RIGHT;

		if (dx > 0) {
			if (c.x < a.x)
				return PointLinePosition.INFRONT_OF_A;
			if (b.x < c.x)
				return PointLinePosition.BEHIND_B;
			return PointLinePosition.ON_SEGMENT;
		}
		if (dx < 0) {
			if (c.x > a.x)
				return PointLinePosition.INFRONT_OF_A;
			if (b.x > c.x)
				return PointLinePosition.BEHIND_B;
			return PointLinePosition.ON_SEGMENT;
		}
		if (dy > 0) {
			if (c.y < a.y)
				return PointLinePosition.INFRONT_OF_A;
			if (b.y < c.y)
				return PointLinePosition.BEHIND_B;
			return PointLinePosition.ON_SEGMENT;
		}
		if (dy < 0) {
			if (c.y > a.y)
				return PointLinePosition.INFRONT_OF_A;
			if (b.y > c.y)
				return PointLinePosition.BEHIND_B;
			return PointLinePosition.ON_SEGMENT;
		}
		System.out.println("Error, pointLineTest with a=b");
		return PointLinePosition.ERROR;
	}
	
}

