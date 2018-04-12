package com.harium.etyl.awt.core.input;

import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.KeyEventListener;
import com.harium.etyl.commons.event.KeyState;
import com.harium.etyl.core.input.keyboard.Keyboard;

import java.awt.event.KeyListener;
import java.util.*;

/**
 * @author yuripourre
 */

public class AWTKeyboard implements KeyListener, Keyboard {

    private KeyEventListener listener;

    private Deque<KeyEvent> keyEvents = new ArrayDeque<>();

    private Map<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
    private Map<Integer, KeyState> keyStates = new HashMap<Integer, KeyState>();

    private Set<Integer> changed = new HashSet<>();

    public AWTKeyboard(KeyEventListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    public void init() {
    }

    public void update(long now) {
        Iterator<Integer> changedIterator = changed.iterator();

        while (changedIterator.hasNext()) {
            Integer key = changedIterator.next();
            KeyState keyState = getState(key);
            boolean pressed = keys.get(key);

            if (pressed) {

                if (keyState == KeyState.RELEASED) {
                    keyStates.put(key, KeyState.ONCE);
                    addKeyEvent(new KeyEvent(key, KeyState.PRESSED));
                } else if (keyState != KeyState.PRESSED) {
                    keyStates.put(key, KeyState.PRESSED);
                }

            } else {

                if ((keyState == KeyState.ONCE) || (keyState == KeyState.PRESSED)) {
                    keyStates.put(key, KeyState.FIRST_RELEASED);
                } else if (keyState == KeyState.FIRST_RELEASED) {
                    keyStates.put(key, KeyState.RELEASED);
                    addKeyEvent(new KeyEvent(key, KeyState.RELEASED));

                    changedIterator.remove();
                }
            }
        }

        poll(listener);
    }

    private KeyState getState(Integer key) {
        KeyState state = keyStates.get(key);
        if (state == null) {
            state = KeyState.RELEASED;
        }

        return state;
    }

    public void poll(KeyEventListener listener) {
        while (!keyEvents.isEmpty()) {
            KeyEvent event = keyEvents.pop();
            if (event == null) {
                System.err.println("AWTKeyboard ERROR!");
                continue;
            }
            listener.updateKeyEvent(event);
        }
    }

    public void keyPressed(java.awt.event.KeyEvent ke) {

        int code = getKeyFromEvent(ke);

        keys.put(code, true);

        changed.add(code);

        ke.consume();
    }

    public void keyReleased(java.awt.event.KeyEvent ke) {

        int code = getKeyFromEvent(ke);

        keys.put(code, false);

        changed.add(code);

        ke.consume();
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent ke) {

        int code = getKeyFromEvent(ke);

        char c = ke.getKeyChar();

        //TODO Fix typed
        if (c != KeyEvent.CHAR_UNDEFINED) {
            addKeyEvent(new KeyEvent(code, c, KeyState.TYPED));
        }

        ke.consume();
    }

    private int getKeyFromEvent(java.awt.event.KeyEvent ke) {
        int code = ke.getKeyCode();

        if (ke.getKeyLocation() != java.awt.event.KeyEvent.KEY_LOCATION_STANDARD) {
            code += ke.getKeyLocation() * 100;
        }

        return code;
    }

    public boolean hasPendingEvent() {
        return !changed.isEmpty();
    }

    private void addKeyEvent(KeyEvent event) {
        long now = System.currentTimeMillis();
        event.setTimestamp(now);
        keyEvents.add(event);
    }

}