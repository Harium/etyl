package com.harium.etyl.effects;

import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.animation.script.OpacityAnimation;
import com.harium.etyl.core.effect.GlobalEffect;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.i18n.DefaultDictionary;
import com.harium.etyl.i18n.Dictionary;
import com.harium.etyl.i18n.LanguageModule;

/**
 * @author yuripourre
 */

public class GenericFullScreenEffect extends GlobalEffect {

    private Dictionary dictionary;

    private static final int RECT_W = 360;
    private static final int RECT_H = 50;
    private final int RECT_X = w / 2 - RECT_W / 2;
    private final int RECT_Y = h / 2 - h / 4;

    public GenericFullScreenEffect(int x, int y, int w, int h) {
        super(x, y, w, h);

        dictionary = DefaultDictionary.getInstance();

        // 3 seconds of animation
        script = new OpacityAnimation(this, 3000);
        script.setInterval(255, 0);
    }

    @Override
    public void draw(Graphics g) {

        g.setOpacity(opacity);

        g.setColor(Color.BLACK);
        g.fillArc(RECT_X - RECT_H / 2, RECT_Y, RECT_H, RECT_H, 90, 180);
        g.fillRect(RECT_X, RECT_Y, RECT_W, RECT_H);
        g.fillArc(RECT_X + RECT_W - RECT_H / 2, RECT_Y, RECT_H, RECT_H, 270, 180);

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(20f));

        String sentence = dictionary.getText(LanguageModule.getInstance().getLanguage(), DefaultDictionary.MESSAGE_FULLSCREEN);

        g.drawStringShadow(sentence, RECT_X, RECT_Y, RECT_W, RECT_H, Color.BLACK);

        g.setOpacity(255);
    }

}

