package com.harium.etyl.awt.camera;

import java.awt.geom.AffineTransform;

public class Camera extends ViewPort {
		
	protected float zoomX = 1;
	protected float zoomY = 1;
		
	private float skewX = 0;
	private float skewY = 0;
	
	public Camera(int x, int y, int w, int h) {
		super(x, y, w, h);
	}	
	
	@Override
	public AffineTransform getTransform() {
		
		AffineTransform transform = AffineTransform.getScaleInstance(zoomX, zoomY);

		if(aimX != 0 || aimY != 0) {
			transform.concatenate(AffineTransform.getTranslateInstance(-aimX/zoomX, -aimY/zoomY));
		}

		return transform;
	}
	
	public void setZoom(float zoom) {
		this.zoomX = zoom;
		this.zoomY = zoom;
	}

	public float getZoomX() {
		return zoomX;
	}

	public void setZoomX(float zoomX) {
		this.zoomX = zoomX;
	}

	public float getZoomY() {
		return zoomY;
	}

	public void setZoomY(float zoomY) {
		this.zoomY = zoomY;
	}
		
}
