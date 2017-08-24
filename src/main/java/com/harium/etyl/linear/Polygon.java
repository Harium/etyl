package com.harium.etyl.linear;

import com.harium.etyl.commons.Drawable;
import com.harium.etyl.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Polygon
 * Forked from: http://algs4.cs.princeton.edu/91primitives/Polygon.java.html
 */
public class Polygon implements Drawable {

    private boolean isClosed = true;
    private int size;
    private Point2D[] array;

    public Polygon() {
        super();
        reset();
    }

    public void reset() {
        size = 0;
        array = new Point2D[4];
    }

    @Override
    public void draw(Graphics g) {
        if (size == 0) {
            return;
        }

        final int pointRadius = 3;

        Point2D firstPoint = array[0];

        g.fillCircle(firstPoint, pointRadius);
        Point2D lastPoint = firstPoint;

        for (int i = 1; i < size; i++) {
            Point2D point = array[i];

            g.drawLine(lastPoint, point);
            g.fillCircle(point, pointRadius);
            lastPoint = point;
        }

        // Draw the last line
        if (isClosed) {
            g.drawLine(lastPoint, firstPoint);
        }
    }

    // Double the size of array
    private void resize() {
        Point2D[] temp = new Point2D[2 * size + 1];
        for (int i = 0; i <= size; i++) temp[i] = array[i];
        array = temp;
    }

    /**
     * @return the number of points
     */
    public int size() {
        return size;
    }

    /**
     * Add the point p to the end of the polygon
     *
     * @param p Point2D
     */
    public void add(double x, double y) {
        add(new Point2D(x, y));
    }

    /**
     * Add the point p to the end of the polygon
     *
     * @param p Point2D
     */
    public void add(Point2D p) {
        if (size >= array.length - 1) resize(); // resize array if needed
        array[size++] = p;                      // add point
        array[size] = array[0];                 // close polygon
    }

    /**
     * Method to calculate the perimeter
     *
     * @return the perimeter of polygon
     */
    public double perimeter() {
        double sum = 0.0;
        for (int i = 0; i < size; i++)
            sum = sum + array[i].distance(array[i + 1]);
        return sum;
    }

    /**
     * Method to calculate the area
     *
     * @return the signed area of polygon
     */
    public double area() {
        double sum = 0.0;
        for (int i = 0; i < size; i++) {
            sum = sum + (array[i].x * array[i + 1].y) - (array[i].y * array[i + 1].x);
        }
        return 0.5 * sum;
    }

    /**
     * Check if the polygon contains the point p
     * if p is on boundary then 0 or 1 is returned, and p is in exactly one point of every partition of plane
     * Reference: http://exaflop.org/docs/cgafaq/cga2.html
     *
     * @param p
     * @return if polygon contains the point
     */
    public boolean contains2(Point2D p) {
        int crossings = 0;
        for (int i = 0; i < size; i++) {
            int j = i + 1;
            boolean cond1 = (array[i].y <= p.y) && (p.y < array[j].y);
            boolean cond2 = (array[j].y <= p.y) && (p.y < array[i].y);
            if (cond1 || cond2) {
                // need to cast to double
                if (p.x < (array[j].x - array[i].x) * (p.y - array[i].y) / (array[j].y - array[i].y) + array[i].x)
                    crossings++;
            }
        }
        if (crossings % 2 == 1) return true;
        else return false;
    }

    public boolean contains(double x, double y) {
        return contains(new Point2D(x, y));
    }

    /**
     * Check if the polygon contains the point p
     * Reference: http://softsurfer.com/Archive/algorithm_0103/algorithm_0103.htm
     *
     * @param p
     * @return if polygon contains the point
     */
    public boolean contains(Point2D p) {
        int winding = 0;
        for (int i = 0; i < size; i++) {
            int ccw = Point2D.ccw(array[i], array[i + 1], p);
            if (array[i + 1].y > p.y && p.y >= array[i].y)  // upward crossing
                if (ccw == +1) winding++;
            if (array[i + 1].y <= p.y && p.y < array[i].y)  // downward crossing
                if (ccw == -1) winding--;
        }
        return winding != 0;
    }

    /**
     * Generates a mutable list with copies of points
     *
     * @return a copy of the points as a list
     */
    public List<Point2D> asList() {
        List<Point2D> list = new ArrayList<Point2D>();
        for (int i = 0; i < size; i++) {
            list.add(new Point2D(array[i]));
        }
        return list;
    }

    public String toString() {
        if (size == 0) return "[ ]";
        String s = "";
        s = s + "[ ";
        for (int i = 0; i <= size; i++)
            s = s + array[i] + " ";
        s = s + "]";
        return s;
    }
}
