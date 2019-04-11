package com.harium.etyl.animation.pivot;

import com.harium.etyl.layer.ImageLayer;
import com.harium.etyl.geometry.Point2D;

import java.util.*;
import java.util.Map.Entry;

public class Part extends ImageLayer {

    private String name;
    private Point2D anchor;
    private Point2D fixed = new Point2D();
    protected boolean flipped = false;

    protected List<Point2D> points = new ArrayList<Point2D>();
    protected Map<Point2D, Set<Part>> parts = new LinkedHashMap<Point2D, Set<Part>>();

    public Part(int x, int y) {
        super(x, y);
        fixed.setLocation(x, y);
        anchor = new Point2D(0, 0);
        addPoint(anchor);
    }

    public Part(int x, int y, String path) {
        super(x, y, path);
        fixed.setLocation(x, y);
        anchor = new Point2D(w / 2, h / 2);
        addPoint(anchor);
    }

    public Part(String path) {
        this(0, 0, path);
    }

    public void addPoint(Point2D point) {
        points.add(point);
        parts.put(point, new HashSet<Part>());
    }

    public void setAnchor(int x, int y) {
        anchor.setLocation(x, y);
    }

    public void attach(Point2D point, Part part) {
        parts.get(point).add(part);
        movePart(part, point);
    }

    private void movePart(Part part, Point2D point) {
        part.moveTo(x + point.x, y + point.y);
    }

    public void moveTo(double x, double y) {
        fixed.setLocation(x, y);
        setLocation((int) (x - anchor.x), (int) (y - anchor.y));

        Iterator<Map.Entry<Point2D, Set<Part>>> iterator = parts.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Point2D, Set<Part>> entry = iterator.next();
            Point2D point = entry.getKey();

            for (Part part : entry.getValue()) {
                movePart(part, point);
            }
        }
    }

    public void rotate(double angle) {
        Point2D center = new Point2D(w / 2, h / 2);
        double diffAngle = angle - this.angle;

        //TODO Move all parts based on delta (after rotate)
        //dx = anchorRotated.getX()-anchor.getX()
        //dy = anchorRotated.getX()-anchor.getX()
        //Avoid reset Anchor

        rotateParts(center, diffAngle);
        resetAnchor();

        setAngle(angle);
    }

    private void resetAnchor() {
        setX((int) (fixed.x - anchor.x));
        setY((int) (fixed.y - anchor.y));
        moveParts();
    }

    private void rotateParts(Point2D pivot, double diffAngle) {
        Iterator<Map.Entry<Point2D, Set<Part>>> iterator = parts.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Point2D, Set<Part>> entry = iterator.next();
            Point2D point = entry.getKey();
            point.rotate(pivot, diffAngle);

            Set<Part> parts = entry.getValue();
            for (Part part : parts) {
                part.rotate(part.getAngle() + diffAngle);
                movePart(part, point);
            }
        }
    }

    private void moveParts() {
        Iterator<Map.Entry<Point2D, Set<Part>>> iterator = parts.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Point2D, Set<Part>> entry = iterator.next();
            Point2D point = entry.getKey();

            Set<Part> parts = entry.getValue();
            for (Part part : parts) {
                movePart(part, point);
            }
        }
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public Collection<Set<Part>> getParts() {
        return parts.values();
    }

    public Set<Part> getParts(Point2D point) {
        return parts.get(point);
    }

    public Point2D getAnchor() {
        return anchor;
    }

    public Point2D getFixed() {
        return fixed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void flip() {
        scaleX = -scaleX;
        angle = -angle;

        double center = getW() / 2;

        Iterator<Map.Entry<Point2D, Set<Part>>> iterator = parts.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Point2D, Set<Part>> entry = iterator.next();
            Point2D point = entry.getKey();

            double dx = point.x - center;
            point.x = (point.x - dx * 2);

            Set<Part> ps = entry.getValue();

            for (Part part : ps) {
                part.flip();
            }
        }
        moveParts();
    }

}