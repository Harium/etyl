package com.harium.etyl.ui.base;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.View;
import com.harium.etyl.ui.ViewGroup;
import com.harium.etyl.ui.style.Orientation;

public abstract class UIViewGroup extends UIView {

    public UIViewGroup(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void add(View component) {
        ((ViewGroup) delegatedView).add(component);
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        ((ViewGroup) delegatedView).setBounds(x, y, w, h);
    }

    @Override
    public void drawWithChildren(Graphics g) {
        ((ViewGroup) delegatedView).drawWithChildren(g);
    }

    public void setOrientation(Orientation orientation) {
        ((ViewGroup) delegatedView).setOrientation(orientation);
    }

    public void add(View view, float weight) {
        ((ViewGroup) delegatedView).add(view, weight);
    }

    public void setRowSize(int rowSize) {
        ((ViewGroup) delegatedView).setRowSize(rowSize);
    }

    @Override
    public void resize() {
        ((ViewGroup) delegatedView).resize();
    }

    public Orientation getOrientation() {
        return ((ViewGroup) delegatedView).getOrientation();
    }

    public void setShrink(boolean shrink) {
        ((ViewGroup) delegatedView).setShrink(shrink);
    }

    public boolean isShrink(boolean shrink) {
        return ((ViewGroup) delegatedView).isShrink();
    }

}
