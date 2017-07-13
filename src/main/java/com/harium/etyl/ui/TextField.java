package com.harium.etyl.ui;

import com.harium.etyl.ui.base.BaseTextField;
import com.harium.etyl.ui.base.UIView;
import com.harium.etyl.ui.theme.ThemeManager;


public class TextField extends UIView {

	private BaseTextField textField;

	public TextField(int x, int y, int w, int h) {
		super(x,y,w,h);

		this.textField = ThemeManager.getInstance().getTheme().createTextField(x, y, w, h);
		delegateView(textField);
	}

	public void rebuild() {
		BaseTextField view = ThemeManager.getInstance().getTheme().createTextField(x, y, w, h);
		view.copy(delegatedView);

		delegateView(view);
	}

	public String getText() {
		return textField.getText();
	}

	public void setText(String text) {
		textField.setText(text);
	}

	public void clearField() {
		textField.clearField();
	}

}