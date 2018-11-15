package com.harium.etyl.awt;

import com.harium.etyl.awt.core.input.AWTKeyboard;
import com.harium.etyl.core.Core;
import com.harium.etyl.core.graphics.Monitor;
import com.harium.etyl.core.input.keyboard.Keyboard;
import com.harium.etyl.core.input.mouse.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.MemoryImageSource;

/**
 * @author yuripourre
 */

public class FullScreenWindow extends Frame {

    private static final long serialVersionUID = -5176767672500250086L;

    private DisplayMode displayMode;
    private Monitor monitor;
    private Core core;

    private int w;
    private int h;

    public FullScreenWindow(Core core, Monitor selectedMonitor) {
        this.core = core;
        this.monitor = selectedMonitor;
        this.displayMode = selectedMonitor.getDevice().getDisplayMode();

        this.w = selectedMonitor.getW();
        this.h = selectedMonitor.getH();

        //These configurations are needed in some cases
        setUndecorated(true);
        setResizable(true);

        if (core.isHideCursor()) {
            hideDefaultCursor();
        }

        setAlwaysOnTop(true);
        setListeners();

        setBounds(selectedMonitor.getX(), selectedMonitor.getY(), selectedMonitor.getW(), selectedMonitor.getH());

        setVisible(true);
    }

    private void setListeners() {
        Mouse mouse = core.getControl().getMouse();
        Keyboard keyboard = core.getControl().getKeyboard();

        addMouseMotionListener(mouse);
        addMouseWheelListener(mouse);
        addMouseListener(mouse);
        addKeyListener((AWTKeyboard) keyboard);
    }

    public void draw(Image volatileImage) {
        int x = core.getCurrentContext().getX();
        int y = core.getCurrentContext().getY();

        Image resized = volatileImage.getScaledInstance(w, h, Image.SCALE_REPLICATE);

        getGraphics().drawImage(resized, x, y, null);
    }

    private void hideDefaultCursor() {
        int[] pixels = new int[16 * 16];
        Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16))
                , new Point(0, 0), "invisibleCursor");
        setCursor(transparentCursor);

        //core.showCursor();
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public DisplayMode getDisplayMode() {
        return displayMode;
    }
}
