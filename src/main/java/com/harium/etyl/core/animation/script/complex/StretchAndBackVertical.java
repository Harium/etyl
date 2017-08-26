package com.harium.etyl.core.animation.script.complex;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.script.StretchVerticalAnimation;

public class StretchAndBackVertical extends StretchVerticalAnimation {

    private StretchVerticalAnimation backToNormal;

    public StretchAndBackVertical(long time) {
        super(time);
        init();
    }

    public StretchAndBackVertical(long delay, long time) {
        super(delay, time);
        init();
    }

    public StretchAndBackVertical(Layer target, long time) {
        super(target, time);
        init();
    }

    private void init() {
        backToNormal = new StretchVerticalAnimation(target, duration);
        backToNormal.setInterval(endValue, startValue);
        addNext(backToNormal);
        backToNormal.addNext(this);
    }

    @Override
    public void setInterval(double startValue, double endValue) {
        super.setInterval(startValue, endValue);
        backToNormal.setInterval(endValue, startValue);
    }

    @Override
    public void onFinish(long now) {
        double offset = ((target.getH() / 2) * endValue);
        target.setY((int) (originalY - offset));
        super.onFinish(now);
    }

}
