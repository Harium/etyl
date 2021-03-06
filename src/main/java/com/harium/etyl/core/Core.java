package com.harium.etyl.core;

import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.context.Context;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.module.Module;
import com.harium.etyl.core.input.HIDController;

import java.awt.*;

public interface Core {
    void initMonitors(int width, int height);

    void moveToCenter();

    void setEngine(EtylFrame frame);

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

    Context getCurrentContext();

    HIDController getControl();

    void updateSuperEvent(GUIEvent event);

    boolean isHideCursor();
}
