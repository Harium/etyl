package com.harium.etyl.core.animation.script.complex;

import com.harium.etyl.commons.layer.Layer;

public class VerticalShakeAnimation extends ShakeAnimation {

    private int initialY = 0;

    public VerticalShakeAnimation(Layer target) {
        super(target);
        repeatTimes = 2;
    }

    public VerticalShakeAnimation(Layer target, long time) {
        super(target, time);
        repeatTimes = 2;
    }

    @Override
    public void calculate(double factor) {
        double division = 1 / (double) repeatTimes;

        int interval = (int) (factor / division);

        double part = interval + 1 - (factor / division);

        double slice = part;

        if (interval % 2 == 0) {

            slice = 1 - slice;
        }

        double startValue = initialY - strength;
        double endValue = initialY + strength;
        double value = startValue + (endValue - startValue) * slice;

        target.setY((int) value);
    }

    @Override
    public void onStart(long now) {
        super.onStart(now);
        if (startValue == UNDEFINED) {
            startValue = target.getY();
        }
        initialY = startValue;
    }

    @Override
    public void onFinish(long now) {
        super.onFinish(now);
        initialY = startValue;
    }

}
