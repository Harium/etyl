package com.harium.etyl.core.linear;

import com.harium.etyl.linear.Triangle;
import org.junit.Assert;
import org.junit.Test;

import com.badlogic.gdx.math.Vector3;

public class TriangleTest {
	
	@Test
	public void testEquals() {
		Vector3 a = new Vector3(1,1,1);
		Vector3 b = new Vector3(2,2,1);
		Vector3 c = new Vector3(2,2,2);
		
		Triangle t1 = new Triangle(a, b, c);
		Triangle t2 = new Triangle(a, b, c);
		
		Assert.assertTrue(t1.equals(t2));
	}	

}
