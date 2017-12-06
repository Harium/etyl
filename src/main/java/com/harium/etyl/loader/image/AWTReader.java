package com.harium.etyl.loader.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AWTReader implements ImageReader {

    @Override
    public BufferedImage loadImage(URL url) throws IOException {
        return loadImage(url.openStream());
    }

    @Override
    public BufferedImage loadImage(InputStream input) throws IOException {
        BufferedImage original = ImageIO.read(input);
        BufferedImage converted = convertImage(original);
        return converted;
    }

    public static BufferedImage convertImage(BufferedImage original) {
        BufferedImage converted = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
        converted.getGraphics().drawImage(original, 0, 0, null);
        return converted;
    }

}