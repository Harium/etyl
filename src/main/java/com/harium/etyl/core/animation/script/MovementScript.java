package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class MovementScript extends LayerAnimation {

    protected double startX = 0;
    protected double endX = 0;

    protected double startY = 0;
    protected double endY = 0;

    public MovementScript(Layer target) {
        super(target);

        this.startX = target.getX();
        this.startY = target.getY();
    }

    public MovementScript(Layer target, long time) {
        this(target);

        this.duration = time;
    }

    public void calculate(double factor) {
        double valueX = interpolator.interpolate(startX, endX, factor);
        double valueY = interpolator.interpolate(startY, endY, factor);

        target.setLocation((int) valueX, (int) valueY);
    }

    public MovementScript from(int x, int y) {
        this.startX = x;
        this.startY = y;

        return this;
    }

    public MovementScript to(int x, int y) {
        this.endX = x;
        this.endY = y;

        return this;
    }

}
