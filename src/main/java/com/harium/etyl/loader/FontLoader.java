package com.harium.etyl.loader;

import com.harium.etyl.commons.loader.LoaderImpl;
import com.harium.etyl.core.graphics.Font;
import com.harium.etyl.util.io.IOHelper;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuripourre
 */

public class FontLoader extends LoaderImpl {

    public static String DEFAULT_FONT = "Dialog.plain";

    private static FontLoader instance = null;

    private String[] systemFonts;

    protected Map<String, Font> fonts = new HashMap<>();

    private FontLoader() {
        super();

        folder = "assets/fonts/";
        loadSystemFont(DEFAULT_FONT);
    }

    public static FontLoader getInstance() {
        if (instance == null) {
            instance = new FontLoader();
        }

        return instance;
    }

    public Font getFont(String fontName, float size) {
        Font font = loadFont(fontName);
        font.getFont().deriveFont(size);

        return font;
    }

    public Font loadFont(String path, boolean absolute) {
        if (path.isEmpty()) {
           return defaultFont();
        }
        String fullPath = fullPath(path, absolute);

        if (!fonts.containsKey(fullPath)) {

            URL dir = null;

            if (!absolute) {
                try {
                    // Local file
                    File file = new File(fullPath);
                    if (!file.exists()) {
                        return defaultFont();
                    }
                    dir = new URL(url, fullPath);
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            } else {

                if (!fullPath.startsWith(IOHelper.FILE_PREFIX)) {
                    fullPath = IOHelper.FILE_PREFIX + fullPath;

                    // Local file
                    File file = new File(fullPath);
                    if (!file.exists()) {
                        return defaultFont();
                    }
                }

                try {
                    dir = new URL(fullPath);
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            try {
                java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, dir.openStream());
                Font f = new Font(font);

                fonts.put(fullPath, f);
                return f;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Font f = fonts.get(fullPath);
        if (f != null) {
            return f;
        } else {
            return defaultFont();
        }
    }

    private Font defaultFont() {
        return fonts.get(DEFAULT_FONT);
    }

    public String[] getSystemFonts() {
        return systemFonts;
    }

    public void setSystemFonts(String[] systemFonts) {
        this.systemFonts = systemFonts;
    }

    public void disposeFont(String fontName) {
        String fullPath = fullPath(fontName, false);
        fonts.remove(fullPath);
    }

    public Font loadFont(String path) {
        return loadFont(path, false);
    }

    public Font loadSystemFont(String name) {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        java.awt.Font[] allFonts = e.getAllFonts();
        for (java.awt.Font font : allFonts) {
            if (name.equals(font.getFontName())) {
                Font f = new Font(font);
                fonts.put(name, f);
                return f;
            }
        }
        return defaultFont();
    }
}

