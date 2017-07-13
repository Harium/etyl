package com.harium.etyl.awt.helper;

import java.awt.*;

public class ColorHelper {

    public static Color darker(Color color, int intensity) {
        int a = color.getAlpha();
        int r = darker(color.getRed(), intensity);
        int g = darker(color.getGreen(), intensity);
        int b = darker(color.getBlue(), intensity);

        Color result = new Color(r, g, b, a);
        return result;
    }

    public static Color brighter(Color color, int intensity) {
        int a = color.getAlpha();
        int r = brighter(color.getRed(), intensity);
        int g = brighter(color.getGreen(), intensity);
        int b = brighter(color.getBlue(), intensity);

        Color result = new Color(r, g, b, a);
        return result;
    }

    public static Color darker(com.harium.etyl.commons.graphics.Color color, int intensity) {
        int a = color.getAlpha();
        int r = darker(color.getRed(), intensity);
        int g = darker(color.getGreen(), intensity);
        int b = darker(color.getBlue(), intensity);

        Color result = new Color(r, g, b, a);
        return result;
    }

    public static Color brighter(com.harium.etyl.commons.graphics.Color color, int intensity) {
        int a = color.getAlpha();
        int r = brighter(color.getRed(), intensity);
        int g = brighter(color.getGreen(), intensity);
        int b = brighter(color.getBlue(), intensity);

        Color result = new Color(r, g, b, a);
        return result;
    }

    private static int darker(int channel, int intensity) {
        int color = channel - intensity;
        if (color < 0) {
            color = 0;
        }
        return color;
    }

    private static int brighter(int channel, int intensity) {
        int color = channel + intensity;
        if (color > 255) {
            color = 255;
        }
        return color;
    }

    public static Color convert(com.harium.etyl.commons.graphics.Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

}
