package com.harium.etyl.core;

import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.GUIEvent;


public interface EtylFrame {

	void init();
	
	void draw();
	
	Application startApplication();
	
	void updateSuperEvent(GUIEvent event);
	
}
