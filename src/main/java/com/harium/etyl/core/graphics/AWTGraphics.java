package com.harium.etyl.core.graphics;

import com.harium.etyl.awt.camera.Camera;
import com.harium.etyl.commons.graphics.Graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public interface AWTGraphics extends Graphics, TextGraphics {

    void setFastImage(BufferedImage image);

    void setImage(BufferedImage image);


    /**
     *
     * @param img
     * @param dx1
     * @param dy1
     * @param dx2
     * @param dy2
     * @param sx1
     * @param sy1
     * @param sx2
     * @param sy2
     * @param observer
     */
    void drawImage (Image img, int dx1, int dy1, int dx2, int dy2, int sx1 , int sy1, int sx2, int sy2, ImageObserver observer);

    void drawImage (Image img, float dx1, float dy1, float dx2, float dy2, float sx1 , float sy1, float sx2, float sy2, ImageObserver observer);

    Graphics2D getGraphics();

    AffineTransform getTransform();

    /**
     *
     * @param tx
     */
    void setTransform(AffineTransform tx);

    /**
     *
     * @param tx
     */
    void transform(AffineTransform tx);

    /**
     *
     * @param color
     */
    void setColor(Color color);

    void setComposite(AlphaComposite composite);

    /**
     *
     * @param img
     * @param x
     * @param y
     */
    void drawImage(Image img, int x, int y);

    /**
     *
     * @param polygon
     */
    void drawPolygon(Polygon polygon);

    /**
     *
     * @param polygon
     */
    void fillPolygon(Polygon polygon);

    /**
     *
     * @param shape
     */
    void draw(Shape shape);

    /**
     *
     * @param shape
     */
    void fill(Shape shape);

	/*void setGraphics(GLGraphics2D graphics) {
		this.screen = graphics;
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}*/

	/*
	 *
	 * @param startX
	 * @param startY
	 * @param w
	 * @param h
	 * @param rgbArray
	 * @param offset
	 * @param scansize

	void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {
		vimg..setRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}*/

    void drawImage(BufferedImage image, int x, int y);

    void drawImage(BufferedImage image, float x, float y);

    void setBackground(Color color);

    void setPaint(Paint paint);

    void resetPaint();

    void setCamera(Camera camera);

    void resetCamera(Camera camera);
}
