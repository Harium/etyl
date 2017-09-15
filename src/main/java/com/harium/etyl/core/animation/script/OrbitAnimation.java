package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class OrbitAnimation extends SingleIntervalAnimation {

    private float centerX = 0, centerY = 0;
    private float tx, ty, tw, th;

    public OrbitAnimation(long time) {
        super(time);
    }

    public OrbitAnimation(long delay, long time) {
        super(delay, time);
    }

    public OrbitAnimation(Layer target, long time) {
        super(target, time);
    }

    public OrbitAnimation(Layer target) {
        super(target);
    }

    @Override
    public OrbitAnimation during(long duration) {
        super.during(duration);
        return this;
    }

    public OrbitAnimation around(float centerX, float centerY) {
        this.centerX = centerX;
        this.centerY = centerY;

        return this;
    }

    @Override
    public void setTarget(Layer target) {
        super.setTarget(target);

        tx = target.getX();
        ty = target.getY();
        tw = target.getW() / 2;
        th = target.getH() / 2;
    }

    @Override
    public void update(double value) {

        double angle = value * Math.PI / 180;

        double s = Math.sin(angle);
        double c = Math.cos(angle);

        // translate point back to origin:
        float px = tx + tw - centerX;
        float py = ty + th - centerY;

        // rotate point
        double xnew = px * c - py * s;
        double ynew = px * s + py * c;

        // translate point back:
        target.setX((int) (xnew + centerX - tw));
        target.setY((int) (ynew + centerY - th));
    }

}
