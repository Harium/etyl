package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public abstract class RepeatedAnimation extends LayerAnimation {

    // It repeats a loop inside the animation
    protected int repeatTimes = 1;

    public RepeatedAnimation(Layer target) {
        super(target);
    }

    public RepeatedAnimation(Layer target, long time) {
        super(target, time);
    }

    public RepeatedAnimation repeat(int times) {
        repeatTimes = times;
        return this;
    }

}
