package com.harium.etyl.ui;

import com.harium.etyl.ui.base.BaseSlider;
import com.harium.etyl.ui.base.UIView;
import com.harium.etyl.ui.theme.ThemeManager;

/**
 * @author yuripourre
 */

public class Slider extends UIView {

    private BaseSlider slider;

    public Slider(int x, int y, int w, int h) {
        super(x, y, w, h);

        this.slider = ThemeManager.getInstance().getTheme().createSlider(x, y, w, h);
        delegateView(slider);
    }

    public void rebuild() {
        BaseSlider view = ThemeManager.getInstance().getTheme().createSlider(x, y, w, h);
        view.copy(delegatedView);

        delegateView(view);
    }

    public int getMinValue() {
        return (int) slider.getMinValue();
    }

    public void setMinValue(int minValue) {
        slider.setMinValue(minValue);
    }

    public int getMaxValue() {
        return (int) slider.getMaxValue();
    }

    public void setMaxValue(int maxValue) {
        slider.setMaxValue(maxValue);
    }

    public int getValue() {
        return (int) slider.getValue();
    }

    public void setValue(int value) {
        slider.setValue(value);
    }

}

