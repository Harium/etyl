package com.harium.etyl.ui.spinner.composer;

import com.harium.etyl.ui.Button;

public class HorizontalComposer extends SpinnerComposer {
	
	public HorizontalComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public Button buildPlusButton(int x, int y, int w, int h) {
		Button plus = new Button(x, y, w, h);
		plus.setLabel(buildPlusLabel());
		
		return plus;
	}
	
	@Override
	public Button buildMinusButton(int x, int y, int w, int h) {
		Button minus = new Button(x, y, w, h);
		minus.setLabel(buildMinusLabel());
		
		return minus;
	}	

}
