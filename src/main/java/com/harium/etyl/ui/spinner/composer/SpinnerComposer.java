package com.harium.etyl.ui.spinner.composer;

import com.harium.etyl.ui.Button;
import com.harium.etyl.ui.Label;
import com.harium.etyl.ui.label.TextLabel;
import com.harium.etyl.commons.layer.GeometricLayer;

public abstract class SpinnerComposer extends GeometricLayer {
	
	public SpinnerComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public abstract Button buildPlusButton(int x, int y, int w, int h);
	
	public abstract Button buildMinusButton(int x, int y, int w, int h);
	
	protected Label buildPlusLabel() {
		return new TextLabel("+");
	}
	
	protected Label buildMinusLabel() {
		return new TextLabel("-");
	}

}
