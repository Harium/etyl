package com.harium.etyl.loader;

import com.harium.etyl.core.graphics.Font;
import org.junit.Assert;
import org.junit.Test;

public class FontLoaderTest {

    @Test
    public void testInit() {
        Assert.assertFalse(FontLoader.getInstance().fonts.isEmpty());
        Assert.assertTrue(FontLoader.getInstance().fonts.containsKey(FontLoader.DEFAULT_FONT));
    }

    @Test
    public void testLoadSystemFont() {
        Font font = FontLoader.getInstance().loadSystemFont("Dialog.plain");
        Assert.assertNotNull(font);
    }

}
