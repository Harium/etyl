package com.harium.etyl.ui;

import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.event.PointerState;
import com.harium.etyl.ui.base.BaseButton;
import com.harium.etyl.ui.base.BaseTextField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UITest {

    @Before
    public void setUp() {
        UI.clear();
    }

    @Test
    public void testUpdatePointerEvent() {
        BaseButton button = new BaseButton(0, 0, 100, 20);
        UI.add(button);
        UI.getInstance().resetMouseOver();

        Assert.assertFalse(UI.getInstance().isMouseOver());

        PointerEvent move = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 10, 10);
        UI.getInstance().updateMouse(move);
        Assert.assertNotNull(UI.getInstance().getMouseOver());
    }

    @Test
    public void testUpdateMouse() {
        BaseButton b = new BaseButton(10, 10, 200, 20);
        BaseTextField f = new BaseTextField(10, 30, 200, 60);

        List<View> views = new ArrayList<View>();
        views.add(b);
        views.add(f);

        UI.addAll(views);

        PointerEvent moveOverButtonEvent = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 20, 20);
        UI.getInstance().updateMouseViews(moveOverButtonEvent, views);

        Assert.assertTrue(b.isMouseOver());

        PointerEvent moveOverFieldEvent = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 20, 40);
        UI.getInstance().updateMouseViews(moveOverFieldEvent, views);

        PointerEvent clickEvent = new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.PRESSED, 20, 40);
        UI.getInstance().updateMouseViews(clickEvent, views);

        Assert.assertTrue(f.isOnFocus());
    }

    @Test
    public void testMouseOut() {
        BaseButton button = new BaseButton(0, 0, 100, 20);
        UI.add(button);
        UI.getInstance().resetMouseOver();

        Assert.assertFalse(UI.getInstance().isMouseOver());

        PointerEvent move = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 10, 10);
        UI.getInstance().updateMouse(move);
        Assert.assertEquals(button, UI.getInstance().getMouseOver());

        move = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, 10, 30);
        UI.getInstance().updateMouse(move);
        Assert.assertNotEquals(button, UI.getInstance().getMouseOver());
    }
}
