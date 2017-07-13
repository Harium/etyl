package com.harium.etyl.awt.core.input;

import com.harium.etyl.commons.event.KeyEventListener;
import com.harium.etyl.core.input.HIDController;
import com.harium.etyl.core.input.keyboard.Keyboard;
import com.harium.etyl.core.input.mouse.Mouse;
import com.harium.etyl.input.JoystickHandler;

/**
 * 
 * HIDController have the input controls, like mouse, keyboard and joystick.
 * 
 * @author yuripourre
 *
 */

public class AWTController implements HIDController {

	private Mouse mouse;
	
	private Keyboard keyboard;
	
	private JoystickHandler joystick;
			
	public AWTController(KeyEventListener listener) {
		
		mouse = new Mouse(0,0);
		
		keyboard = new AWTKeyboard(listener);
		keyboard.init();
		
		joystick = JoystickHandler.getInstance();
		joystick.setListener(listener);
	}

	public Mouse getMouse() {
		return mouse;
	}
	
	public Keyboard getKeyboard() {
		return keyboard;
	}

}
