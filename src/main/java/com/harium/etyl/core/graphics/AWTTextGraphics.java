package com.harium.etyl.core.graphics;

import java.awt.*;
import java.awt.font.FontRenderContext;

public interface AWTTextGraphics extends TextGraphics {

    java.awt.Font getFont();

    FontRenderContext getFontRenderContext();

    FontMetrics getFontMetrics();

    void setFont(java.awt.Font font);

    void drawStringShadow(String text, int x, int y, Color shadowColor);

    void drawStringShadow(String text, int x, int y, int w, int h, Color shadowColor);

    void drawStringShadow(String text, float x, float y, Color shadowColor);

    void drawStringShadow(String text, float x, float y, float w, float h, Color shadowColor);

}
