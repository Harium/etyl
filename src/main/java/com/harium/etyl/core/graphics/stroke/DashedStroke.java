package com.harium.etyl.core.graphics.stroke;

import com.harium.etyl.core.graphics.Graphics;

public class DashedStroke extends BaseStroke {

    private int dashSize = 6;
    private int dashSpacing = dashSize;

    protected int plot(Graphics g, int x, int y, int count) {
        int dash = count;

        if (dash < dashSize) {
            g.putPixel(x, y);
        }

        dash++;

        if (dash > dashSpacing + dashSize) {
            dash = 0;
        }

        return dash;
    }

    public int getDashSpacing() {
        return dashSpacing;
    }

    public int getDashSize() {
        return dashSize;
    }
}
