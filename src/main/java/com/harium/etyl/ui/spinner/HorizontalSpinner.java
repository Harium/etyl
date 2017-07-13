package com.harium.etyl.ui.spinner;

import com.harium.etyl.ui.Spinner;
import com.harium.etyl.ui.spinner.composer.HorizontalComposer;
import com.harium.etyl.ui.spinner.composer.SpinnerComposer;

public abstract class HorizontalSpinner<T extends Number> extends Spinner<T> {
	
	public HorizontalSpinner(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	protected SpinnerComposer buildComposer() {
		return new HorizontalComposer(x, y, w, h);
	}
	
}
