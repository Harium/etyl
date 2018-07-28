package com.harium.etyl.core.stroke;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.core.graphics.stroke.BasicStroke;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class BasicStrokeTest {

    Graphics g;
    BasicStroke stroke;

    @Before
    public void setUp() {
        g = mock(Graphics.class);
        stroke = new BasicStroke();
    }

    @Test
    public void testDrawLine() {
        stroke.drawLine(g, 0, 0, 10, 0);
        verify(g, times(11)).putPixel(any(int.class), any(int.class));
    }

    @Test
    public void testDrawLineWithWidth() {
        stroke.setWidth(2);
        stroke.drawLine(g, 0, 0, 10, 0);
        verify(g, times(22)).putPixel(any(int.class), any(int.class));
    }

    @Test
    public void testDrawRect() {
        stroke.drawRect(g, 0, 0, 10, 10);
        verify(g, times(10 * 4 - 4)).putPixel(any(int.class), any(int.class));
    }

}
