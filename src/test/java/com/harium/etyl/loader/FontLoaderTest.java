package com.harium.etyl.loader;

import com.harium.etyl.core.graphics.Font;
import com.harium.etyl.util.PathHelper;
import com.harium.etyl.util.io.IOHelper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FontLoaderTest {

    @BeforeClass
    public static void init() {
        FontLoader.getInstance().setUrl(IOHelper.FILE_PREFIX + PathHelper.currentDirectory() + "assets/");
    }

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

    @Test
    public void testLoadEmptyFont() {
        Font font = FontLoader.getInstance().loadFont("");
        Assert.assertNotNull(font);
    }

    @Test
    public void testLoadInvalidFont() {
        Font font = FontLoader.getInstance().loadFont("null.ttf");
        Assert.assertNotNull(font);
    }

}
