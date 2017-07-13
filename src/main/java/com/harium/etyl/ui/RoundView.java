package com.harium.etyl.ui;

import com.harium.etyl.ui.style.Roundness;

public abstract class RoundView extends View {
	
	public RoundView(int x, int y, int w, int h) {
		super(x,y,w,h);
		this.style.roundness = new Roundness(getTheme().getStyle().roundness);
	}

}
