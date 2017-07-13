package com.harium.etyl.loader.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class AWTReader implements ImageReader {

	@Override
	public BufferedImage loadImage(URL url) throws IOException {
		return ImageIO.read(url);
	}

	@Override
	public BufferedImage loadImage(InputStream input) throws IOException {
		return ImageIO.read(input);
	}

}