package com.harium.etyl.effects.particle.fireworks;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.effects.particle.BasicEmitter;

import java.awt.*;

public class FireworksEmitter extends BasicEmitter {

    public FireworksEmitter(int x, int y) {
        super(x, y, 20);
        variance = 360;
    }

    @Override
    public void drawEmitter(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, radius, radius);
    }

}
