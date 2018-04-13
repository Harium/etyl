package com.harium.etyl.core.input.mouse;

import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.event.PointerState;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.*;

/**
 * @author yuripourre
 */

public class Mouse implements MouseMotionListener, MouseInputListener, MouseWheelListener {

    protected int x = 0;
    protected int y = 0;
    protected int z = 0;

    // Allows mutiple cursors / multi-touch
    Map<Integer, DragEvent> dragged = new HashMap<>();
    Deque<PointerEvent> events = new ArrayDeque<>(8);

    public Mouse(int x, int y) {
        super();

        this.x = x;
        this.y = y;

        dragged.put(java.awt.event.MouseEvent.BUTTON1, new DragEvent());
        dragged.put(java.awt.event.MouseEvent.BUTTON2, new DragEvent());
        dragged.put(java.awt.event.MouseEvent.BUTTON3, new DragEvent());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setCoordinates(int x, int y) {
        setX(x);
        setY(y);
    }

    private void addEvent(int button, PointerState state) {
        addEvent(button, state, 0);
    }

    private void addEvent(int button, PointerState state, int amount) {
        addEvent(button, state, 0, amount);
    }

    private void addEvent(int button, PointerState state, int amountX, int amountY) {
        MouseEvent key = MouseEvent.MOUSE_NONE;

        switch (button) {
            case java.awt.event.MouseEvent.BUTTON1:
                key = MouseEvent.MOUSE_BUTTON_LEFT;
                break;
            case java.awt.event.MouseEvent.BUTTON2:
                key = MouseEvent.MOUSE_BUTTON_MIDDLE;
                break;
            case java.awt.event.MouseEvent.BUTTON3:
                key = MouseEvent.MOUSE_BUTTON_RIGHT;
                break;
        }

        events.add(new PointerEvent(key, state, x, y, amountX, amountY));
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent me) {

        PointerState state = PointerState.CLICK;

        if (me.getClickCount() == 2) {
            state = PointerState.DOUBLE_CLICK;
        } else if (me.getClickCount() > 2) {
            state = PointerState.MULTIPLE_CLICK;
        }

        setCoordinates(me.getX(), me.getY());
        addEvent(me.getButton(), state, me.getClickCount());

        me.consume();
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent me) {

        setCoordinates(me.getX(), me.getY());

        addEvent(me.getButton(), PointerState.PRESSED);

        pressDragButton(me.getButton());

        me.consume();
    }

    private void pressDragButton(int button) {
        DragEvent event = dragged.get(button);
        event.active = true;
    }

    private void releaseDragButton(int button) {
        DragEvent event = dragged.get(button);
        event.active = false;
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent me) {
        setCoordinates(me.getX(), me.getY());

        addEvent(me.getButton(), PointerState.RELEASED);

        releaseDragButton(me.getButton());

        me.consume();
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent me) {
        setCoordinates(me.getX(), me.getY());

        addMouseMoveEvent(x, y);

        me.consume();
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent me) {
        //mouseMoved( me );
        me.consume();
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent me) {
        me.consume();
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent me) {
        DragEvent event = dragged.get(me.getButton());
        event.x = me.getX();
        event.y = me.getY();

        int deltaX = me.getX() - event.x;
        int deltaY = me.getY() - event.y;

        setCoordinates(me.getX(), me.getY());

        addEvent(me.getButton(), PointerState.DRAGGED, deltaX, deltaY);
        me.consume();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        MouseEvent key = MouseEvent.MOUSE_WHEEL_DOWN;

        if (mwe.getWheelRotation() < 0) {
            key = MouseEvent.MOUSE_WHEEL_UP;
        }

        events.add(new PointerEvent(key, PointerState.PRESSED, x, y, mwe.getWheelRotation()));
    }

    public void addMouseMoveEvent(int x, int y) {
        events.add(new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, x, y));
    }

    public Deque<PointerEvent> getEvents() {
        return events;
    }

    public void addEvent(PointerEvent event) {
        events.add(event);
    }

    public boolean hasEvents() {
        return !events.isEmpty();
    }

    /* package */ class DragEvent {
        int x;
        int y;
        boolean active = false;
    }
}
