package com.harium.etyl.ui;

import com.harium.etyl.layer.BufferedLayer;

public abstract class BackBufferedView extends View {

	protected BufferedLayer layer;
	
	public BackBufferedView(int x, int y, int w, int h) {
		super(x,y,w,h);
		layer = new BufferedLayer(x, y, w+1, h+1);
	}
	
}
