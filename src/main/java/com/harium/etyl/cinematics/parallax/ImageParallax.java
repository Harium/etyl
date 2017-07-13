package com.harium.etyl.cinematics.parallax;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class ImageParallax extends Parallax {

	private ImageLayer layer;
	
	public ImageParallax(String imagePath) {
		super();
		
		layer = new ImageLayer(imagePath);
	}
	
	public ImageParallax(int y, String imagePath) {
		super();
		
		layer = new ImageLayer(0, y, imagePath);
	}

	@Override
	public void draw(Graphics g) {
		int mod = (offset / proximity) % layer.getW();
		
		if(mod == 0) {
			layer.simpleDraw(g, 0, layer.getY());
		} else {
			layer.simpleDraw(g, -mod, layer.getY());
			layer.simpleDraw(g, -mod + layer.getW(), layer.getY());
		}
	}
	
}
