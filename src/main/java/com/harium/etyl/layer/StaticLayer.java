package com.harium.etyl.layer;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.loader.image.ImageLoader;
import com.harium.etyl.util.io.IOHelper;

import java.awt.geom.AffineTransform;

/**
 *
 * @author yuripourre
 *
 */

public class StaticLayer extends Layer {

	protected String path = "";

	public StaticLayer() {
		super();
	}

	/**
	 *
	 * @param x
	 * @param y
	 */
	public StaticLayer(int x, int y) {
		super(x,y);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public StaticLayer(int x, int y, int w, int h) {
		super(x,y,w,h);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param path
	 */
	public StaticLayer(int x, int y, int w, int h, String path) {
		super(x,y);

		this.path = path;
		load();

		this.w = w;
		this.h = h;
	}

	/**
	 *
	 * @param path
	 */
	public StaticLayer(String path) {
		this.path = path;
		load();
	}

	public StaticLayer(String path, boolean absolute) {
		this.path = path;
		StaticLayer loaded = load(absolute);
		if (absolute) {
			this.path = loaded.getPath();
		}
	}

	public String getPath() {
		return path;
	}

	/**
	 *
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
		load();
	}

	public void onLoad(String path) {
		this.path = path;
	}

	/**
	 *
	 * @param w
	 * @param h
	 */
	public void setSize(int w , int h) {
		setW(w);
		setH(h);
	}

	/**
	 *
	 * @param layer
	 */
	public void cloneLayer(StaticLayer layer) {
		this.path = layer.path;
		this.w = layer.w;
		this.h = layer.h;
	}

	public StaticLayer load() {
		return load(false);
	}

	public StaticLayer load(boolean absolute) {
		StaticLayer layer;
		if (path.startsWith(IOHelper.STREAM_PREFIX)) {
			layer = ImageLoader.getInstance().loadImageAsStream(path);
		} else {
			layer = ImageLoader.getInstance().loadImage(path, absolute);
		}
		this.w = layer.w;
		this.h = layer.h;

		// It is needed to update ImageLayer
		setW(layer.w);
		setH(layer.h);
		setOriginCenter();

		return layer;
	}

	public AffineTransform getTransform() {
		return getTransform(0, 0);
	}

	public AffineTransform getTransform(float offsetX, float offsetY) {
		AffineTransform transform = new AffineTransform();

		float px = getX() + offsetX;
		float py = getY() + offsetY;

		transform.translate(px + originX, py + originY);

		// Scale
		if (scaleX != 1 || scaleY != 1) {
			transform.scale(scaleX, scaleY);
		}

		// Rotate
		if (angle != 0) {
			if ((scaleY > 0 && scaleX > 0)
					|| (scaleX < 0 && scaleY < 0)) {
				transform.rotate(Math.toRadians(angle));
			} else {
				transform.rotate(Math.toRadians(-angle));
			}
		}

		// Move to origin (centered)
		transform.translate(-px - originX, -py - originY);

		return transform;
	}

}
