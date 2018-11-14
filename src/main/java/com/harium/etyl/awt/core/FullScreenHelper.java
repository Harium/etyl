package com.harium.etyl.awt.core;

import com.harium.etyl.awt.FullScreenWindow;
import com.harium.etyl.core.Core;
import com.harium.etyl.core.graphics.Monitor;

import java.awt.*;

public class FullScreenHelper {

    public static FullScreenWindow enableFullScreen(Core core, Monitor selectedMonitor) {
        return enableFullScreen(core, selectedMonitor);
    }

    public static FullScreenWindow enableFullScreen(Core core, Monitor selectedMonitor, boolean kioskMode) {
        GraphicsDevice gd = selectedMonitor.getDevice();

        FullScreenWindow frame = new FullScreenWindow(core, selectedMonitor);

        gd.setFullScreenWindow(frame);
        frame.setVisible(true);
        if (kioskMode) {
            frame.setAlwaysOnTop(true);
        }

        return frame;
    }

    public static void disableFullScreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();

        gs.setFullScreenWindow(null);
    }

}
