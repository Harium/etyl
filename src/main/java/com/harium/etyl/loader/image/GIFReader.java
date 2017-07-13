package com.harium.etyl.loader.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.harium.etyl.loader.image.gif.GIFDecoder;

public class GIFReader implements ImageReader, AnimationReader {

	private static GIFReader instance = null;
	
	public static GIFReader getInstance() {
		if(instance==null) {
			instance = new GIFReader();
		}

		return instance;
	}
	
	public GIFReader() {
		super();
	}
	
	@Override
	public BufferedImage loadImage(URL url) throws IOException {
		GIFDecoder decoder = new GIFDecoder();
		decoder.read(url.getPath());
		return decoder.getImage();
	}

	@Override
	public BufferedImage loadImage(InputStream input) throws IOException {
		GIFDecoder decoder = new GIFDecoder();
		decoder.read(input);
		return decoder.getImage();
	}

	@Override
	public List<ImageFrame> loadAnimation(URL url) throws IOException {
		GIFDecoder decoder = new GIFDecoder();
		decoder.read(url.getPath());
		return decoder.getFrames();
	}
	
}
