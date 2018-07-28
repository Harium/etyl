package com.harium.etyl.core.graphics;

/**
 * Font class to wrap native fonts
 * Constants borrowed from awt
 */
public class Font {

    protected java.awt.Font font;

    public Font(java.awt.Font font) {
        this.font = font;
    }

    public java.awt.Font getFont() {
        return font;
    }
}
