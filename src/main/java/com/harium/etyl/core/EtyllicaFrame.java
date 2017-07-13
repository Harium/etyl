package com.harium.etyl.core;

import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.GUIEvent;


public interface EtyllicaFrame {

	public void init();
	
	public void draw();
	
	public Application startApplication();
	
	public void updateSuperEvent(GUIEvent event);
	
}
