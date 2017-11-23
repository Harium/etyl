package com.harium.etyl.util.concurrency;

import java.util.*;
import java.util.Map.Entry;

public class ConcurrentSet<T> {

    private Set<T> set;
    private Map<T, ItemAction> itemActions;

    private boolean locked = false;

    public ConcurrentSet() {
        super();

        set = new HashSet<T>();
        itemActions = new HashMap<T, ItemAction>();
    }

    public void add(T t) {
        if (!locked) {
            set.add(t);
        } else {
            itemActions.put(t, ItemAction.ADD_ITEM);
        }
    }

    public void remove(T t) {
        if (!locked) {
            set.remove(t);
        } else {
            itemActions.put(t, ItemAction.REMOVE_ITEM);
        }
    }

    public Set<T> lock() {
        locked = true;

        return set;
    }

    public void unlock() {
        locked = false;

        if (!itemActions.isEmpty()) {
            Iterator<Map.Entry<T, ItemAction>> iterator = itemActions.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<T, ItemAction> entry = iterator.next();
                ItemAction action = entry.getValue();

                if (ItemAction.ADD_ITEM.equals(action)) {
                    set.add(entry.getKey());
                } else if (ItemAction.REMOVE_ITEM.equals(action)) {
                    set.remove(entry.getKey());
                }
            }

            itemActions.clear();
        }
    }

    public Set<T> getSet() {
        return set;
    }

}