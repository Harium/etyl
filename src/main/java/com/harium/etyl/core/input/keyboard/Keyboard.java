package com.harium.etyl.core.input.keyboard;

import com.harium.etyl.commons.Updatable;
import com.harium.etyl.commons.event.KeyEventListener;

public interface Keyboard extends Updatable {
	void init();
	void poll(KeyEventListener listener);
}
