package com.harium.etyl.ui;

import com.harium.etyl.awt.AWTArrowDrawer;
import com.harium.etyl.commons.context.Context;
import com.harium.etyl.commons.event.*;
import com.harium.etyl.commons.module.Module;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.core.input.mouse.MouseStateChanger;
import com.harium.etyl.i18n.Language;
import com.harium.etyl.i18n.LanguageModule;
import com.harium.etyl.theme.etyllic.EtylArrowTheme;
import com.harium.etyl.ui.theme.ArrowDrawer;
import com.harium.etyl.ui.theme.ArrowTheme;
import com.harium.etyl.ui.theme.Theme;
import com.harium.etyl.ui.theme.ThemeManager;
import com.harium.etyl.ui.theme.listener.ThemeListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UI implements Module, ThemeListener, MouseStateChanger {
    private static UI instance;

    private Context context;

    public int w, h;
    private int mx, my;

    public static boolean timerClick = false;
    public ArrowDrawer arrowDrawer = new AWTArrowDrawer();
    public ArrowTheme arrowTheme = new EtylArrowTheme();

    //Timer click arc
    private int arc = 0;

    private View focus = NULL_VIEW;

    //View above Mouse
    public View mouseOver = NULL_VIEW;
    protected View focusComponent = NULL_VIEW;

    private boolean overClickable = false;

    public boolean needReload = false;

    private boolean locked = false;
    private boolean updating = false;
    private boolean updatingEvents = false;

    public static List<GUIEvent> guiEvents = new ArrayList<GUIEvent>();
    private static List<View> views = new ArrayList<View>();

    private UI() {
        super();
    }

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();

            ThemeManager.getInstance().setArrowThemeListener(instance.arrowDrawer);
            ThemeManager.getInstance().setArrowTheme(instance.arrowTheme);
            ThemeManager.getInstance().setThemeListener(instance);
        }

        return instance;
    }

    public void updateGui(List<View> components) {
        Iterator<GUIEvent> eventIterator = guiEvents.listIterator();

        while (eventIterator.hasNext()) {
            GUIEvent event = eventIterator.next();

            dispatchEvent(event, components);
            // Better than clear (avoid clear unhandled events)
            eventIterator.remove();
        }
    }

    @Override
    public void updateGuiEvent(GUIEvent event) {
        dispatchEvent(event, views);
    }

    public void dispatchEvent(GUIEvent event, List<View> views) {
        Iterator<View> componentIterator = views.listIterator();

        while (componentIterator.hasNext()) {
            View component = componentIterator.next();
            dispatchEvent(event, component);
        }
    }

    private void dispatchEvent(GUIEvent event, View component) {
        component.updateEvent(event);

        Iterator<View> viewIterator = component.views.listIterator();
        while (viewIterator.hasNext()) {
            View child = viewIterator.next();
            dispatchEvent(event, child);
        }
    }

    public void updateMouseViews(PointerEvent event, List<View> views) {
        updatingEvents = true;

        Iterator<View> viewIterator = views.listIterator();
        while (viewIterator.hasNext()) {
            View view = viewIterator.next();
            boolean wasMouseOver = view.isMouseOver();
            //Update View
            updateEvent(view, view.updateMouse(event));

            if (view == mouseOver) {

                if (!view.isMouseOver()) {
                    // Lose Mouse Focus
                    mouseOver = NULL_VIEW;
                }

            } else if (!wasMouseOver && view.isMouseOver()) {
                // Gain Mouse Focus
                mouseOver = view;
            }

            //Update Children
            updateMouseViews(event, view.getViews());
        }
        updatingEvents = false;
    }

    private GUIEvent updateMouse(View view, PointerEvent event) {

        if (!view.isVisible()) {
            return GUIEvent.NONE;
        }

        GUIEvent result = view.updateMouse(event);

        if (GUIEvent.MOUSE_IN == result) {
            setMouseOver(view);
        } else if (GUIEvent.MOUSE_OUT == result) {
            resetMouseOver();
        } else if (GUIEvent.GAIN_FOCUS == result) {
            setFocus(view);
        } else if (GUIEvent.LOST_FOCUS == result) {
            removeFocus(view);
        } else if (result != GUIEvent.NONE && result != null) {
            updateEvent(view, result);
        }

        return GUIEvent.NONE;
    }

    private GUIEvent updateEvent(View view, GUIEvent lastEvent) {

        switch (lastEvent) {

            case GAIN_FOCUS:

                //Remove focus from last
                if (focus != null) {
                    focus.updateEvent(GUIEvent.LOST_FOCUS);
                }

                view.setOnFocus(true);

                focus = view;

                break;

            case LOST_FOCUS:

                if (view == focus) {
                    //TODO Mouse.loseFocus()
                    //events.add(new Event(Tecla.NONE, KeyState.LOSE_FOCUS));
                    //events.add(new Event(DeviceType.KEYBOARD, Tecla.NONE, KeyState.LOSE_FOCUS));

                    focus = NULL_VIEW;
                }

                break;

			/*case MOUSE_OVER:
            if(!mouseOver) {
				mouseOver = true;
				mouseOverClickable = true;
				//TODO componente.setMouseOver(true);
			}

			break;*/

			/*case MOUSE_OVER_UNCLICKABLE:
            if(!mouseOver) {
				mouseOver = true;
				mouseOverClickable = false;
			}			
			break;*/

            case MOUSE_OVER_WITH_FOCUS:

                //lastOver = componente;
                break;

            case NEXT_COMPONENT:

                System.out.println("LostFocus");

                //controle.getTeclado().loseFocus();
                //events.add(new Event(DeviceType.KEYBOARD, Tecla.NONE, KeyState.))

                view.updateEvent(GUIEvent.LOST_FOCUS);

                break;

            case WINDOW_CLOSE:

                //TODO
                //((Window)componente.setClose(true));

                break;

			/*case ONCE:
            //this.event (param)
			event.setState(KeyState.PRESSED);

			//Prevent consume
			events.add(event);
			break;
			 */

            case UPDATE_MOUSE:
                updateMouse(view, new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, mx, my));
                break;

            case APPLICATION_CHANGED:
                //listener.changeApplication();
                break;

            default:

			/*if(view.isMouseOver()) {
                view.update(GUIEvent.MOUSE_OUT);
			}*/

                break;
        }

        //view.setLastEvent(lastEvent);
        //view.update(lastEvent);
        //view.executeAction(lastEvent);

        return GUIEvent.NONE;
    }

    public void setMouseOver(View view) {
        if (mouseOver != null) {
            removeMouseOver(mouseOver);
        }

        mouseOver = view;
        mouseOver.mouseIn();

        overClickable = true;
    }

    public void resetMouseOver() {
        removeMouseOver(mouseOver);
        mouseOver = NULL_VIEW;
        overClickable = false;
    }

    private void setFocus(View view) {
        if (focus != NULL_VIEW) {
            removeFocus(focus);
        }
        focus = view;
        view.setOnFocus(true);
        view.updateEvent(GUIEvent.GAIN_FOCUS);
    }

    private void removeFocus(View view) {
        if (view == focus) {
            view.setOnFocus(false);
            view.updateEvent(GUIEvent.LOST_FOCUS);
            focus = NULL_VIEW;
        }
    }

    private void removeMouseOver(View view) {
        if (view == null)
            return;
        view.setMouseOver(false);
        view.updateEvent(GUIEvent.MOUSE_OUT);
    }

    public void drawCursor(Graphics g) {
        arrowDrawer.setLocation(mx, my);
        arrowDrawer.draw(g);
        //Draw Accessible Cursor
        if (timerClick && overClickable) {
            arrowDrawer.drawTimerArc(g, arc);
        }
    }

    //Move to ArrowDrawer
    public void updateTimerClick(long now) {
        final int speed = 3;

        if (mouseOver != null) {

            if (timerClick) {

                if (arc < 360) {
                    arc += speed;
                } else {

                    updateEvent(mouseOver, GUIEvent.MOUSE_LEFT_BUTTON_DOWN);
                    updateEvent(mouseOver, GUIEvent.MOUSE_LEFT_BUTTON_UP);

                    resetMouseOver();
                }
            }

        } else {
            if (timerClick) {
                arc = 0;
            }
        }
    }

    public void drawUIViews(Graphics g) {
        Iterator<View> componentIterator = views.listIterator();

        while (componentIterator.hasNext()) {
            View child = componentIterator.next();
            child.drawWithChildren(g);
        }
    }

    @Override
    public void updateMouse(PointerEvent event) {
        mx = event.getX();
        my = event.getY();
        updateMouseViews(event, views);
    }

    public void updateKeyboard(KeyEvent event) {
        //Only the focused component handles the keyboard
        if (focus != null) {
            GUIEvent focusEvent = focus.updateKeyboard(event);

            if (focusEvent != GUIEvent.NONE && focusEvent != null) {
                //TODO Update NExtComponent

                if (focusEvent == GUIEvent.NEXT_COMPONENT) {
                    View next = focus.findNext();

                    if (next != null) {
                        updateEvent(focus, focusEvent);
                        updateEvent(next, GUIEvent.GAIN_FOCUS);
                    }
                } else {
                    updateEvent(focus, focusEvent);
                }
            }
        }
    }

    @Override
    public void init(Context context) {
        this.context = context;
        this.w = context.getW();
        this.h = context.getH();
    }

    @Override
    public void dispose(Context context) {
        views.clear();
    }

    @Override
    public void draw(Graphics g) {
        if (locked || isUpdating()) {
            return;
        }

        drawUIViews(g);

        //Draw Cursor
        if (context.isDrawCursor()) {
            drawCursor(g);
        }
    }

    @Override
    public void update(long now) {
        updating = true;

        if (needReload) {
            fastReload();
            needReload = false;
        }

        updateTimerClick(now);

        updateGui(views);

        updating = false;
    }

    private void fastReload() {
        locked = true;

        //Just Rebuild UI Components
        for (View view : views) {
            view.rebuild();
        }

        locked = false;
    }

    @Override
    public void updateTheme(Theme theme) {
        needReload = true;
    }

    public static void add(View view) {
        UI.views.add(view);
    }

    public static void addAll(List<View> views) {
        UI.views.addAll(views);
    }

    public static void remove(View view) {
        UI.views.remove(view);
    }

    public static void clear() {
        UI.views.clear();
    }

    private boolean isUpdating() {
        return updating || updatingEvents;
    }

    public static void changeLanguage(Language language) {
        LanguageModule.getInstance().changeLanguage(language);
        guiEvents.add(GUIEvent.LANGUAGE_CHANGED);
    }

    @Override
    public void changeMouseState(MouseState state) {
        arrowDrawer.changeState(state);
    }

    public View getMouseOver() {
        return mouseOver;
    }

    public boolean isMouseOver() {
        return mouseOver != NULL_VIEW;
    }

    private static final View NULL_VIEW = new View() {
        @Override
        public GUIEvent updateKeyboard(KeyEvent event) {
            return null;
        }

        @Override
        public void updateEvent(GUIEvent event) {
        }
    };

}
