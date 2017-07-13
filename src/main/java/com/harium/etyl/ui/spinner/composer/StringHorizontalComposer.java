package com.harium.etyl.ui.spinner.composer;

import com.harium.etyl.ui.Label;
import com.harium.etyl.ui.label.TextLabel;

public class StringHorizontalComposer extends HorizontalComposer {
	
	public StringHorizontalComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	protected Label buildPlusLabel() {
		return new TextLabel(">");
	}
	
	@Override
	protected Label buildMinusLabel() {
		return new TextLabel("<");
	}	

}
