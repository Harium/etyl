package com.harium.etyl.core.graphics;

/**
 * Font class to wrap native fonts
 * Constants borrowed from awt
 */
public class Font {

    public static final int PLAIN = 0;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;

    protected java.awt.Font font;

    public Font(java.awt.Font font) {
        this.font = font;
    }

    public java.awt.Font getFont() {
        return font;
    }

    public int getStyle() {
        return font.getStyle();
    }

    public float getSize() {
        return font.getSize();
    }
}
