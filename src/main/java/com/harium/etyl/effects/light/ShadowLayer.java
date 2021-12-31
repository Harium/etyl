package com.harium.etyl.effects.light;

import java.awt.*;

import com.harium.etyl.commons.graphics.Graphics;
import com.harium.etyl.core.graphics.AWTGraphics;
import com.harium.etyl.layer.BufferedLayer;

public class ShadowLayer extends BufferedLayer {

	private Color color = Color.BLACK;
	
	public ShadowLayer(int x, int y, int w, int h) {
		super(x, y, w, h);		
	}
	
	public void drawLights(Graphics g, LightSpot ... spots) {
		
		refresh();
		
		setColor(color);

		AWTGraphics awtGraphics = (AWTGraphics) g;
		awtGraphics.setImage(buffer);

		awtGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1.0f));
		
		for(LightSpot spot: spots) {
			spot.drawLight(g);
		}
		
		g.resetOpacity();
		
		g.resetImage();

		awtGraphics.drawImage(buffer, x, y);
	}
	
	public void setColor(Color color) {
		this.color = color;
		
		clearGraphics();
		graphics.setColor(color);
		graphics.fillRect(x, y, w, h);		
	}
	
}
