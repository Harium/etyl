package com.harium.etyl.core.input.mouse;

import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.event.PointerState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

// TODO FIX ME!
@Ignore
public class MouseTest {

    Mouse mouse;

    @Before
    public void setUp() {
        mouse = new Mouse(0,0);
    }

    @Test
    public void testAddEvent() {
        PointerEvent event1 = new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE);
        PointerEvent event2 = new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.CLICK);

        mouse.addEvent(event1);
        mouse.addEvent(event2);

        Assert.assertEquals(2, mouse.getEvents().size());
        Assert.assertEquals(event1, mouse.getEvents().pop());
        Assert.assertEquals(event2, mouse.getEvents().pop());
    }

    @Test
    public void testMouseMove() {
        java.awt.event.MouseEvent event = mock(java.awt.event.MouseEvent.class);
        doReturn(10).when(event).getX();
        doReturn(20).when(event).getY();

        mouse.mouseMoved(event);


        verify(event, times(1)).getX();
        verify(event, times(1)).getY();

        Assert.assertEquals(1, mouse.getEvents().size());

        PointerEvent generatedEvent = mouse.getEvents().pop();
        Assert.assertEquals(10, generatedEvent.getX());
        Assert.assertEquals(20, generatedEvent.getY());
    }

    @Test
    public void testMouseLeftPressed() {
        java.awt.event.MouseEvent event = mock(java.awt.event.MouseEvent.class);
        doReturn(java.awt.event.MouseEvent.BUTTON1).when(event).getButton();
        doReturn(10).when(event).getX();
        doReturn(20).when(event).getY();

        mouse.mousePressed(event);

        verify(event, times(1)).getX();
        verify(event, times(1)).getY();
        Assert.assertEquals(1, mouse.getEvents().size());

        PointerEvent generatedEvent = mouse.getEvents().pop();
        Assert.assertEquals(MouseEvent.MOUSE_BUTTON_LEFT, generatedEvent.getKey());
        Assert.assertEquals(10, generatedEvent.getX());
        Assert.assertEquals(20, generatedEvent.getY());

        Mouse.DragEvent dragEvent = mouse.dragged.get(java.awt.event.MouseEvent.BUTTON1);
        Assert.assertEquals(true, dragEvent.active);
    }

    @Test
    public void testMouseLeftReleased() {
        java.awt.event.MouseEvent event = mock(java.awt.event.MouseEvent.class);
        doReturn(java.awt.event.MouseEvent.BUTTON1).when(event).getButton();
        doReturn(10).when(event).getX();
        doReturn(20).when(event).getY();

        mouse.mouseReleased(event);

        verify(event, times(1)).getX();
        verify(event, times(1)).getY();
        Assert.assertEquals(1, mouse.getEvents().size());

        PointerEvent generatedEvent = mouse.getEvents().pop();
        Assert.assertEquals(MouseEvent.MOUSE_BUTTON_LEFT, generatedEvent.getKey());
        Assert.assertEquals(10, generatedEvent.getX());
        Assert.assertEquals(20, generatedEvent.getY());

        Mouse.DragEvent dragEvent = mouse.dragged.get(java.awt.event.MouseEvent.BUTTON1);
        Assert.assertEquals(false, dragEvent.active);
    }

    @Test
    public void testInit() {
        Assert.assertEquals(5, mouse.dragged.size());
        Assert.assertTrue(mouse.dragged.containsKey(java.awt.event.MouseEvent.BUTTON1));
        Assert.assertTrue(mouse.dragged.containsKey(java.awt.event.MouseEvent.BUTTON2));
        Assert.assertTrue(mouse.dragged.containsKey(java.awt.event.MouseEvent.BUTTON3));
        Assert.assertTrue(mouse.dragged.containsKey(Mouse.MULTI_TOUCH_4));
        Assert.assertTrue(mouse.dragged.containsKey(Mouse.MULTI_TOUCH_5));
    }


}
