package com.harium.etyl.core;

import com.harium.etyl.awt.AWTWindow;
import com.harium.etyl.awt.core.input.AWTController;
import com.harium.etyl.commons.Updatable;
import com.harium.etyl.commons.graphics.Graphics;
import com.harium.etyl.commons.loader.Loader;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.script.SingleIntervalAnimation;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.context.Context;
import com.harium.etyl.commons.context.Session;
import com.harium.etyl.commons.context.UpdateIntervalListener;
import com.harium.etyl.commons.context.load.ApplicationLoader;
import com.harium.etyl.commons.context.load.LoaderListener;
import com.harium.etyl.core.effect.GlobalEffect;
import com.harium.etyl.commons.event.*;
import com.harium.etyl.commons.module.Module;
import com.harium.etyl.commons.module.ModuleHandler;
import com.harium.etyl.commons.ui.UIComponent;
import com.harium.etyl.core.error.ErrorMessages;
import com.harium.etyl.core.graphics.Monitor;
import com.harium.etyl.core.input.keyboard.Keyboard;
import com.harium.etyl.core.input.mouse.Mouse;
import com.harium.etyl.util.io.IOHelper;

import java.util.*;

/**
 * @author yuripourre
 */

public abstract class BaseCore implements Core, KeyEventListener, Updatable, LoaderListener<Context> {

    private static final int TITLE_BAR_HEIGHT = 50;

    protected int width;
    protected int height;

    protected String path = "";

    //External Windows
    protected AWTWindow window = null;

    protected AWTController control;

    private Mouse mouse;
    private Keyboard keyboard;

    //private List<KeyEvent> joyEvents;

    private boolean firstLoad = true;
    private boolean firstDraw = true;

    private boolean allowFullscreen = false;
    private boolean fullScreenEnable = false;

    private boolean fixEventPosition = false;

    protected int fps = 0;

    //FullScreen Stuff
    private boolean enableFullScreen = false;
    private boolean disableFullScreen = false;

    private boolean alt = false;
    private boolean enter = false;
    private boolean esc = false;

    protected boolean running = true;

    protected GUIEvent superEvent = GUIEvent.NONE;

    private List<SingleIntervalAnimation> globalScripts = new ArrayList<SingleIntervalAnimation>();

    protected List<Monitor> monitors = new ArrayList<Monitor>();

    protected Session session = buildSession();

    protected ApplicationLoader applicationLoader;

    private ModuleHandler modules = new ModuleHandler();

    protected List<Loader> loaders = new ArrayList<>();

    public BaseCore(int w, int h) {
        super();
        this.width = w;
        this.height = h;

        control = new AWTController(this);

        setMouse(control.getMouse());
        setKeyboard(control.getKeyboard());

        applicationLoader = new ApplicationLoader(w, h);
    }

    public AWTWindow getWindow() {
        return window;
    }

    public void update(long now) {

        if (!getCurrentContext().isLoaded()) {
            return;
        }

        superEvent = GUIEvent.NONE;

        updateActiveWindow(now);

        modules.update(now);

        Context application = getCurrentContext();

        updateApplication(application, now);

        if (checkApplicationChange(application)) {
            return;
        }

        updateInput(application, now);

        //Update in another thread
        //Joystick locks the application
        //JoystickLoader.getInstance().update(now);

        handleFullScreen();
    }

    private void updateInput(Context application, long now) {
        Deque<PointerEvent> events = getMouse().getEvents();

        while (!events.isEmpty()) {
            PointerEvent event = events.pop();
            application.updateMouse(event);
            updatePointerEvent(event);
        }

        getKeyboard().update(now);
    }

    private boolean checkApplicationChange(Context application) {
        //if window, receive command to change application
        if (application.getNextApplication() != application) {
            this.changeApplication();
            return true;
        }

        return false;
    }

    private void handleFullScreen() {
        if (enableFullScreen) {
            enableFullScreen = false;

            superEvent = GUIEvent.ENABLE_FULL_SCREEN;
        }

        if (disableFullScreen) {
            disableFullScreen = false;

            superEvent = GUIEvent.DISABLE_FULL_SCREEN;
        }
    }

    public void resizeApplication(int w, int h) {

        Context application = getCurrentContext();

        application.resize(w, h);

        application.setW(w);
        application.setH(h);
    }

    public boolean updateApplication(Context context, long now) {
        if (context.isLocked()) {
            return false;
        }

        if (context.getUpdateInterval() == 0) {

            context.update(now);

            context.setLastUpdate(now);

            updateComponents(context, now);

        } else if (now - context.getLastUpdate() >= context.getUpdateInterval()) {

            UpdateIntervalListener updated = context.getUpdated();

            if (updated == null) {
                return false;
            }

            updated.timeUpdate(now);

            context.setLastUpdate(now);

            updateComponents(context, now);
        }

        return true;
    }

