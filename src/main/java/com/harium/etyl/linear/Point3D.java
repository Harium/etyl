package com.harium.etyl.linear;

public class Point3D {

	protected double x;
	protected double y;
	protected double z;

	public Point3D() {
		this(0,0,0);
	}
	
	public Point3D(double x, double y) {
		this(x,y,0);
	}

	public Point3D(double x, double y, double z) {
		super();

		this.x = x;
		this.y = y;
		this.z = z;				
	}

	public Point3D(Point3D point) {
		this(point.x, point.y, point.z);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void offsetX(double offset) {
		this.x += offset;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void offsetY(double offset) {
		this.y += offset;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public void offsetZ(double offset) {
		this.z += offset;
	}

	public void setCoordinates(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double angle(int px, int py) {
		return angle(x, px, y, py);		
	}

	public double angle(Point2D point) {
		return angle(x, point.getX(), y, point.getY());
	}

	public double angle2D(Point3D point) {
		return angle(x, point.getX(), y, point.getY());
	}

	public double angleXY(Point3D point) {
		return angleXY(point.getX(), point.getY());
	}
	
	public double angleXY(double px, double py) {
		double deltaX = px - x;
		double deltaY = py - y;

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

		return angleInDegrees;
	}
	
	public double angleXZ(Point3D point) {
		return angleXZ(point.getX(), point.getZ());
	}
	
	public double angleXZ(double px, double pz) {
		double deltaX = px - x;
		double deltaZ = pz - z;

		double angleInDegrees = Math.atan2(deltaZ, deltaX) * 180 / Math.PI;

		return angleInDegrees;
	}
	
	public double angleYZ(Point3D point) {
		return angleYZ(point.getX(), point.getZ());
	}
	
	public double angleYZ(double py, double pz) {
		double deltaY = py - y;
		double deltaZ = pz - z;

		double angleInDegrees = Math.atan2(deltaZ, deltaY) * 180 / Math.PI;

		return angleInDegrees;
	}

	protected double angle(double x, double px, double y, double py) {

		double deltaX = px - x;
		double deltaY = py - y;

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

		return angleInDegrees;		
	}
	
	public double distanceXY(Point3D p) {
		return distanceXY(p.x, p.y);
	}

	public double distanceXY(double px, double py) {
		return Math.sqrt((px - x) * (px - x) + (py - y) * (py - y));
	}
	
	public double distance(Point3D target) {
		double deltaX = x-target.getX();
		double deltaY = y-target.getY();
		double deltaZ = z-target.getZ();
		
		return Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
	}
		
	public Point3D distantPoint(Point3D target, double distance) {
		
		double deltaX = x-target.getX();
		double deltaY = y-target.getY();
		double deltaZ = z-target.getZ();
		
		double dist = Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
		
		deltaX /= dist;
		deltaY /= dist;
		deltaZ /= dist;
				
		Point3D c = new Point3D(x-distance*deltaX, y-distance*deltaY, z-distance*deltaZ);

		return c;
	}
	
	public Point3D sub(Point3D p) {
		return new Point3D(x-p.x, y-p.y, z-p.z);
	}
	
	@Override
	public String toString() {
		String text = "("+x+", "+y+", "+z+")"; 
		return text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
}
