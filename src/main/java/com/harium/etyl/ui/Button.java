package com.harium.etyl.ui;

import com.harium.etyl.ui.base.BaseButton;
import com.harium.etyl.ui.base.UIView;
import com.harium.etyl.ui.theme.ThemeManager;

public class Button extends UIView {

	private BaseButton button;

	public Button(int x, int y, int w, int h) {
		super(x,y,w,h);

		this.button = ThemeManager.getInstance().getTheme().createButton(x, y, w, h);
		delegateView(button);
	}

	public Button(Label label) {
		this(0,0,0,0);
		setLabel(label);
	}

	public void rebuild() {
		BaseButton view = ThemeManager.getInstance().getTheme().createButton(x, y, w, h);
		view.copy(delegatedView);

		delegateView(view);
	}

	public String getAlt() {
		return button.getAlt();
	}

	public Label getLabel() {
		return button.getLabel();
	}

	public void setAlt(String alt) {
		button.setAlt(alt);
	}

	public void setLabel(Label label) {
		this.button.setLabel(label);
	}

	public void setCenterLabel(Label label) {
		button.setCenterLabel(label);
	}

}
