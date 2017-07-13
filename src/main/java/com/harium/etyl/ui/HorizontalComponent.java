package com.harium.etyl.ui;

import com.harium.etyl.layer.ImageLayer;

public abstract class HorizontalComponent extends View {

	protected ImageLayer layer;
	
	protected int startPart = 0;
	protected int endPart = 0;
	
	public HorizontalComponent(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
}
