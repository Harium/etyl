package com.harium.etyl.effects.particle;

import com.harium.etyl.commons.particle.Particle;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;


public class BasicParticle extends Particle {

    private ImageLayer layer;

    public BasicParticle(int x, int y) {
        super(x, y);

        layer = new ImageLayer(x, y, "particle.png");
    }

    public void draw(Graphics g) {
        layer.draw(g);
    }

    public void setX(int x) {
        this.x = x;
        layer.setX(x);
    }

    public void setY(int y) {
        this.y = y;
        layer.setY(y);
    }

    @Override
    public void update(long now) {
        super.update(now);
        layer.setLocation((int)x, (int)y);
    }

    public ImageLayer getLayer() {
        return layer;
    }
}
