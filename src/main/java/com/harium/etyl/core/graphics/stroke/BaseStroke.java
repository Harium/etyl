package com.harium.etyl.core.graphics.stroke;

import com.badlogic.gdx.math.Vector2;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.geometry.Line2;
import com.harium.etyl.geometry.Polygon;

public abstract class BaseStroke implements Stroke {

    private float width = 1;

    public BaseStroke() {
        super();
    }

    public BaseStroke(int width) {
        super();
        this.width = width;
    }

    @Override
    public void drawLine(Graphics g, Line2 line) {
        drawLine(g, (int) line.getP1().x, (int) line.getP1().y, (int) line.getP2().x, (int) line.getP2().y);
    }

    @Override
    public void drawPolygon(Graphics g, Polygon polygon) {
        int i = 1;
        Vector2 next = polygon.getList().get(i);

        for (Vector2 point : polygon.getList()) {
            drawLine(g, (int) point.x, (int) point.y, (int) next.x, (int) next.y);
            i++;
            i %= polygon.size();
            next = polygon.getList().get(i);
        }
    }

    @Override
    public void drawRect(Graphics g, int x, int y, int w, int h) {
        int count = 0;

        for (int wi = 0; wi < width; wi++) {

            // Draw Left to Right
            for (int i = 0; i < w; i++) {
                count = plot(g, x + i, y + wi, count);
            }

            // Draw Right to Bottom-Right
            for (int i = 1; i < h - 1; i++) {
                count = plot(g, x + w, y + i + wi, count);
            }

            // Draw Bottom-Right to Left-Bottom
            for (int i = 0; i < w; i++) {
                count = plot(g, x + w - i, y + h + wi, count);
            }

            // Draw Left-Bottom to Top
            for (int i = 1; i < h - 1; i++) {
                count = plot(g, x, y + h - i + wi, count);
            }
        }
    }

    /**
     * Draw line based on Bresenham's Algorithm
     * Based on: https://rosettacode.org/wiki/Bitmap/Bresenham%27s_line_algorithm#Java
     *
     * @param g
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    @Override
    public void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        // delta of exact value and rounded value of the dependent variable
        int d = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        for (int wi = 0; wi < width; wi++) {

            int x = x1;
            int y = y1;
            int count = 0;

            if (dx >= dy) {
                while (true) {
                    count = plot(g, x, y + wi, count);

                    if (x == x2) {
                        break;
                    }
                    x += ix;
                    d += dy2;
                    if (d > dx) {
                        y += iy;
                        d -= dx2;
                    }
                }
            } else {
                while (true) {
                    count = plot(g, x, y + wi, count);

                    if (y == y2) {
                        break;
                    }
                    y += iy;
                    d += dx2;
                    if (d > dy) {
                        x += ix;
                        d -= dy2;
                    }
                }
            }
        }
    }

    protected abstract int plot(Graphics g, int x, int y, int count);

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
