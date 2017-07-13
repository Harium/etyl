package com.harium.etyl.linear;

/**
 *  this class represents a simple circle. <br />
 *  it is used by the Delaunay Triangulation class. <br />
 *  <br />
 *  note that this class is immutable.
 *  
 *  @see DelaunayTriangulation
 */
public class Circle  {

	private Point3D center;
	private double radius;

	/**
	 * Constructor. <br />
	 * Constructs a new Circle.
	 * @param center Center of the circle.
	 * @param radius Radius of the circle.
	 */
	public Circle( Point3D c, double r ) {
		this.center = c;
		this.radius = r;
	}

	/**
	 * Copy Constructor. <br />
	 * Creates a new Circle with same properties of <code>circle</code>.
	 * @param circle Circle to clone.
	 */
	public Circle(Circle circle) {
		this.center = circle.center;
		this.radius = circle.radius;
	}

	public String toString() {
		return(new String(" Circle["+ center.toString() + "|" + radius + "|" + (int) Math.round(Math.sqrt(radius)) + "]"));
	}

	/**
	 * Gets the center of the circle.
	 * @return the center of the circle.
	 */
	public Point3D getCenter() {
		return center;
	}

	/**
	 * Gets the radius of the circle.
	 * @return the radius of the circle.
	 */
	public double getRadius() {
		return radius;
	}
}
