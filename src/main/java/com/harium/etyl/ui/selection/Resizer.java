package com.harium.etyl.ui.selection;

import com.harium.etyl.commons.Drawable;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.MouseState;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.layer.GeometricLayer;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.core.input.mouse.MouseStateChanger;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Resizer<T extends Layer> implements Drawable {

    private int count = 0;
    ResizerEvent lastEvent = null;
    Layer copy = new Layer();

    private static final int BUTTON_SIZE = 16;
    public static final int UNKNOWN = -1;
    private static final Layer NULL_LAYER = new Layer(UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN);

    protected Map<Integer, T> layers = new HashMap<Integer, T>();

    protected int selectedIndex = UNKNOWN;
    protected Layer selected = NULL_LAYER;
    protected Layer overlay = new Layer();

    private ResizerPoint selectedArea;
    protected ResizerPoint[] points;

    private ResizerListener listener;
    private MouseStateChanger mouseStateChanger;

    protected boolean moveOnly = false;
    private boolean dragged = false;
    private boolean changed = false;

    protected int offsetX = 0;
    protected int offsetY = 0;

    private int initialX = 0;
    private int initialY = 0;
    private double initialW = 0;
    private double initialH = 0;

    private int lastIndex = UNKNOWN;

    private static final int SHIFT_SPEED = 10;
    private static final int NORMAL_SPEED = 1;

    private int keyboardSpeed = 1;
    private int speedFactor = NORMAL_SPEED;

    public Resizer(MouseStateChanger mouseStateChanger) {
        super();

        this.mouseStateChanger = mouseStateChanger;

        points = new ResizerPoint[9];
        for (int i = 0; i < 8; i++) {
            points[i] = new ResizerPoint(0, 0, 1, 1);
        }

        selectedArea = new ResizerPoint(0, 0, 1, 1);
        selectedArea.setState(MouseState.MOVE);

        points[8] = selectedArea;

        points[0].setState(MouseState.ARROW_NW_SE);
        points[1].setState(MouseState.ARROW_VERTICAL);
        points[2].setState(MouseState.ARROW_NE_SW);
        points[3].setState(MouseState.ARROW_HORIZONTAL);
        points[4].setState(MouseState.ARROW_HORIZONTAL);
        points[5].setState(MouseState.ARROW_NE_SW);
        points[6].setState(MouseState.ARROW_VERTICAL);
        points[7].setState(MouseState.ARROW_NW_SE);
        points[8].setState(MouseState.MOVE);
    }

    public abstract void drawOverlay(Graphics graphics);

    public void refresh() {
        select(selected);
    }

    public void deselect() {
        selected = NULL_LAYER;
        mouseStateChanger.changeMouseState(MouseState.NORMAL);
    }

    public void select(int index) {
        select(layers.get(index));
    }

    public void select(Layer layer) {
        if (!isSelected()) {
            deselect();
        }

        this.selected = layer;
        selectedArea.copy(layer);

        int inc = 0;

        if (!moveOnly) {
            //Update 8 points
            for (int b = 0; b < 9; b++) {

                int i = b % 3;
                int j = b / 3;

                if (i == 1 && j == 1) {
                    inc = -1;
                    continue;
                }

                int offsetX = (int) (layer.getW() * (1 - layer.getScaleX())) / 2;
                int offsetY = (int) (layer.getH() * (1 - layer.getScaleY())) / 2;

                int bx = (int) (layer.getX() + offsetX + i * (layer.getW() * layer.getScaleX() / 2) - BUTTON_SIZE / 2);
                int by = (int) (layer.getY() + offsetY + j * (layer.getH() * layer.getScaleY() / 2) - BUTTON_SIZE / 2);

                points[b + inc].setBounds(bx, by, BUTTON_SIZE, BUTTON_SIZE);
            }
        }
    }


    public void handleEvent(PointerEvent event) {
        int mx = event.getX() - offsetX;
        int my = event.getY() - offsetY;

        if (!isSelected()) {
            checkMouseOver(mx, my);
        }

        if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
            if (!isSelected()) {
                checkSelection(mx, my);
            } else if (!isDragged()) {
                deselect();
            }
        }

        if (!isSelected()) {
            return;
        }

        changed = false;

        if (!dragged) {
            if (!moveOnly) {
                for (int b = 0; b < 9; b++) {
                    if (points[b].colideRectPoint(mx, my)) {
                        lastIndex = b;

                        mouseStateChanger.changeMouseState(points[b].getState());
                        changed = true;

                        handleDragEvent(event);
                        break;
                    }
                }
            } else {
                handleDragEvent(event);
            }

        }

        if (event.isButtonUp(MouseEvent.MOUSE_BUTTON_LEFT)) {
            dragged = false;
            if (lastEvent != null) {
                notifyListener(lastEvent);
                lastEvent = null;
            }
        } else if (dragged && event.isDraggedButton(MouseEvent.MOUSE_BUTTON_LEFT)) {
            resizeEvent(lastIndex, event);
            refresh();
        }

        if (!changed && event.isClicked(MouseEvent.MOUSE_BUTTON_LEFT)) {
            deselect();
        }

        if (!changed) {
            mouseStateChanger.changeMouseState(MouseState.NORMAL);
        }

    }

    protected boolean checkMouseOver(int mx, int my) {
        for (Layer component : layers.values()) {
            if (component.onMouse(mx, my)) {
                overlay.copy(component);
                overlay.setVisible(true);
                return true;
            }
        }
        return false;
    }

    protected void checkSelection(int mx, int my) {
        selectedIndex = UNKNOWN;
        for (Entry<Integer, T> entry : layers.entrySet()) {
            T component = entry.getValue();
            if (component.onMouse(mx, my)) {
                overlay.copy(component);
                selectedIndex = entry.getKey();
                select(component);
                overlay.setVisible(false);

                break;
            }
        }
    }

    private void resizeEvent(int index, PointerEvent event) {
        lastEvent = ResizerEvent.SCALE;

        switch (index) {
            case 0:
                resizeUp(event);
                resizeLeft(event);
                break;

            case 1:
                resizeUp(event);
                break;

            case 2:
                resizeUp(event);
                resizeRight(event);
                break;

            case 3:
                resizeLeft(event);
                break;

            case 4:
                resizeRight(event);
                break;

            case 5:
                resizeDown(event);
                resizeLeft(event);
                break;

            case 6:
                resizeDown(event);
                break;

            case 7:
                resizeDown(event);
                resizeRight(event);
                break;

            default:
                moveSelected(event);
                lastEvent = ResizerEvent.MOVE;
                break;
        }
    }

    private void handleDragEvent(PointerEvent event) {

        if (!dragged && event.isDraggedButton(MouseEvent.MOUSE_BUTTON_LEFT)) {
            setInitialValues();
            dragged = true;
            copy.copy(selected);
        }

        if (event.isButtonUp(MouseEvent.MOUSE_BUTTON_LEFT)) {
            dragged = false;
        }
    }

    private void setInitialValues() {
        initialX = selected.getX();
        initialY = selected.getY();
        initialW = selected.getW() * selected.getScaleX();
        initialH = selected.getH() * selected.getScaleY();
    }

    private void moveSelected(PointerEvent event) {
        selected.setX(initialX + event.getAmountX());
        selected.setY(initialY + event.getAmountY());
    }

    private void resizeUp(PointerEvent event) {
        selected.setY(initialY + event.getAmountY() / 2);
        double sy = initialH - event.getAmountY();
        selected.setScaleY(sy / selected.getH());
    }

    private void resizeDown(PointerEvent event) {
        selected.setY(initialY + event.getAmountY() / 2);
        double sy = initialH + event.getAmountY();
        selected.setScaleY(sy / selected.getH());
    }

    private void resizeLeft(PointerEvent event) {
        selected.setX(initialX + event.getAmountX() / 2);
        double sx = initialW - event.getAmountX();
        selected.setScaleX(sx / selected.getW());
    }

    private void resizeRight(PointerEvent event) {
        selected.setX(initialX + event.getAmountX() / 2);
        double sx = initialW + event.getAmountX();
        selected.setScaleX(sx / selected.getW());
    }

    public boolean isDragged() {
        return dragged || changed;
    }

    public boolean isSelected() {
        return selected != NULL_LAYER;
    }

    public GeometricLayer getSelectedLayer() {
        return selected;
    }

    public void handleKeyEvent(KeyEvent event) {

        if (event.isAnyKeyDown(KeyEvent.VK_SHIFT_LEFT, KeyEvent.VK_SHIFT_RIGHT)) {
            speedFactor = SHIFT_SPEED;
        } else if (event.isAnyKeyUp(KeyEvent.VK_SHIFT_LEFT, KeyEvent.VK_SHIFT_RIGHT)) {
            speedFactor = NORMAL_SPEED;
        }

        if (event.isKeyDown(KeyEvent.VK_UP_ARROW)) {
            if (selectedIndex != UNKNOWN) {
                selected.offsetY(-speed());
                notifyListener(ResizerEvent.MOVE);
                refresh();
            }
        } else if (event.isKeyDown(KeyEvent.VK_DOWN_ARROW)) {
            if (selectedIndex != UNKNOWN) {
                selected.offsetY(+speed());
                notifyListener(ResizerEvent.MOVE);
                refresh();
            }
        }

        if (event.isKeyDown(KeyEvent.VK_LEFT_ARROW)) {
            if (selectedIndex != UNKNOWN) {
                selected.offsetX(-speed());
                notifyListener(ResizerEvent.MOVE);
                refresh();
            }
        } else if (event.isKeyDown(KeyEvent.VK_RIGHT_ARROW)) {
            if (selectedIndex != UNKNOWN) {
                selected.offsetX(+speed());
                notifyListener(ResizerEvent.MOVE);
                refresh();
            }
        }
    }

    protected int speed() {
        return keyboardSpeed * speedFactor;
    }

    private void notifyListener(ResizerEvent event) {
        if (listener == null)
            return;

        listener.onResize(event, selectedIndex, selected, copy);
    }

    public ResizerListener getListener() {
        return listener;
    }

    public void setListener(ResizerListener listener) {
        this.listener = listener;
    }

    public Collection<T> getLayers() {
        return layers.values();
    }

    public void setLayers(List<T> layers) {
        for (T layer : layers) {
            addLayer(layer);
        }
    }

    private Integer generateId() {
        count++;
        return count;
    }

    public int getId(T layer) {
        for (Entry<Integer, T> entry : layers.entrySet()) {
            if (entry.getValue().equals(layer)) {
                return entry.getKey();
            }
        }
        return UNKNOWN;
    }

    public int addLayer(T layer) {
        int id = generateId();
        layers.put(id, layer);

        return id;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void removeLayer(int index) {
        if (selectedIndex == index) {
            deselect();
        }
        layers.remove(index);
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public boolean isMoveOnly() {
        return moveOnly;
    }

    public void setMoveOnly(boolean moveOnly) {
        this.moveOnly = moveOnly;
    }
}