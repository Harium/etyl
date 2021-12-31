package com.harium.etyl.geometry.drawer;

import com.badlogic.gdx.math.Vector2;
import com.harium.etyl.commons.graphics.Graphics;
import com.harium.etyl.geometry.Polygon;

public class PolygonDrawer {

    private static final int POINT_RADIUS = 3;

    public static void draw(Graphics g, Polygon polygon) {
        if (polygon.getList().isEmpty()) {
            return;
        }

        Vector2 firstPoint = polygon.getList().get(0);

        fillCircle(g, firstPoint);
        Vector2 lastPoint = firstPoint;

        for (int i = 1; i < polygon.getList().size(); i++) {
            Vector2 point = polygon.getList().get(i);

            drawLine(g, lastPoint, point);
            fillCircle(g, point);
            lastPoint = point;
        }

        // Draw the last line
        if (polygon.isClosed()) {
            drawLine(g, lastPoint, firstPoint);
        }
    }

    private static void fillCircle(Graphics g, Vector2 firstPoint) {
        g.fillCircle(firstPoint.x, firstPoint.y, POINT_RADIUS);
    }

    private static void drawLine(Graphics g, Vector2 a, Vector2 b) {
        g.drawLine(a.x, a.y, b.x, b.y);
    }

}
