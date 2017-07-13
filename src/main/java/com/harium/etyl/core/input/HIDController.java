package com.harium.etyl.core.input;

import com.harium.etyl.core.input.keyboard.Keyboard;
import com.harium.etyl.core.input.mouse.Mouse;

public interface HIDController {
	Mouse getMouse();
	Keyboard getKeyboard();
}
