package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public abstract class RepeatedScript extends LayerAnimation {

    // It repeats a loop inside the animation
    protected int repeatTimes = 1;

    public RepeatedScript(Layer target) {
        super(target);
    }

    public RepeatedScript(Layer target, long time) {
        super(target, time);
    }

    public RepeatedScript repeat(int times) {
        repeatTimes = times;
        return this;
    }

}
