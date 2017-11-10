package com.harium.etyl.ui.label;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Label;
import com.harium.etyl.ui.theme.Theme;
import com.harium.etyl.ui.theme.ThemeManager;
import com.harium.etyl.commons.layer.GeometricLayer;
import com.harium.etyl.layer.TextLayer;

/**
 * @author yuripourre
 */

public class TextLabel extends Label {

    protected TextLayer layer;

    public TextLabel(int x, int y) {
        super(x, y);
        this.layer = new TextLayer(x, y, "");
    }

    public TextLabel(int x, int y, int w) {
        super(x, y, w);
        this.layer = new TextLayer(x, y, "");
    }

    public TextLabel(int x, int y, String text) {
        super(x, y);
        this.layer = new TextLayer(x, y, text);
    }

    public TextLabel(String text) {
        this(0, 0, text);
    }

    public TextLabel(String text, float size) {
        this(0, 0, text);
        layer.setSize(size);
    }

    @Override
    public void setX(int x) {
        this.x = x;
        this.layer.setX(x);
    }

    @Override
    public void setY(int y) {
        this.y = y;
        this.layer.setY(y);
    }

    @Override
    public void updateEvent(GUIEvent event) {
        switch (event) {

            case LOST_FOCUS:
                onFocus = false;
                break;

            case GAIN_FOCUS:
                onFocus = true;
                break;

            default:
                break;

        }
    }

    @Override
    public void draw(Graphics g) {
        Theme theme = ThemeManager.getInstance().getTheme();

        g.setFont(theme.getFont());
        g.setFont(g.getFont().deriveFont(layer.getStyle()));
        g.setFont(g.getFont().deriveFont(layer.getSize()));

        if (!onFocus) {
            g.setColor(theme.getTextColor());
        } else {
            g.setColor(theme.getButtonOnFocus());
        }

        //Label is always in center
        if (!theme.isShadow()) {
            g.drawString(layer.getText(), bx, by, bw, bh);
        } else {
            g.drawStringShadow(layer.getText(), bx, by, bw, bh, theme.getShadowColor());
        }

    }

    @Override
    public GUIEvent updateKeyboard(KeyEvent event) {

        if (event.isKeyDown(KeyEvent.VK_TAB)) {
            return GUIEvent.NEXT_COMPONENT;
        }

        return GUIEvent.NONE;
    }

    public String getText() {
        return this.layer.getText();
    }

    /**
     * @param text
     */
    public void setText(String text) {
        this.layer.setText(text);
    }

    public float getFontSize() {
        return this.layer.getSize();
    }

    /**
     * @param size
     */
    public void setFontSize(float size) {
        this.layer.setSize(size);
    }

    /**
     * @param border
     */
    public void setBorder(boolean border) {
        this.layer.setBorder(border);
    }

    /**
     * @param borderColor
     */
    public void setBorderColor(Color borderColor) {
        this.layer.setBorderColor(borderColor);
    }

    /**
     * @param borderWidh
     */
    public void setBorderWidth(int borderWidh) {
        this.layer.setBorderWidth(borderWidh);
    }

    @Override
    public void centralize(int x, int y, int w, int h) {
        layer.centralize(x, y, w, h);
    }

    @Override
    public void centralize(GeometricLayer layer) {
        layer.centralize(layer);
    }

    @Override
    public void centralizeX(GeometricLayer layer) {
        layer.centralizeX(layer);
    }

    @Override
    public int centralizeX(int startX, int endX) {
        return layer.centralizeX(startX, endX);
    }

    @Override
    public void centralizeY(GeometricLayer layer) {
        layer.centralizeY(layer);
    }

    @Override
    public int centralizeY(int startY, int endY) {
        return layer.centralizeY(startY, endY);
    }

    public TextLayer getLayer() {
        return layer;
    }

    public int getFontStyle() {
        return layer.getStyle();
    }

    public void setFontStyle(int fontStyle) {
        layer.setStyle(fontStyle);
    }

}