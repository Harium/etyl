package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class HorizontalAnimation extends SingleIntervalAnimation {

    {
        startValue = UNDEFINED;
    }

    public HorizontalAnimation(Layer target) {
        super(target);
    }

    public HorizontalAnimation(long delay, long time) {
        super(delay, time);
    }

    public HorizontalAnimation(Layer target, long time) {
        super(target, time);
    }

    @Override
    protected void update(double value) {
        target.setX((int) value);
    }

    @Override
    public void onStart(long now) {
        super.onStart(now);
        if (startValue == UNDEFINED) {
            startValue = target.getX();
        }
    }

}
