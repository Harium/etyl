package com.harium.etyl.theme.etyllic;

import com.harium.etyl.ui.base.*;
import com.harium.etyl.ui.theme.Theme;
import com.harium.etyl.theme.etyllic.components.EtyllicCheckBox;

public class EtyllicTheme extends Theme {
	
	public EtyllicTheme() {
		super();
	}
	
	public BaseButton createButton(int x, int y, int w, int h) {
		BaseButton button = new BaseButton(x, y, w, h);
		return button;
	}

	public BaseCheckBox createCheckBox(int x, int y, int w, int h) {
		BaseCheckBox checkBox = new EtyllicCheckBox(x, y, w, h);
		return checkBox;
	}
	
	public BaseRadioButton createRadioButton(int x, int y, int w, int h) {
		BaseRadioButton radioButton = new BaseRadioButton(x, y, w, h);
		return radioButton;
	}
	
	public BaseTextField createTextField(int x, int y, int w, int h) {
		BaseTextField textField = new BaseTextField(x, y, w, h);
		return textField;
	}

	@Override
	public BaseTextView createTextView(int x, int y, int w, int h) {
		BaseTextView textView = new BaseTextView(x, y, w, h);
		return textView;
	}

	@Override
	public BaseSlider createSlider(int x, int y, int w, int h) {
		BaseSlider slider = new BaseSlider(x, y, w, h);
		return slider;
	}

	@Override
	public BaseTable createTable(int x, int y, int w, int h) {
		BaseTable table = new BaseTable(x, y, w, h);
		return table;
	}

	@Override
	public BasePanel createPanel(int x, int y, int w, int h) {
		BasePanel panel = new BasePanel(x, y, w, h);
		return panel;
	}
	
	@Override
	public BasePanel createLeftPanel(int x, int y, int w, int h) {
		return createPanel(x, y, w, h);
	}
	
	@Override
	public BasePanel createRightPanel(int x, int y, int w, int h) {
		return createPanel(x, y, w, h);
	}
}
