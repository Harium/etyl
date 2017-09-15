package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class VerticalAnimation extends SingleIntervalAnimation {

    {
        startValue = UNDEFINED;
    }

    public VerticalAnimation(long time) {
        super(time);
    }

    public VerticalAnimation(Layer target) {
        super(target);
    }

    public VerticalAnimation(Layer target, long time) {
        super(target, time);
    }

    @Override
    protected void update(double value) {
        target.setY((int) value);
    }

    @Override
    public void onStart(long now) {
        super.onStart(now);
        if (startValue == UNDEFINED) {
            startValue = target.getY();
        }
    }

}
