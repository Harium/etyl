package com.harium.etyl.ui;

import com.harium.etyl.ui.base.BaseCheckBox;
import com.harium.etyl.ui.base.UIView;
import com.harium.etyl.ui.theme.ThemeManager;

public class CheckBox extends UIView {
	
	private BaseCheckBox checkbox;
	
	public CheckBox(int x, int y, int w, int h) {
		super(x,y,w,h);

		this.checkbox = ThemeManager.getInstance().getTheme().createCheckBox(x, y, w, h);
		delegateView(checkbox);
	}

	public void rebuild() {
		BaseCheckBox view = ThemeManager.getInstance().getTheme().createCheckBox(x, y, w, h);
		view.copy(checkbox);
		delegateView(view);
	}

	public String getAlt() {
		return checkbox.getAlt();
	}

	public Label getLabel() {
		return checkbox.getLabel();
	}
	
	public void setAlt(String alt) {
		checkbox.setAlt(alt);
	}

	public void setLabel(Label label) {
		checkbox.setLabel(label);
	}

	public void setCenterLabel(Label label) {
		checkbox.setCenterLabel(label);
	}

	public void setChecked(boolean checked) {
		checkbox.setChecked(checked);
	}
	
	public void setChecker(Label checker) {
		checkbox.setChecker(checker);
	}

}
