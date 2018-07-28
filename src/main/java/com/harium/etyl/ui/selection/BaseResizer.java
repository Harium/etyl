package com.harium.etyl.ui.selection;

import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.commons.graphics.stroke.DashedStroke;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.core.input.mouse.MouseStateChanger;


public class BaseResizer<T extends Layer> extends Resizer<T> {

    private final DashedStroke dash = new DashedStroke();

    public BaseResizer(MouseStateChanger mouseStateChanger) {
        super(mouseStateChanger);
    }

    public void draw(Graphics g) {
        drawOverlay(g);

        if (!isSelected()) {
            return;
        }

        g.setColor(Color.BLACK);

        drawScaledRect(g, selected);

        if (!moveOnly) {
            for (int b = 0; b < 8; b++) {
                points[b].draw(g, offsetX, offsetY);
            }
        }
    }

    public void drawOverlay(Graphics g) {
        if (!overlay.isVisible()) {
            return;
        }

        g.setColor(Color.BLACK);
        g.setAlpha(60);
        fillScaledRect(g, overlay);
        g.resetOpacity();
    }

    private void drawScaledRect(Graphics g, Layer layer) {
        int sw = (int) (layer.getW() * layer.getScaleX());
        int sh = (int) (layer.getH() * layer.getScaleY());

        int oX = (int) (layer.getW() * (1 - layer.getScaleX())) / 2;
        int oY = (int) (layer.getH() * (1 - layer.getScaleY())) / 2;

        dash.drawRect(g, layer.getX() + oX + offsetX, layer.getY() + oY + offsetY, sw, sh);
    }

    private void fillScaledRect(Graphics g, Layer layer) {
        int sw = (int) (layer.getW() * layer.getScaleX());
        int sh = (int) (layer.getH() * layer.getScaleY());

        int oX = (int) (layer.getW() * (1 - layer.getScaleX())) / 2;
        int oY = (int) (layer.getH() * (1 - layer.getScaleY())) / 2;

        g.fillRect(layer.getX() + oX + offsetX, layer.getY() + oY + offsetY, sw, sh);
    }

}
