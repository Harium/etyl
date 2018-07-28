package com.harium.etyl.ui.panel;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.View;

import java.awt.*;

public class ColoredTextPanel extends View {

    private Color backgroundcolor = Color.GRAY;
    private Color bordercolor = Color.BLACK;
    private float borderWidth = 4f;

    private String text = "Hello my friend stay awile and listen!";

    private int spacing = 0;
    private float fontSize = 20;

    public ColoredTextPanel(int x, int y, int w, int h) {
        super(x, y, w, h);

        //TODO Use Theme's default values
        style.padding.top = 5;
        style.padding.right = 4;
    }

    @Override
    public void updateEvent(GUIEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {

        g.setColor(backgroundcolor);
        g.fillRect(left(), top(), width(), height());

        g.setFont(g.getFont().deriveFont(fontSize));

        for (int i = 0; i < h / fontSize; i++) {

            switch (i % 4) {
                case 0:
                    g.setColor(Color.BLUE);
                    break;
                case 1:
                    g.setColor(Color.RED);
                    break;
                case 2:
                    g.setColor(Color.ORANGE);
                    break;
                default:
                    g.setColor(Color.GREEN);
                    break;
            }

            g.drawString(text, left(), top() + (int) (fontSize + (i * fontSize + spacing)));
        }

        g.getGraphics().setStroke(new BasicStroke(borderWidth));
        g.setColor(bordercolor);
        g.drawRect(left(), top(), width(), height());
        g.getGraphics().setStroke(new BasicStroke(1f));
    }

    @Override
    public GUIEvent updateMouse(PointerEvent event) {
        // TODO Auto-generated method stub
        return GUIEvent.NONE;
    }

    @Override
    public GUIEvent updateKeyboard(KeyEvent event) {
        // TODO Auto-generated method stub
        return GUIEvent.NONE;
    }

}