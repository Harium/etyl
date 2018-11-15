package com.harium.etyl.awt.core;

import com.harium.etyl.awt.FullScreenWindow;
import com.harium.etyl.core.Core;
import com.harium.etyl.core.graphics.Monitor;

import javax.swing.*;
import java.awt.*;

public class FullScreenHelper {

    public static FullScreenWindow enableFullScreen(Core core, Monitor selectedMonitor) {
        GraphicsDevice gd = selectedMonitor.getDevice();

        FullScreenWindow frame = new FullScreenWindow(core, selectedMonitor);

        gd.setFullScreenWindow(frame);
        frame.validate();

        return frame;
    }

    public static void disableFullScreen(FullScreenWindow fullScreenWindow) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();

        // Reset Display Mode
        //fullScreenWindow.getMonitor().getDevice().setDisplayMode(fullScreenWindow.getDisplayMode());
        gs.setFullScreenWindow(null);
    }

}
