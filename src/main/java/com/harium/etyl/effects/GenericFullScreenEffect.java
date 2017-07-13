package com.harium.etyl.effects;

import java.awt.Color;

import com.harium.etyl.core.animation.script.OpacityAnimation;
import com.harium.etyl.core.effect.GlobalEffect;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.i18n.DefaultDictionary;
import com.harium.etyl.i18n.LanguageModule;

/**
 * 
 * @author yuripourre
 *
 */

public class GenericFullScreenEffect extends GlobalEffect {
	
	private DefaultDictionary dictionary;
		
	public GenericFullScreenEffect(int x, int y, int w, int h) {
		super(x, y, w, h);
			
		dictionary = new DefaultDictionary();
		
		//3 seconds animation
		script = new OpacityAnimation(this, 3000);
		script.setInterval(255, 0);
		
	}
	
	private final float rectW = 360;
	private final float rectH = 50;
	private final float rectX = w/2-rectW/2;
	private final float rectY = h/2-h/4;
	
	@Override
	public void draw(Graphics g) {
		
		g.setOpacity(opacity);
				
		g.setColor(Color.BLACK);
		g.fillArc(rectX-rectH/2, rectY, rectH, rectH, 90, 180);
		g.fillRect(rectX, rectY, rectW, rectH);
		g.fillArc(rectX+rectW-rectH/2, rectY, rectH, rectH, 270, 180);
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(20f));
		
		String sentence = dictionary.getText(LanguageModule.getInstance().getLanguage(), DefaultDictionary.MESSAGE_FULLSCREEN);
		
		g.drawStringShadow(sentence, rectX, rectY, rectW, rectH, Color.BLACK);
		
		g.setOpacity(255);
				
	}
		
}

