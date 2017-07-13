package com.harium.etyl.util;

import com.harium.etyl.linear.Point2D;
import com.harium.etyl.linear.Point3D;

public class PointUtils {
	
	public static Point3D distantPoint(Point3D a, Point3D b, double distanceFromA) {
		
		double deltaX = a.getX()-b.getX();
		double deltaY = a.getY()-b.getY();
		double deltaZ = a.getZ()-b.getZ();
		
		double dist = Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
		
		deltaX /= dist;
		deltaY /= dist;
		deltaZ /= dist;
				
		Point3D c = new Point3D(a.getX()-distanceFromA*deltaX, a.getY()-distanceFromA*deltaY, a.getZ()-distanceFromA*deltaZ);

		return c;
	}
	
	public static Point2D distantPoint(Point2D a, Point2D b, double distanceFromA) {
		
		double deltaX = a.getX()-b.getX();
		double deltaY = a.getY()-b.getY();
		
		double dist = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		
		deltaX /= dist;
		deltaY /= dist;
				
		Point2D c = new Point2D(a.getX()-distanceFromA*deltaX, a.getY()-distanceFromA*deltaY);

		return c;
	}
	
	public static double angle(double px, double py, double qx, double qy) {
		double deltaX = px - qx;
		double deltaY = py - qy;

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
		return angleInDegrees;
	}
	
}
