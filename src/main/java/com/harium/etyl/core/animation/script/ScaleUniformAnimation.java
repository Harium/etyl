package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class ScaleUniformAnimation extends SingleIntervalAnimation {

    public ScaleUniformAnimation(long time) {
        super(time);
    }

    public ScaleUniformAnimation(long delay, long time) {
        super(delay, time);
    }

    public ScaleUniformAnimation(Layer target) {
        super(target);
    }

    public ScaleUniformAnimation(Layer target, long time) {
        super(target, time);
    }

    @Override
    public void update(double value) {
        target.setScale(value);
    }

}
