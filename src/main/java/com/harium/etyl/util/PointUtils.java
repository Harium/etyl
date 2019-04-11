package com.harium.etyl.util;

import com.harium.etyl.geometry.Point2D;
import com.harium.etyl.geometry.Point3D;

public class PointUtils {

    public static Point3D distantPoint(Point3D a, Point3D b, double distanceFromA) {
        double deltaX = a.x - b.x;
        double deltaY = a.y - b.y;
        double deltaZ = a.z - b.z;

        double dist = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

        deltaX /= dist;
        deltaY /= dist;
        deltaZ /= dist;

        Point3D c = new Point3D(a.x - distanceFromA * deltaX, a.y - distanceFromA * deltaY, a.z - distanceFromA * deltaZ);

        return c;
    }

    public static Point2D distantPoint(Point2D a, Point2D b, double distanceFromA) {

        double deltaX = a.x - b.x;
        double deltaY = a.y - b.y;

        double dist = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        deltaX /= dist;
        deltaY /= dist;

        Point2D c = new Point2D(a.x - distanceFromA * deltaX, a.y - distanceFromA * deltaY);

        return c;
    }

}
