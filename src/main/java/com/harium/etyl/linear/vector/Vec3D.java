package com.harium.etyl.linear.vector;

/**
 * 
 * Based on js-aruco code: http://code.google.com/p/js-aruco/source/browse/trunk/src/posit2.js
 * 
 */

public class Vec3D {

	public double[] v;
	
	private static final int X = 0;
	
	private static final int Y = 1;
	
	private static final int Z = 2;
	
	public Vec3D() {
		this(0,0,0);
	}
	
	public Vec3D(double x, double y, double z) {
		super();
		
		this.v = new double[3];
		
		v[X] = x;
		v[Y] = y;
		v[Z] = z;
		
	}
	
	public void copy(Vec3D a) {
		
		this.v[X] = a.v[X];
		
		this.v[Y] = a.v[Y];
		
		this.v[Z] = a.v[Z];
				
	}
	
	public void copy(double x, double y, double z) {
		
		this.v[X] = x;
		
		this.v[Y] = y;
		
		this.v[Z] = z;
				
	}

	public static Vec3D add(Vec3D a, Vec3D b) {
		
		double x = a.v[X] + b.v[X];
		double y = a.v[Y] + b.v[Y];
		double z = a.v[Z] + b.v[Z];
		
		Vec3D v = new Vec3D(x, y, z);

		return v;
	}

	public static Vec3D sub(Vec3D a, Vec3D b) {
		
		double x = a.v[X] - b.v[X];
		double y = a.v[Y] - b.v[Y];
		double z = a.v[Z] - b.v[Z];
		
		Vec3D v = new Vec3D(x, y, z);

		return v;
	}

	public static Vec3D mult(Vec3D a, Vec3D b) {
		
		double x = a.v[X] * b.v[X];
		double y = a.v[Y] * b.v[Y];
		double z = a.v[Z] * b.v[Z];
		
		Vec3D v = new Vec3D(x, y, z);

		return v;
	}

	public static Vec3D addScalar(Vec3D a, double b) {
		
		double x = a.v[X] + b;
		double y = a.v[Y] + b;
		double z = a.v[Z] + b;
		
		Vec3D v = new Vec3D(x, y, z);

		return v;
	}

	public static Vec3D multScalar(Vec3D a, double b) {
		
		double x = a.v[X] * b;
		double y = a.v[Y] * b;
		double z = a.v[Z] * b;
		
		Vec3D v = new Vec3D(x, y, z);

		return v;
	}

	public static double dot(Vec3D a, Vec3D b) {
		
		return a.v[X] * b.v[X] + a.v[Y] * b.v[Y] + a.v[Z] * b.v[Z];
	}

	public static Vec3D cross(Vec3D a, Vec3D b) {

		return new Vec3D(
				a.v[Y] * b.v[Z] - a.v[Z] * b.v[Y],
				a.v[Z] * b.v[X] - a.v[X] * b.v[Z],
				a.v[X] * b.v[Y] - a.v[Y] * b.v[X]);
	}
	
	public static void cross(Vec3D a, Vec3D b, Vec3D c) {

		c.v[X] = a.v[Y] * b.v[Z] - a.v[Z] * b.v[Y];
		c.v[Y] = a.v[Z] * b.v[X] - a.v[X] * b.v[Z];
		c.v[Z] = a.v[X] * b.v[Y] - a.v[Y] * b.v[X];		
	}
		
	public double normalize() {
		
		double len = Math.sqrt(this.square());

		if (len > 0.0) {
			v[X] /= len;
			v[Y] /= len;
			v[Z] /= len;
		}

		return len;
	}
		
	public double length() {
		return Math.sqrt(this.square());
	}

	public static Vec3D inverse(Vec3D a) {
		
		double x = 0;
		double y = 0;
		double z = 0;
		
		if (a.v[X] != 0.0) {
			x = 1.0 / a.v[X];
		}
		if (a.v[Y] != 0.0) {
			y = 1.0 / a.v[Y];
		}
		if (a.v[Z] != 0.0) {
			z = 1.0 / a.v[Z];
		}

		return new Vec3D(x, y, z);
	}

	public double square() {
		
		return v[X] * v[X] + v[Y] * v[Y] + v[Z] * v[Z];
	}

	public int minIndex() {
		
		return v[X] < v[Y]? (v[X] < v[Z]? 0: 2): (v[Y] < v[Z]? 1: 2);
	}

	public void setX(double x) {
		v[X] = x;
	}
	
	public void setY(double y) {
		v[Y] = y;
	}
	
	public void setZ(double z) {
		v[Z] = z;
	}
	
	public double getX() {
		return v[X];
	}

	public double getY() {
		return v[Y];
	}
	
	public double getZ() {
		return v[Z];
	}
	
}
