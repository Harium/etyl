package com.harium.etyl.core.graphics;

import com.harium.etyl.commons.layer.GeometricLayer;

import java.awt.*;
import java.awt.font.FontRenderContext;

public interface TextGraphics {

    void setFont(java.awt.Font font);

    void setFont(com.harium.etyl.core.graphics.Font font);

    java.awt.Font getFont();

    void setFontSize(float size);

    void setFontStyle(int fontStyle);

    FontRenderContext getFontRenderContext();

    FontMetrics getFontMetrics();

    int textWidth(String text);

    void drawString(String text, int x, int y);

    void drawString(String text, float x, float y);

    void drawString(String text, int x, int y, int w, int h);

    void drawString(String text, GeometricLayer layer);

    void drawString(String text, GeometricLayer layer, int offsetX, int offsetY);

    void drawString(String text, float x, float y, float w, float h);

    void drawStringExponent(String text, String exponent, int x, int y);

    void drawStringExponentShadow(String text, String exponent, int x, int y);

    void drawStringShadow(String text, int x, int y, int w, int h);

    void drawStringShadow(String text, int x, int y, int w, int h, Color shadowColor);

    void drawStringShadow(String text, int x, int y, int w, int h, com.harium.etyl.commons.graphics.Color shadowColor);

    void drawStringShadow(String text, float x, float y, float w, float h, Color shadowColor);

    void drawStringShadow(String text, float x, float y, float w, float h, com.harium.etyl.commons.graphics.Color shadowColor);

    void drawStringBorder(String text, float x, float y);

    void drawStringBorder(String text, int x, int y, int w, int h);

    void drawStringBorderX(String text, float y);

    void drawStringShadow(String text, int x, int y);

    void drawStringShadow(String text, float x, float y);

    void drawStringShadow(String text, GeometricLayer layer);

    void drawStringShadow(String text, int x, int y, Color shadowColor);

    void drawStringShadow(String text, int x, int y, com.harium.etyl.commons.graphics.Color shadowColor);

    void drawStringShadow(String text, float x, float y, Color shadowColor);

    void drawStringShadow(String text, float x, float y, com.harium.etyl.commons.graphics.Color shadowColor);

    void drawStringShadowX(String text, int y);

    void drawStringShadowX(String text, float y);

    void drawStringX(String text, float offsetX, float y);

    void drawStringX(String text, int y);

    void drawStringX(String text, float y);

}
