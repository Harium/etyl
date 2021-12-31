package com.harium.etyl.input;

import com.harium.etyl.commons.Updatable;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.KeyEventListener;
import com.harium.etyl.commons.loader.LoaderImpl;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author yuripourre
 */

public class JoystickHandler extends LoaderImpl implements Updatable, Runnable {

    private static JoystickHandler instance = null;

    public static JoystickHandler getInstance() {
        if (instance == null) {
            instance = new JoystickHandler();
        }

        return instance;
    }

    private boolean initialized = false;

    private KeyEventListener listener;

    private int updateDelay = 5;//5 ms

    private int joysticks = 5;

    private List<KeyEvent> joystickEvents = new ArrayList<KeyEvent>();

    private Map<Integer, Joystick> inputStreams = new HashMap<Integer, Joystick>();

    private ScheduledExecutorService executor;

    private ScheduledFuture<?> future;

    public void init(int joysticks) {
        this.joysticks = joysticks;
        initLoader();

        executor = Executors.newSingleThreadScheduledExecutor();
        future = executor.scheduleAtFixedRate(this, updateDelay, updateDelay, TimeUnit.MILLISECONDS);
    }

    @Override
    public void initLoader() {

        initialized = true;

        int j = 0;

        for (; j < joysticks; j++) {

            try {
                this.inputStreams.put(j, new Joystick(j));
                System.out.println("Joystick " + j + " found!");

            } catch (FileNotFoundException e) {
                System.out.println("Joystick " + j + " not found.");
                break;
            }
        }

        this.joysticks = j;
    }

    @Override
    public void run() {

        if (!initialized) {
            init(joysticks);
        }

        update(0);
    }

    public List<KeyEvent> getJoyEvents() {
        return joystickEvents;
    }

    public void update(long now) {

        Iterator<Map.Entry<Integer, Joystick>> iterator = inputStreams.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<Integer, Joystick> entry = iterator.next();
            listen(entry.getKey());
        }

        notifyListener();
    }

    private void notifyListener() {
        Iterator<KeyEvent> eventIterator = joystickEvents.listIterator();

        while (eventIterator.hasNext()) {
            KeyEvent event = eventIterator.next();
            listener.updateJoystickEvent(event);
            eventIterator.remove();
        }
    }

    private void listen(Integer id) {

        List<KeyEvent> eventList = inputStreams.get(id).listen();

        if (!eventList.isEmpty()) {
            joystickEvents.addAll(eventList);
        }

    }

    public KeyEventListener getListener() {
        return listener;
    }

    public void setListener(KeyEventListener listener) {
        this.listener = listener;
    }

    public ScheduledFuture<?> getFuture() {
        return future;
    }

}
