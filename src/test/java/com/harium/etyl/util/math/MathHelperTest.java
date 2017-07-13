package com.harium.etyl.util.math;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.harium.etyl.linear.Triangle;

import com.badlogic.gdx.math.Vector3;

public class MathHelperTest {
	@Test
	public void testNormal() {
		Vector3 a = new Vector3(0,0,0);
		Vector3 b = new Vector3(10,0,0);
		Vector3 c = new Vector3(0,10,0);
		Triangle triangle = new Triangle(a, b, c);
		
		Vector3 normal = MathHelper.calculateNormal(triangle);
		
		Assert.assertEquals(0, normal.x, 0.0001);
		Assert.assertEquals(0, normal.y, 0.0001);
		Assert.assertEquals(1, normal.z, 0.0001);
	}
	
	@Test
	public void testOrderedInZList() {
		Vector3 a = new Vector3(0,0,3);
		Vector3 b = new Vector3(10,0,2);
		Vector3 c = new Vector3(0,10,1);

		List<Vector3> ordered = MathHelper.orderedInZ(a, b, c);

		Assert.assertEquals(c, ordered.get(0));
		Assert.assertEquals(b, ordered.get(1));
		Assert.assertEquals(a, ordered.get(2));
	}

	@Test
	public void testAreaTriangleInOrigin() {
		Vector3 a = new Vector3(0,0,0);
		Vector3 b = new Vector3(10,0,0);
		Vector3 c = new Vector3(0,10,0);
		Triangle triangle = new Triangle(a, b, c);

		Assert.assertEquals(50, MathHelper.triangleArea(triangle), 0.00001);
	}

	/**
	 * Test based on explanation: https://www.youtube.com/watch?v=MnpaeFPyn1A
	 */
	@Test
	public void testAreaTriangle() {
		Vector3 a = new Vector3(-5,5,-5);
		Vector3 b = new Vector3(1,-6,6);
		Vector3 c = new Vector3(2,-3,4);
		Triangle triangle = new Triangle(a, b, c);

		Assert.assertEquals(19.3067f, MathHelper.triangleArea(triangle), 0.0001);
	}

	@Test
	public void testTriangularPrismVolume() {
		Vector3 a = new Vector3(-5,5,-5);
		Vector3 b = new Vector3(1,-6,6);
		Vector3 c = new Vector3(2,-3,4);
		Triangle triangle = new Triangle(a, b, c);
		float height = 10;

		Assert.assertEquals(193.0673f, MathHelper.trianglularPrismVolume(triangle, height), 0.0001);
	}

	@Test
	public void testVolumeUnderAlignedTriangle() {
		Vector3 a = new Vector3(0,0,1);
		Vector3 b = new Vector3(1,0,1);
		Vector3 c = new Vector3(0,1,1);
		Triangle triangle = new Triangle(a, b, c);
		
		Vector3 d = new Vector3(1,1,1);
		Triangle otherTriangle = new Triangle(b, d, c);

		//0.5 = half of cube volume
		Assert.assertEquals(0.5, MathHelper.volumeUnderTriangle(triangle), 0.001);
		Assert.assertEquals(0.5, MathHelper.volumeUnderTriangle(otherTriangle), 0.001);
	}

	@Test
	public void testVolumeUnderOriginTriangle() {
		Vector3 a = new Vector3(0,0,0);
		Vector3 b = new Vector3(1,0,0);
		Vector3 c = new Vector3(0,1,0);
		Triangle triangle = new Triangle(a, b, c);

		Assert.assertEquals(0, MathHelper.volumeUnderTriangle(triangle), 0.001);
	}
	
	@Test
	public void testVolumeUnderTriangle() {
		Vector3 a = new Vector3(-5,5,-5);
		Vector3 b = new Vector3(1,-6,6);
		Vector3 c = new Vector3(2,-3,4);
		Triangle triangle = new Triangle(a, b, c);

		Assert.assertEquals(24.166f, MathHelper.volumeUnderTriangle(triangle), 0.001);
	}
	
	@Test
	public void testVolumeUnderOtherTriangle() {
		Vector3 a = new Vector3(57.352f, 78.256f, 16.55f);
		Vector3 b = new Vector3(58.96f, 78.256f, 16.12f);
		Vector3 c = new Vector3(58.96f, 79.864f, 21.98f);
		Triangle triangle = new Triangle(a, b, c);

		Assert.assertEquals(23.5468f, MathHelper.volumeUnderTriangle(triangle), 0.001);
	}
	
	@Test
	public void testVolumeOfMesh() {
		Vector3 a = new Vector3(0,0,1);
		Vector3 b = new Vector3(1,0,1);
		Vector3 c = new Vector3(0,-1,1);
		Triangle triangle = new Triangle(a, c, b);
		
		//Projection points
		Vector3 pa = new Vector3(a.x,a.y,0);
		Vector3 pb = new Vector3(b.x,b.y,0);
		Vector3 pc = new Vector3(c.x,c.y,0);
		
		Triangle t1 = new Triangle(a, b, pa);
		Triangle t2 = new Triangle(b, pb, pa);
		Triangle t3 = new Triangle(b, c, pb);
		Triangle t4 = new Triangle(c, pc, pb);
		Triangle t5 = new Triangle(c, a, pc);
		Triangle t6 = new Triangle(a, pa, pc);
		Triangle tp = new Triangle(pa, pb, pc);

		Vector3 ntop = MathHelper.calculateNormal(triangle);
		Vector3 nt1 = MathHelper.calculateNormal(t1);
		Vector3 nt2 = MathHelper.calculateNormal(t2);
		Vector3 nt3 = MathHelper.calculateNormal(t3);
		Vector3 nt4 = MathHelper.calculateNormal(t4);
		Vector3 nt5 = MathHelper.calculateNormal(t5);
		Vector3 nt6 = MathHelper.calculateNormal(t6);
		Vector3 np = MathHelper.calculateNormal(tp);
		
		Assert.assertEquals(1, ntop.z, 0.001);
		Assert.assertEquals(0, nt1.x, 0.001);
		Assert.assertEquals(1, nt1.y, 0.001);
		Assert.assertEquals(nt1.y, nt2.y, 0.0001);
		Assert.assertEquals(0.707, nt3.x, 0.001);
		Assert.assertEquals(-0.707, nt3.y, 0.001);
		Assert.assertEquals(nt3, nt4);
		Assert.assertEquals(-1, nt5.x, 0.001);
		Assert.assertEquals(0, nt5.y, 0.001);
		Assert.assertEquals(nt5.x, nt6.x, 0.001);
		Assert.assertEquals(-1, np.z, 0.001);
		
		Set<Triangle> triangles = new HashSet<Triangle>();
		triangles.add(triangle);
		triangles.add(t1);
		triangles.add(t2);
		triangles.add(t3);
		triangles.add(t4);
		triangles.add(t5);
		triangles.add(t6);
		triangles.add(tp);
		Assert.assertEquals(0.5, MathHelper.volumeOfMesh(triangles), 0.001);
	}
}
