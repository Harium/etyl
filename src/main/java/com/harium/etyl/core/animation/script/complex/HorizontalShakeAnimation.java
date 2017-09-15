package com.harium.etyl.core.animation.script.complex;

import com.harium.etyl.commons.layer.Layer;

public class HorizontalShakeAnimation extends ShakeAnimation {

    private int initialX = 0;

    public HorizontalShakeAnimation(Layer target) {
        super(target);
        repeatTimes = 2;
    }

    public HorizontalShakeAnimation(Layer target, long time) {
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

        double startValue = initialX - strength;

        double endValue = initialX + strength;

        double value = startValue + (endValue - startValue) * slice;

        target.setX((int) value);
    }

    @Override
    public void onStart(long now) {
        super.onStart(now);
        if (startValue == UNDEFINED) {
            startValue = target.getX();
        }
        initialX = target.getX();
    }

    @Override
    public void onFinish(long now) {
        super.onFinish(now);
        initialX = startValue;
    }


}
