package com.harium.etyl.ui.label;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

/**
 * 
 * ImageLayer as label
 * 
 * @author yuripourre
 *
 */

public class ImageLabel extends Icon {

	protected ImageLayer layer; 
	
	public ImageLabel(int x, int y, String path) {
		super(x, y);
		layer = new ImageLayer(0,0,path);
	}
	
	public ImageLabel(String path){
		this(0, 0, path);
	}
	
	public ImageLabel(int x, int y, ImageLayer layer) {
		super(x, y);
		this.layer = layer;
	}
	
	public ImageLabel(ImageLayer layer) {
		this(0, 0, layer);
	}
		
	@Override
	public void draw(Graphics g) {
		//layer.draw(g);
		layer.simpleDraw(g);
	}
	
	//Useful methods to centralize label
	@Override
	public void setX(int x){
		layer.setX(x);
	}
	
	@Override
	public void setY(int y){
		layer.setY(y);
	}
	
	@Override
	public int getW(){
		return layer.getW();
	}
	
	@Override
	public int getH(){
		return layer.getH();
	}
	
	@Override
	public void setContentBounds(int bx, int by, int bw, int bh) {
		super.setContentBounds(bx, by, bw, bh);
		if (this.layer != null) {
			this.layer.centralize(bx, by, bw, bh);	
		}
	}
	
}
