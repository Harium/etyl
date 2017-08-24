package com.harium.etyl.linear;


/**
 * @author yuripourre
 */
public class Point2D {
    protected String name = "";

    protected double x = 0d;
    protected double y = 0d;

    protected int color = 0x000000;//Black Hex Value

    public Point2D() {
        super();
        setLocation(0, 0);
    }

    public Point2D(double x, double y, int color) {
        this(x, y);
        this.color = color;
    }

    public Point2D(double x, double y, String name) {
        this(x, y);
        this.name = name;
    }

    public Point2D(double x, double y) {
        super();
        setLocation(x, y);
    }

    public Point2D(Point2D point) {
        super();
        setLocation(point.x, point.y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }

    public void setOffset(double x, double y) {
        setOffsetX(x);
        setOffsetY(y);
    }

    public void setOffsetX(double x) {
        this.x += x;
    }

    public void setOffsetY(double y) {
        this.y += y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double angle(Point2D point) {
        return angle(point.getX(), point.getY());
    }

    public double angle(double px, double py) {
        return angleXY(px, py);
    }

    public double angleXY(double px, double py) {

        double deltaX = px - x;
        double deltaY = py - y;

        double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

        return angleInDegrees;
    }

    public static double angle(double px, double py, double qx, double qy) {

        double deltaX = qx - px;
        double deltaY = qy - py;

        double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

        return angleInDegrees;
    }

    public void rotate(double cx, double cy, double degreeAngle) {
        double angle = Math.toRadians(degreeAngle);
        double nx = cx + (x - cx) * Math.cos(angle) - (y - cy) * Math.sin(angle);
        double ny = cy + (x - cx) * Math.sin(angle) + (y - cy) * Math.cos(angle);

        x = nx;
        y = ny;
    }

    public void rotate(Point2D p, double degreeAngle) {
        rotate(p.getX(), p.getY(), degreeAngle);
    }

    public static boolean isRightTurn(Point2D a, Point2D b, Point2D c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x) > 0;
    }

    public double distance(Point2D point) {

        return distance(point.getX(), point.getY());
    }

    public double distance(double px, double py) {
        return distance(px, py, this.x, this.y);
    }

    public static double distance(double px, double py, double qx, double qy) {
        double difX = px - qx;
        double difY = py - qy;

        double dist = Math.sqrt(Math.pow(difX, 2) + Math.pow(difY, 2));

        return dist;
    }

    public Point2D distantPoint(Point2D b, double distance) {

        double deltaX = x - b.getX();
        double deltaY = y - b.getY();

        double dist = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        deltaX /= dist;
        deltaY /= dist;

        Point2D c = new Point2D(x - distance * deltaX, y - distance * deltaY);

        return c;
    }

    /**
     * Check if a->b->c is a counter-clockwise turn
     *
     * @param a
     * @param b
     * @param c
     * @return +1 if counter-clockwise, -1 if clockwise, 0 if collinear
     */
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
        if (area2 < 0) return -1;
        else if (area2 > 0) return +1;
        else return 0;
    }

    /**
     * Check if a, b and c are collinear
     *
     * @param a
     * @param b
     * @param c
     * @return they are collinear
     */
    public static boolean collinear(Point2D a, Point2D b, Point2D c) {
        return ccw(a, b, c) == 0;
    }

    /**
     * Check if c is between a and b?
     * Reference: O' Rourke p. 32
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static boolean between(Point2D a, Point2D b, Point2D c) {
        if (ccw(a, b, c) != 0) return false;
        if (a.x == b.x && a.y == b.y) {
            return a.x == c.x && a.y == c.y;
        } else if (a.x != b.x) {
            // ab not vertical
            return (a.x <= c.x && c.x <= b.x) || (a.x >= c.x && c.x >= b.x);
        } else {
            // ab not horizontal
            return (a.y <= c.y && c.y <= b.y) || (a.y >= c.y && c.y >= b.y);
        }
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static Point2D clone(Point2D point) {
        return new Point2D(point.getX(), point.getY());
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
        Point2D other = (Point2D) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }

}
