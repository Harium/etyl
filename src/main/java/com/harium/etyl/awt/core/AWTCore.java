package com.harium.etyl.awt.core;

import com.harium.etyl.awt.AWTGraphics;
import com.harium.etyl.awt.AWTWindow;
import com.harium.etyl.awt.FullScreenWindow;
import com.harium.etyl.awt.core.input.AWTKeyboard;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.context.Session;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.core.BaseCore;
import com.harium.etyl.core.EtylFrame;
import com.harium.etyl.core.graphics.Monitor;
import com.harium.etyl.core.loop.FrameSkippingLoop;
import com.harium.etyl.core.loop.GameLoop;
import com.harium.etyl.effects.GenericFullScreenEffect;

import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.ComponentEvent;
import java.awt.image.MemoryImageSource;
import java.awt.image.VolatileImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AWTCore is a BaseCore based on AWT
 *
 * @author yuripourre
 */
public class AWTCore extends BaseCore implements Runnable, java.awt.event.ComponentListener {

    public static final String COMPONENT = "COMPONENT";

    private GraphicsConfiguration configuration;

    private Component component;

    private VolatileImage volatileImage;

    private AWTGraphics graphic;

    private FullScreenWindow fullScreen = null;

    private EtylFrame engine;

    private GameLoop gameLoop;

    private boolean locked = true;
    private boolean hideCursor = true;

    public AWTCore(Component component, int width, int height) {
        super(width, height);

        this.component = component;

        int windowX = 0, windowY = 0;

        if (component != null) {
            this.configuration = component.getGraphicsConfiguration();
            initGraphics(width, height);
            //Core methods
            initMonitors(width, height);
            moveToCenter();

            windowX = component.getX();
            windowY = component.getY();

            enableDragAndDrop();
        }

        window = new AWTWindow(windowX, windowY, width, height);
        window.setComponent(component);

        gameLoop = new FrameSkippingLoop(this);
    }

    public void initMonitors(int width, int height) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = ge.getScreenDevices();