    private void updateComponents(Context context, long now) {
        Iterator<UIComponent> componentIterator = context.getComponents().listIterator();
        while (componentIterator.hasNext()) {
            UIComponent component = componentIterator.next();
            component.update(now);
        }
    }

    private void updateActiveWindow(long now) {

        List<AWTWindow> windows = window.getWindows();

        //Creating Windows
        //if application has windows
        if (!windows.isEmpty()) {

            //For each new window in application.windows
            for (AWTWindow window : windows) {
                //if this !windows.contains(window)
                replaceWindow(window);
            }

            window.getWindows().clear();
        }

		/*if(window.isClose()) {

			if(windows.size()>0) {
				windows.remove(window);
				window = windows.get(windows.size()-1);
			}else{
				System.exit(0);
			}

	    }*/

    }

    @Override
    public void updateJoystickEvent(KeyEvent event) {

        Context context = getCurrentContext();

        //Debug Joystick Commands
        //System.out.println("UpdateJoystick "+event.getKey());

        handleApplicationKeyEvents(context, event);
    }

    public void updatePointerEvent(PointerEvent event) {
        if (fixEventPosition) {
            fixEventPosition(event);
        }

        modules.updateMouse(event);

        updateWindowEvent(event, window);
    }

    private void updateWindowEvent(PointerEvent event, AWTWindow window) {
        GUIEvent frameEvent = updateFrameEvents(event);

        if (frameEvent != GUIEvent.NONE) {
            superEvent = frameEvent;
        }
    }

    public void draw(Graphics g) {
        if (firstDraw) {
            getCurrentContext().initGraphics(g);
            firstDraw = false;
        }

        drawContext(getCurrentContext(), g);

        //Draw Handlers
        modules.draw(g);

        //Draw Global Effects
        drawGlobalEffects(g);
    }

    private void drawContext(Context context, Graphics g) {
        if (context.isClearBeforeDraw()) {
            g.setColor(context.getBackgroundColor());
            g.fillRect(0, 0, context.getW(), context.getH());
        }

        g.setFps(fps);
        context.draw(g);

        //Draw Components
        for (UIComponent component : context.getComponents()) {
            component.draw(g);
        }
    }

    private void drawGlobalEffects(Graphics g) {
        Iterator<SingleIntervalAnimation> scriptIterator = globalScripts.listIterator();

        while (scriptIterator.hasNext()) {
            SingleIntervalAnimation script = scriptIterator.next();

            if (!script.isStopped()) {
                script.getTarget().draw(g);
            } else {
                scriptIterator.remove();
            }
        }
    }

    public void addEffect(GlobalEffect effect) {
        Animation.getInstance().add(effect.getScript());
        globalScripts.add(effect.getScript());

        //TODO add animation
        //globalEffects.add(effect);
    }

    private void updateKeyboardEvents(KeyEvent event) {

        if (event.isKeyDown(KeyEvent.VK_ALT_RIGHT) || event.isKeyDown(KeyEvent.VK_ALT_LEFT)) {

            alt = true;
        } else if (event.isKeyUp(KeyEvent.VK_ALT_RIGHT) || event.isKeyUp(KeyEvent.VK_ALT_LEFT)) {

            alt = false;
        }

        if (event.isKeyDown(KeyEvent.VK_ENTER)) {
            enter = true;
        } else if (event.isKeyUp(KeyEvent.VK_ENTER)) {
            enter = false;
        }

        if (event.isKeyDown(KeyEvent.VK_ESC)) {
            esc = true;
        } else if (event.isKeyUp(KeyEvent.VK_ESC)) {
            esc = false;
        }

        if (alt && enter) {
            alt = false;
            enter = false;

            if (allowFullscreen && !isFullScreenEnable()) {
                enableFullScreen = true;
            }
        }

        if (esc) {

            esc = false;
            if (isFullScreenEnable()) {
                disableFullScreen = true;
            }
        }
    }

