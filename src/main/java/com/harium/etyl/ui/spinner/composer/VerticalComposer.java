package com.harium.etyl.ui.spinner.composer;

import com.harium.etyl.ui.Button;
import com.harium.etyl.ui.Label;
import com.harium.etyl.ui.label.TextLabel;

public class VerticalComposer extends SpinnerComposer {
	
	public VerticalComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public Button buildPlusButton(int x, int y, int w, int h) {
		Button plus = new Button(x, y, w, h);
		plus.setLabel(buildPlusLabel());
		
		return plus;
	}
	
	@Override
	protected Label buildPlusLabel() {
		return new TextLabel("+");
	}

	@Override
	public Button buildMinusButton(int x, int y, int w, int h) {
		Button minus = new Button(x, y, w, h);
		minus.setLabel(buildMinusLabel());
				
		return minus;
	}
	
	@Override
	protected Label buildMinusLabel() {
		return new TextLabel("-");
	}

}
