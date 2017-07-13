package com.harium.etyl.ui;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.ui.base.BaseTextField;

/**
 * 
 * @author yuripourre
 *
 */

public class PasswordField extends BaseTextField {

	public PasswordField(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public String getText() {
		
		String password = "";
		
		for(int i=0;i<text.length();i++)
			password += '*';
		
		return password;
	}
	
	public String getTrueText(){
		return text;
	}
	
	//TODO Remove test
	@Override
	public void updateEvent(GUIEvent event) {

		switch (event) {

		case GAIN_FOCUS:

			onFocus = true;
			//System.out.println("PasswordField, gain focus");
			
			break;

		case LOST_FOCUS:

			onFocus = false;
			//System.out.println("PasswordField, lost focus");

			break;

		case MOUSE_OVER_WITH_FOCUS:
		case MOUSE_OVER:

			mouseOver = true;

			break;

		case MOUSE_OUT:
			mouseOver = false;

			break;

		default:
			break;
		}
	}

}
