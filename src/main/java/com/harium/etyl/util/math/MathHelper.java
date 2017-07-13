package com.harium.etyl.util.math;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.harium.etyl.linear.Triangle;

import com.badlogic.gdx.math.Vector3;

public class MathHelper {

	public static Vector3 calculateNormal(Triangle triangle) {
		Vector3 a = new Vector3(triangle.getA());
		Vector3 b = new Vector3(triangle.getB());
		Vector3 c = new Vector3(triangle.getC());

		Vector3 v = new Vector3(b).sub(a);
		Vector3 u = new Vector3(c).sub(a);

		Vector3 normal = new Vector3(v).crs(u);
		normal.nor();

		return normal;
	}

	public static boolean isClockwise(Triangle triangle) {
		return isClockwise(triangle.getA(), triangle.getB(), triangle.getC());
	}
	
	public static boolean isClockwise(Vector3 a, Vector3 b, Vector3 c) {
		PointLinePosition res = PointLineTest.pointLineTest(a, b, c);

		return (res == PointLinePosition.ON_SEGMENT) ||
				(res == PointLinePosition.LEFT) ||
				(res == PointLinePosition.INFRONT_OF_A) ||
				(res == PointLinePosition.BEHIND_B);
	}

	public static float triangleArea(Triangle triangle) {
		Vector3 a = triangle.getA();
		Vector3 b = triangle.getB();
		Vector3 c = triangle.getC();

		Vector3 ab = new Vector3(b.sub(a));
		Vector3 ac = new Vector3(c.sub(a));

		Vector3 result = ab.crs(ac);

		return result.len()/2;
	}

	public static float trianglularPrismVolume(Triangle triangle, float height) {
		float triangleArea = triangleArea(triangle);
		return triangleArea * height;
	}

	/**
	 * Method to calculate the volume under the triangle 
	 * @param triangle
	 * @return volume between the triangle and the plane z = 0
	 */
	public static double volumeUnderTriangle(Triangle triangle) {
		return volumeUnderTriangle(triangle, 0);
	}

	public static double volumeUnderTriangle(Triangle triangle, float z) {
		Vector3 a = triangle.getA();
		Vector3 b = triangle.getB();
		Vector3 c = triangle.getC();

		//Projection points
		Vector3 pa = new Vector3(a.x,a.y,z);
		Vector3 pb = new Vector3(b.x,b.y,z);
		Vector3 pc = new Vector3(c.x,c.y,z);

		Triangle t1 = new Triangle(a, c, pa);
		Triangle t2 = new Triangle(c, pc, pa);
		Triangle t3 = new Triangle(c, b, pc);
		Triangle t4 = new Triangle(b, pb, pc);
		Triangle t5 = new Triangle(b, a, pb);
		Triangle t6 = new Triangle(a, pa, pb);
		Triangle tp = new Triangle(pa, pb, pb);

		Set<Triangle> triangles = new HashSet<Triangle>();
		triangles.add(triangle);
		triangles.add(t1);
		triangles.add(t2);
		triangles.add(t3);
		triangles.add(t4);
		triangles.add(t5);
		triangles.add(t6);
		triangles.add(tp);

		return volumeOfMesh(triangles);
	}

	/**
	 * Method to calculate volume of a 3d mesh
	 * Cha Zhang and Tsuhan Chen - EFFICIENT FEATURE EXTRACTION 
	 * FOR 2D/3D OBJECTS IN MESH REPRESENTATION
	 * @param triangles
	 * @return the volume
	 */
	public static double volumeOfMesh(Set<Triangle> triangles) {
		double sum = 0;

		for(Triangle triangle: triangles) {
			sum += signedVolumeOfTriangle(triangle);
		}

		return sum;
	}

	public static double signedVolumeOfTriangle(Triangle triangle) {
		return signedVolumeOfTriangle(triangle.getA(), triangle.getB(), triangle.getC());
	}

	public static double signedVolumeOfTriangle(Vector3 p1, Vector3 p2, Vector3 p3) {
		double v321 = p3.x*p2.y*p1.z;
		double v231 = p2.x*p3.y*p1.z;
		double v312 = p3.x*p1.y*p2.z;
		double v132 = p1.x*p3.y*p2.z;
		double v213 = p2.x*p1.y*p3.z;
		double v123 = p1.x*p2.y*p3.z;
		return (-v321 + v231 + v312 - v132 - v213 + v123)/6.0f;
	}

	public static List<Vector3> orderedInZ(Vector3 ... points) {
		List<Vector3> ordered = Arrays.asList(points);

		Collections.sort(ordered, LOWER_Z_COMPARATOR);
		return ordered;
	}

	private static final Comparator<Vector3> LOWER_Z_COMPARATOR = new Comparator<Vector3>() {
		@Override
		public int compare(Vector3 a, Vector3 b) {
			double diff = a.z - b.z;

			if (diff > 0) {
				return 1;
			} else if (diff < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	};

}
