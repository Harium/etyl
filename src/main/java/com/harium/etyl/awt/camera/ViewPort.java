package com.harium.etyl.awt.camera;

import java.awt.geom.AffineTransform;

import com.harium.etyl.layer.BufferedLayer;

public class ViewPort extends BufferedLayer {

	protected int aimX = 0;
	
	protected int aimY = 0;
	
	public ViewPort(int x, int y, int w, int h) {
		super(x, y, w, h);		
	}
	
	public int getAimX() {
		return aimX;
	}

	public void setAimX(int aimX) {
		this.aimX = aimX;
	}

	public int getAimY() {
		return aimY;
	}

	public void setAimY(int aimY) {
		this.aimY = aimY;
	}
	
	public void setAimLocation(int x, int y) {
		aimX = x;
		aimY = y;
	}
	
	@Override
	public AffineTransform getTransform() {
						
		if(aimX != 0 || aimY != 0) {
			return AffineTransform.getTranslateInstance(-aimX, -aimY);
		} else {
			return AffineTransform.getScaleInstance(1, 1);
		}		
	}
			
}