        if (devices.length > 0) {

            for (int i = 0; i < devices.length; i++) {

                Rectangle gcBounds = devices[i].getDefaultConfiguration().getBounds();

                int x = gcBounds.x;
                int y = gcBounds.y;
                int w = gcBounds.width;
                int h = gcBounds.height;

                monitors.add(new Monitor(x, y, w, h, devices[i]));
            }

        } else {
            monitors.add(new Monitor(0, 0, width, height, devices[0]));
        }
    }

    public void moveToCenter() {
        Monitor firstMonitor = monitors.get(0);
        int x = firstMonitor.getW() / 2 - component.getWidth() / 2;
        int y = firstMonitor.getH() / 2 - component.getHeight() / 2;

        component.setLocation(x, y);
    }

    private void enableDragAndDrop() {
        new DropTarget(component, new AWTDragAndDrop(this));
    }

    private void initGraphics(int width, int height) {
        locked = true;

        this.graphic = new AWTGraphics(width, height);
        defineSize(width, height);

        locked = false;
    }

    public void updateSuperEvent(GUIEvent event) {
        if (event == GUIEvent.ENABLE_FULL_SCREEN) {
            enableFullScreen();
        } else if (event == GUIEvent.DISABLE_FULL_SCREEN) {
            disableFullScreen();
        } else if (event == GUIEvent.REQUEST_FOCUS) {
            if (!component.hasFocus()) {
                component.requestFocus();
            }
        }
    }

    public void enableFullScreen() {
        Monitor selectedMonitor = monitors.get(0);

        Point p = this.component.getLocation();

        for (Monitor monitor : monitors) {
            if (monitor.colideRectPoint(p.x, p.y)) {
                selectedMonitor = monitor;
            }
        }

        if (!isFullScreenEnable()) {
            fullScreen = FullScreenHelper.enableFullScreen(this, selectedMonitor);
            setFullScreenEnable(true);
        }

        addEffect(new GenericFullScreenEffect(0, 0, this.width, height));
    }

    public void disableFullScreen() {
        fullScreen.dispose();
        FullScreenHelper.disableFullScreen();
        setFullScreenEnable(false);
    }

    public void startCore(Application application) {
        //Setup first Application
        application.setSession(session);

        this.window.setApplication(application);
        reload(application);

        component.setFocusTraversalKeysEnabled(false);
        component.setFocusable(true);
        component.requestFocus();

        // Avoid hide default cursor
        if (hideCursor) {
            hideDefaultCursor(component);
            application.showCursor();
        } else {
            // Hide custom cursor
            application.hideCursor();
        }

        // Define Listeners
        component.addMouseMotionListener(getMouse());
        component.addMouseWheelListener(getMouse());
        component.addMouseListener(getMouse());

        component.addKeyListener((AWTKeyboard) getKeyboard());
    }

    //Component Methods
    private VolatileImage createBackBuffer(int width, int height) {
        return createBackBuffer(width, height, Transparency.OPAQUE);
    }

    private VolatileImage createBackBuffer(int width, int height, int transparency) {
        return configuration.createCompatibleVolatileImage(width, height, transparency);
    }

    public void defineSize(int width, int height) {
        component.setSize(width, height);

        volatileImage = createBackBuffer(width, height);

        if (volatileImage != null) {
            graphic.setScreen(volatileImage.createGraphics());
        }
    }

    public void validateVolatileImage() {
        int valCode = volatileImage.validate(configuration);

        // This means the device doesn't match up to this hardware accelerated image.
        if (valCode == VolatileImage.IMAGE_INCOMPATIBLE) {
            volatileImage = createBackBuffer(width, height); // recreate the hardware accelerated image.
            graphic.setScreen(volatileImage.createGraphics());
        }
    }

    public static void hideDefaultCursor(Component component) {
        int[] pixels = new int[16 * 16];

        Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16))
                , new Point(0, 0), "invisibleCursor");
        component.setCursor(transparentCursor);
    }

    public void paint(Graphics g) {
        if (locked) {
            return;
        }

        validateVolatileImage();

        draw(graphic);

        // TODO Avoid check for null
        if (volatileImage == null) {
            return;
        }

        if (!isFullScreenEnable()) {
            g.drawImage(volatileImage, window.getContext().getX(), window.getContext().getY(), component);
        } else {
            fullScreen.draw(volatileImage);
        }

        g.dispose();
    }

    public int getW() {
        return width;
    }

    public void setW(int w) {
        this.width = w;
    }

    public int getH() {
        return height;
    }

    public void setH(int h) {
        this.height = h;
    }

    public void update(double delta) throws Exception {

        long now = System.currentTimeMillis();

        update(now);

        updateEngine(delta);
    }

    public void render() {
        engine.draw();
    }

    public void hideCursor() {
        hideCursor = true;
    }

    public void setEngine(EtylFrame engine) {
        this.engine = engine;
    }

    public void startEngine() {
        component.setVisible(true);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(this);
    }

    @Override
    public void run() {
        try {
            gameLoop.loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateEngine(double delta) {
        GUIEvent event = getSuperEvent();
        engine.updateSuperEvent(event);
    }

    protected Session buildSession() {
        Session session = new Session();
        session.put(COMPONENT, component);
        return session;
    }

    @Override
    public void componentHidden(ComponentEvent event) {
        // TODO Auto-generated method stub
    }

    @Override
    public void componentMoved(ComponentEvent event) {
        // TODO Auto-generated method stub
    }

    @Override
    public void componentResized(ComponentEvent event) {
        Component component = event.getComponent();

        Rectangle bounds = component.getBounds();

        int width = bounds.width;
        int height = bounds.height;

        initGraphics(width, height);
        //System.out.println("Resized: "+bounds.x+", "+bounds.y+", "+bounds.width+", "+bounds.height);
        resizeApplication(width, height);
    }

    @Override
    public void componentShown(ComponentEvent event) {
        // TODO Auto-generated method stub
    }

    public Component getComponent() {
        return component;
    }

    public FullScreenWindow getFullScreenWindow() {
        return fullScreen;
    }

    public void showCursor() {
        hideCursor = false;
    }
}