    private void updateNumpadMouse(KeyEvent event) {

        if (Configuration.getInstance().isNumpadMouse()) {

            int speed = 1;

            //Move Left/Right
            if (event.isKeyDown(KeyEvent.VK_NUMPAD_LEFT_ARROW)) {

                getMouse().setX(getMouse().getX() - speed);
                getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, getMouse().getX(), getMouse().getY()));

            } else if (event.isKeyDown(KeyEvent.VK_NUMPAD_RIGHT_ARROW)) {

                getMouse().setX(getMouse().getX() + speed);
                getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, getMouse().getX(), getMouse().getY()));

            }

            //Move Up/Down
            if (event.isKeyDown(KeyEvent.VK_NUMPAD_UP_ARROW)) {

                getMouse().setX(getMouse().getY() - speed);
                getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, getMouse().getX(), getMouse().getY()));

            } else if (event.isKeyDown(KeyEvent.VK_NUMPAD_DOWN_ARROW)) {

                getMouse().setX(getMouse().getY() + speed);
                getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, getMouse().getX(), getMouse().getY()));

            }

            //Mouse Left Button
            if (event.isKeyDown(KeyEvent.VK_NUMPAD_INS)) {
                getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.PRESSED));
            } else if (event.isKeyUp(KeyEvent.VK_NUMPAD_INS)) {
                getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.RELEASED));
            }/*else if(event.getKeyTyped(Tecla.VK_NUMPAD_INS)) {
                Gui.getInstance().addEvent(new Event(Tecla.MOUSE_BUTTON_LEFT, KeyState.CLICK));
			}*/

            //Mouse Right Button
            if (event.isKeyDown(KeyEvent.VK_NUMPAD_DEL)) {
                getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_BUTTON_RIGHT, PointerState.PRESSED));
            } else if (event.isKeyUp(KeyEvent.VK_NUMPAD_DEL)) {
                getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_BUTTON_RIGHT, PointerState.RELEASED));
            }/*else if(event.getKeyTyped(Tecla.VK_NUMPAD_DEL)) {
                getMouse().addEvent(new PointerEvent(MouseButton.MOUSE_BUTTON_RIGHT, KeyState.CLICK));
			}*/

        }
    }

    private GUIEvent updateFrameEvents(PointerEvent event) {

        if (event.getState() == PointerState.CLICK) {
            return GUIEvent.REQUEST_FOCUS;
        }

        if (event.getState() == PointerState.DRAGGED) {

            if (getMouse().getY() <= TITLE_BAR_HEIGHT) {

                return GUIEvent.WINDOW_MOVE;
            }
        }

        return GUIEvent.NONE;
    }

    public void replaceWindow(AWTWindow window) {
        if (this.window != window) {
            window.setClose(false);

            this.window = window;

            //Avoid unnecessary reload
            reload(window.getContext());
        }
    }

    public void setMainApplication(Application application) {
        reload(application);
    }

    public void changeApplication() {
        Context currentApplication = getCurrentContext();
        // Remove Handlers
        modules.dispose(currentApplication);
        currentApplication.dispose();

        Context nextApplication = currentApplication.getNextApplication();

        // Setup nextApplication
        nextApplication.setSession(session);
        nextApplication.setDrawCursor(currentApplication.isDrawCursor());

        reload(nextApplication);
    }

    public Context getCurrentContext() {
        return window.getContext();
    }

    protected void reload(Context application) {
        if (application == null) {
            System.err.println(ErrorMessages.APPLICATION_NULL);
            return;
        }

        modules.init(application);

        if (application.isLoaded()) {
            Application nextApplication = applicationLoader.reloadApplication(this, application);
            window.setApplication(nextApplication);
        }
    }

    @Override
    public void updateKeyEvent(KeyEvent event) {
        Context context = getCurrentContext();

        handleApplicationKeyEvents(context, event);

        modules.updateKeyboard(event);

        updateKeyboardEvents(event);

        updateNumpadMouse(event);
    }

    protected void handleApplicationKeyEvents(Context context, KeyEvent event) {
        //Handle Application commands
        context.updateKeyboard(event);
    }

    private void fixEventPosition(PointerEvent event) {
        event.setX(event.getX() - window.getX());
        event.setY(event.getY() - window.getY());
    }

    public AWTController getControl() {
        return control;
    }

    public GUIEvent getSuperEvent() {
        return superEvent;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    @Override
    public void onLoad(Context context) {
        firstDraw = true;
        window.setApplication(context);
        context.setLoaded(true);
    }

    public void initLoaders() {
        for (Loader loader : loaders) {
            loader.setUrl(path);
            loader.initLoader();
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {

        //For Windows
        String s = IOHelper.fixPath(path);

        this.path = s;
    }

    protected Session buildSession() {
        return new Session();
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public List<Monitor> getMonitors() {
        return monitors;
    }

    public boolean isFullScreenEnable() {
        return fullScreenEnable;
    }

    public void setFullScreenEnable(boolean fullScreenEnable) {
        this.fullScreenEnable = fullScreenEnable;
    }

    public boolean isAllowFullscreen() {
        return allowFullscreen;
    }

    public void setAllowFullscreen(boolean allowFullscreen) {
        this.allowFullscreen = allowFullscreen;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public boolean isRunning() {
        return running;
    }

    public Session getSession() {
        return session;
    }

    public List<Loader> getLoaders() {
        return loaders;
    }

    public void setLoaders(List<Loader> loaders) {
        this.loaders = loaders;
    }

    public void addLoader(Loader loader) {
        loaders.add(loader);
    }

}
