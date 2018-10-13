package com.harium.etyl.core;

import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.GUIEvent;

import java.awt.image.BufferStrategy;


public interface EtylFrame {

    void init();

    void draw();

    BufferStrategy getBufferStrategy();

    Application startApplication();

    void updateSuperEvent(GUIEvent event);

}
