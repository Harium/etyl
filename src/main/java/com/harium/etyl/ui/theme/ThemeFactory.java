package com.harium.etyl.ui.theme;

import com.harium.etyl.ui.base.*;

public interface ThemeFactory {
		
	BaseButton createButton(int x, int y, int w, int h);

	BaseCheckBox createCheckBox(int x, int y, int w, int h);
	
	BaseRadioButton createRadioButton(int x, int y, int w, int h);

	BasePanel createPanel(int x, int y, int w, int h);
	
	BasePanel createLeftPanel(int x, int y, int w, int h);
	
	BasePanel createRightPanel(int x, int y, int w, int h);
	
	BaseTable createTable(int x, int y, int w, int h);
	
	BaseTextField createTextField(int x, int y, int w, int h);
	
	BaseTextView createTextView(int x, int y, int w, int h);

	BaseSlider createSlider(int x, int y, int w, int h);
}
