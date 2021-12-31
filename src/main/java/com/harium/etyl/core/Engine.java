package com.harium.etyl.core;

import com.harium.etyl.awt.core.AWTCore;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.loader.Loader;

public interface Engine {

	AWTCore getCore();

	void init();

	void addLoader(Loader loader);

	void hideCursor();

	void updateSuperEvent(GUIEvent event);

}
