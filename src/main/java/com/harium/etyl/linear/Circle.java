package com.harium.etyl.linear;


public class Circle {

    private Point3D center;
    private double radius;

    /**
     * Constructor. <br />
     * Constructs a new Circle.
     *
     * @param center Center of the circle.
     * @param radius Radius of the circle.
     */
    public Circle(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Copy Constructor. <br />
     * Creates a new Circle with same properties of <code>circle</code>.
     *
     * @param circle Circle to clone.
     */
    public Circle(Circle circle) {
        this.center = circle.center;
        this.radius = circle.radius;
    }

    public String toString() {
        return (new String(" Circle[" + center.toString() + "|" + radius + "|" + (int) Math.round(Math.sqrt(radius)) + "]"));
    }

    /**
     * Gets the center of the circle.
     *
     * @return the center of the circle.
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     * Gets the radius of the circle.
     *
     * @return the radius of the circle.
     */
    public double getRadius() {
        return radius;
    }
}
