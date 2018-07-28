package com.harium.etyl.core.graphics.stroke;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.geometry.Line2;
import com.harium.etyl.geometry.Polygon;

public interface Stroke {

    void drawRect(Graphics g, int x, int y, int w, int h);

    void drawLine(Graphics g, int x1, int y1, int x2, int y2);

    void drawLine(Graphics g, Line2 line);

    void drawPolygon(Graphics g, Polygon polygon);

    float getWidth();

    void setWidth(float width);

}
