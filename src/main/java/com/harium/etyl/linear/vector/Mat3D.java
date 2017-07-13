package com.harium.etyl.linear.vector;

/**
 * 
 * Based on js-aruco code: http://code.google.com/p/js-aruco/source/browse/trunk/src/posit2.js
 * 
 */

public class Mat3D {

	public double[][] m;

	public Mat3D() {
		super();

		this.m = new double[3][3];
	}

	public static Mat3D clone(Mat3D b) {

		Mat3D matrix = new Mat3D();

		double[][] a = b.m;

		matrix.m[0][0] = a[0][0];
		matrix.m[0][1] = a[0][1];
		matrix.m[0][2] = a[0][2];
		matrix.m[1][0] = a[1][0];
		matrix.m[1][1] = a[1][1];
		matrix.m[1][2] = a[1][2];
		matrix.m[2][0] = a[2][0];
		matrix.m[2][1] = a[2][1];
		matrix.m[2][2] = a[2][2];

		return matrix;

	}

	public Mat3D copy(Mat3D b) {

		double[][] a = b.m;

		this.m[0][0] = a[0][0];
		this.m[0][1] = a[0][1];
		this.m[0][2] = a[0][2];
		this.m[1][0] = a[1][0];
		this.m[1][1] = a[1][1];
		this.m[1][2] = a[1][2];
		this.m[2][0] = a[2][0];
		this.m[2][1] = a[2][1];
		this.m[2][2] = a[2][2];

		return this;
	}

	public static Mat3D fromRows(Vec3D a, Vec3D b, Vec3D c) {

		Mat3D matrix = new Mat3D();

		double[][] m = matrix.m;

		m[0][0] = a.v[0];
		m[0][1] = a.v[1];
		m[0][2] = a.v[2];
		m[1][0] = b.v[0];
		m[1][1] = b.v[1];
		m[1][2] = b.v[2];
		m[2][0] = c.v[0];
		m[2][1] = c.v[1];
		m[2][2] = c.v[2];

		return matrix;
	}

	public static Mat3D fromDiagonal(Vec3D a){

		Mat3D matrix = new Mat3D();

		double[][] m = matrix.m;

		m[0][0] = a.v[0];
		m[1][1] = a.v[1];
		m[2][2] = a.v[2];

		return matrix;
	}

	public static Mat3D transpose(Mat3D a){

		Mat3D matrix = new Mat3D();

		double[][] m = matrix.m;

		m[0][0] = a.m[0][0];
		m[0][1] = a.m[1][0];
		m[0][2] = a.m[2][0];
		m[1][0] = a.m[0][1];
		m[1][1] = a.m[1][1];
		m[1][2] = a.m[2][1];
		m[2][0] = a.m[0][2];
		m[2][1] = a.m[1][2];
		m[2][2] = a.m[2][2];

		return matrix;
	}

	public static Mat3D mult(Mat3D a, Mat3D b){

		Mat3D matrix = new Mat3D();

		double[][] m = matrix.m;

		m[0][0] = a.m[0][0] * b.m[0][0] + a.m[0][1] * b.m[1][0] + a.m[0][2] * b.m[2][0];
		m[0][1] = a.m[0][0] * b.m[0][1] + a.m[0][1] * b.m[1][1] + a.m[0][2] * b.m[2][1];
		m[0][2] = a.m[0][0] * b.m[0][2] + a.m[0][1] * b.m[1][2] + a.m[0][2] * b.m[2][2];
		m[1][0] = a.m[1][0] * b.m[0][0] + a.m[1][1] * b.m[1][0] + a.m[1][2] * b.m[2][0];
		m[1][1] = a.m[1][0] * b.m[0][1] + a.m[1][1] * b.m[1][1] + a.m[1][2] * b.m[2][1];
		m[1][2] = a.m[1][0] * b.m[0][2] + a.m[1][1] * b.m[1][2] + a.m[1][2] * b.m[2][2];
		m[2][0] = a.m[2][0] * b.m[0][0] + a.m[2][1] * b.m[1][0] + a.m[2][2] * b.m[2][0];
		m[2][1] = a.m[2][0] * b.m[0][1] + a.m[2][1] * b.m[1][1] + a.m[2][2] * b.m[2][1];
		m[2][2] = a.m[2][0] * b.m[0][2] + a.m[2][1] * b.m[1][2] + a.m[2][2] * b.m[2][2];

		return matrix;
	}

	public static Vec3D multVector(Mat3D m, Vec3D a){

		return new Vec3D(
				m.m[0][0] * a.v[0] + m.m[0][1] * a.v[1] + m.m[0][2] * a.v[2],
				m.m[1][0] * a.v[0] + m.m[1][1] * a.v[1] + m.m[1][2] * a.v[2],
				m.m[2][0] * a.v[0] + m.m[2][1] * a.v[1] + m.m[2][2] * a.v[2]);
	}

	public Vec3D column(int index){
		
		double[][] m = this.m;

		return new Vec3D( m[0][index], m[1][index], m[2][index] );
	}

	public Vec3D row(int index){
		double[][] m = this.m;

		return new Vec3D( m[index][0], m[index][1], m[index][2] );
	}

}
