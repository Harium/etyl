package com.harium.etyl.core;

import java.awt.Graphics;

import com.harium.etyl.commons.module.Module;
import com.harium.etyl.commons.context.Application;

public interface Core {
	void initMonitors(int width, int height);
	void moveToCenter();
	void setEngine(EtyllicaFrame frame);
	void startEngine();
	void startCore(Application application);
	void setPath(String path);
	void paint(Graphics g);
	String getPath();

	void update(double delta) throws Exception;
	void render();
	boolean isRunning();
	void setFps(int fps);

	void addModule(Module module);
}
