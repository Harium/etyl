package com.harium.etyl.ui;

import com.harium.etyl.ui.base.BaseRadioButton;
import com.harium.etyl.ui.base.UIView;
import com.harium.etyl.ui.theme.ThemeManager;

public class RadioButton extends UIView {
	
	private BaseRadioButton radio;
	
	public RadioButton(int x, int y, int w, int h) {
		super(x,y,w,h);

		this.radio = ThemeManager.getInstance().getTheme().createRadioButton(x, y, w, h);
		delegateView(radio);
	}

	public void rebuild() {
		BaseRadioButton view = ThemeManager.getInstance().getTheme().createRadioButton(x, y, w, h);
		view.copy(delegatedView);

		delegateView(view);
	}

	public String getAlt() {
		return radio.getAlt();
	}

	public Label getLabel() {
		return radio.getLabel();
	}
	
	public RadioGroup getGroup() {
		return radio.getGroup();
	}
	
	public void setAlt(String alt) {
		radio.setAlt(alt);
	}

	public void setLabel(Label label) {
		radio.setLabel(label);
	}

	public void setCenterLabel(Label label) {
		radio.setCenterLabel(label);
	}

	public void setChecked(boolean checked) {
		radio.setChecked(checked);
	}
	
	public void setChecker(Label checker) {
		radio.setChecker(checker);
	}
	
	public void setGroup(RadioGroup group) {
		radio.setGroup(group);
	}

	public void check() {
		radio.check();
	}

}
