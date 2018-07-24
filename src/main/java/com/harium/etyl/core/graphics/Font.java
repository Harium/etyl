package com.harium.etyl.core.graphics;

/**
 * Font class to wrap native fonts
 * Constants borrowed from awt
 */
public class Font {

    /**
     * The plain style constant.
     */
    public static final int PLAIN = 0;

    /**
     * The bold style constant.  This can be combined with the other style
     * constants (except PLAIN) for mixed styles.
     */
    public static final int BOLD = 1;

    /**
     * The italicized style constant.  This can be combined with the other
     * style constants (except PLAIN) for mixed styles.
     */
    public static final int ITALIC = 2;

    protected java.awt.Font font;

    public Font(java.awt.Font font) {
        this.font = font;
    }

    public java.awt.Font getFont() {
        return font;
    }
}
