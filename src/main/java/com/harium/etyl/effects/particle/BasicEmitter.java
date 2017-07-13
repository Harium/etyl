package com.harium.etyl.effects.particle;

import com.harium.etyl.commons.particle.Emitter;
import com.harium.etyl.core.graphics.Graphics;

import java.awt.*;
import java.util.Random;

public class BasicEmitter extends Emitter<BasicParticle> {

    protected float particleSpeed = 10;
    protected float angle = 0;
    protected float variance = 360;
    protected int radius = 15;

    public BasicEmitter(int x, int y) {
        super(x, y);

        maxParticles = 300;
        particleRate = 100;
        setSpeed(20);
    }

    public BasicEmitter(int x, int y, int radius) {
        this(x, y);
        this.radius = radius;
    }

    @Override
    public void drawEmitter(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillCircle(x, y, 30);
    }

    @Override
    protected BasicParticle createParticle(long now) {
        double rad = new Random().nextInt((int) (variance * 2)) - variance;
        rad -= angle;
        rad += 360;
        rad %= 360;

        rad = Math.toRadians(rad);

        float xVel = (float) Math.cos(rad);
        float yVel = (float) Math.sin(rad);

        BasicParticle particle = new BasicParticle(x - radius / 2, y - radius / 2);
        particle.setxVelocity(xVel * particleSpeed);
        particle.setyVelocity(yVel * particleSpeed);

        return particle;
    }

    @Override
    protected void drawParticle(BasicParticle particle, Graphics g) {
        particle.getLayer().simpleDraw(g);
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setVariance(int variance) {
        this.variance = variance;
    }
}
