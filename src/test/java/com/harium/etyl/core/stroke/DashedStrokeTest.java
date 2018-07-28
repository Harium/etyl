package com.harium.etyl.core.stroke;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.core.graphics.stroke.DashedStroke;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DashedStrokeTest {

    Graphics g;
    DashedStroke stroke;

    @Before
    public void setUp() {
        g = mock(Graphics.class);
        stroke = new DashedStroke();
    }

    @Test
    public void testDrawLine() {
        stroke.drawLine(g, 0, 0, stroke.getDashSpacing() + stroke.getDashSize(), 0);
        verify(g, times(stroke.getDashSize())).putPixel(any(int.class), any(int.class));
    }

    @Test
    public void testDrawRect() {
        stroke.drawRect(g, 0, 0, stroke.getDashSpacing() + stroke.getDashSize(), stroke.getDashSpacing() + stroke.getDashSize() + 1);
        verify(g, times(stroke.getDashSize() * 4)).putPixel(any(int.class), any(int.class));
    }

}
