package com.harium.etyl.loader.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface ImageReader {
    @Deprecated
    BufferedImage loadImage(URL url) throws IOException;

    BufferedImage loadImage(InputStream input) throws IOException;
}
