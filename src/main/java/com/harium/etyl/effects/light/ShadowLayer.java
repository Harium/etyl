package com.harium.etyl.effects.light;

import java.awt.AlphaComposite;
import java.awt.Color;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.BufferedLayer;

public class ShadowLayer extends BufferedLayer {

	private Color color = Color.BLACK;
	
	public ShadowLayer(int x, int y, int w, int h) {
		super(x, y, w, h);		
	}
	
	public void drawLights(Graphics g, LightSpot ... spots) {
		
		refresh();
		
		setColor(color);
		
		g.setImage(buffer);
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1.0f));
		
		for(LightSpot spot: spots) {
			spot.drawLight(g);
		}
		
		g.resetOpacity();
		
		g.resetImage();
		
		g.drawImage(buffer, x, y);
	}
	
	public void setColor(Color color) {
		this.color = color;
		
		clearGraphics();
		graphics.setColor(color);
		graphics.fillRect(x, y, w, h);		
	}
	
}
