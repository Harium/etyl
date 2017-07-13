package com.harium.etyl.linear;

import com.harium.etyl.commons.collision.CollisionDetector;
import com.harium.etyl.commons.math.EtyllicaMath;

public class Ellipse {

    protected Point2D center;

    protected int w = 1;
    protected int h = 1;

    protected double angle = 0;

    public Ellipse(int x, int y) {
        super();
        this.center = new Point2D(x, y);
    }

    public Ellipse(int x, int y, int w, int h) {
        this(x, y);
        this.w = w;
        this.h = h;
    }

    public Ellipse(int x, int y, int w, int h, double angle) {
        this(x, y, w, h);
        this.angle = angle;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean contains(double px, double py) {

        final double a = Math.toRadians(angle);

        final double cos = Math.cos(a);
        final double sin = Math.sin(a);

        double p = EtyllicaMath.sqr(cos * (px - center.getX()) + sin * (py - center.getY())) / (w * w);
        double q = EtyllicaMath.sqr(sin * (px - center.getX()) - cos * (py - center.getY())) / (h * h);

        return (p + q <= 1);
    }

    public boolean colideEllipsePoint(double px, double py) {
        return CollisionDetector.colideEllipsePoint(center.getX(), center.getY(), angle, w, h, px, py);
    }

}
