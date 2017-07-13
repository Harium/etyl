package com.harium.etyl.ui.textfield;

import com.harium.etyl.ui.PasswordField;

/**
 * 
 * @author yuripourre
 *
 */

public class ConfirmPasswordField extends PasswordField{

	private PasswordField passwordField;
	
	public ConfirmPasswordField(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}

}
