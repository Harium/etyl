package com.harium.etyl.loader.image;

import java.awt.image.BufferedImage;

/*
 * based on GifFrame code
 */

public class ImageFrame {

	private BufferedImage image;
	
	private int delay = 0;

	public ImageFrame(BufferedImage image, int delay) {
		super();
		this.image = image;
		this.delay = delay;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
		
}
