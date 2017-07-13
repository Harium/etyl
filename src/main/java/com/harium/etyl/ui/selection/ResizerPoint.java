package com.harium.etyl.ui.selection;

import com.harium.etyl.commons.event.MouseState;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.commons.layer.Layer;

public class ResizerPoint extends Layer {

    private MouseState state;

    public ResizerPoint(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    // Draw with half size
    public void draw(Graphics g) {
        draw(g, 0, 0);
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        int sw = (int) (getW() * getScaleX());
        int sh = (int) (getH() * getScaleY());

        int oX = (int) (getW() * (1 - getScaleX())) / 2;
        int oY = (int) (getH() * (1 - getScaleY())) / 2;

        g.fillRect(getX() + oX + offsetX, getY() + oY + offsetY, sw, sh);
    }

    public MouseState getState() {
        return state;
    }

    public void setState(MouseState state) {
        this.state = state;
    }

}
