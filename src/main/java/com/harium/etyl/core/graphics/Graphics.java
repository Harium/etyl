package com.harium.etyl.core.graphics;

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

import com.harium.etyl.awt.camera.Camera;
import com.harium.etyl.geometry.Line2;
import com.harium.etyl.geometry.Point2D;
import com.harium.etyl.commons.math.Vector2i;
import com.harium.etyl.commons.layer.GeometricLayer;
import com.harium.etyl.commons.layer.Layer;


public interface Graphics extends TextGraphics {

	void setFastImage(BufferedImage image);

	void setImage(BufferedImage image);

	void setFps(float fps);

	float getFps();

	void resetImage();

	int getWidth();
	
	int getHeight();
	
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
	void drawImage (Image img, int dx1, int dy1,int dx2, int dy2, int sx1 , int sy1, int sx2, int sy2, ImageObserver observer);

	void drawImage (Image img, float dx1, float dy1, float dx2, float dy2, float sx1 , float sy1, float sx2, float sy2, ImageObserver observer);

	Graphics2D getGraphics();

    /**
     * Method to draw a single pixel
     * @param x
     * @param y
     */
	void putPixel(int x, int y);

	/**
	 * 
	 * @param layer
	 */
	void drawRect(GeometricLayer layer);

	/**
	 * 
	 * @param layer
	 */
	void drawRect(Layer layer);

	/**
	 * 
	 * @param layer
	 */
	void fillOval(GeometricLayer layer);

	/**
	 * 
	 * @param layer
	 * @param startAngle
	 * @param arcAngle
	 */
	void fillArc(GeometricLayer layer, int startAngle, int arcAngle);

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

	void resetTransform();

	/**
	 * 
	 * @param color
	 */
	void setColor(int color) ;

	/**
	 * 
	 * @param color
	 */
	void setColor(Color color);

	void setColor(com.harium.etyl.commons.graphics.Color color);

	/**
	 * 
	 * @param percent
	 */
	void setAlpha(int percent);
	
	void resetAlpha();

	void setComposite(AlphaComposite composite);

	void setClearAlpha(int percent);

	/**
	 * 
	 * @param opacity
	 */
	void setOpacity(int opacity);

	void resetOpacity();

	/**
	 * Method to prepare graphics to draw images ONLY
	 */
	void beginImageBatch();
	
	/**
	 * Method to end images ONLY mode
	 */
	void endImageBatch();
	
	/**
	 * 
	 * @param img
	 * @param x
	 * @param y
	 */
	void drawImage(Image img, int x, int y);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	void drawArc(float x, float y, float w, float h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	void drawLine(int x1,int y1,int x2,int y2);

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	void drawLine(float x1,float y1,float x2,float y2);

	/**
	 * 
	 * @param p
	 * @param q
	 */
	void drawLine(Point2D p, Point2D q);
	
	/**
	 * 
	 * @param line
	 */
	void drawLine(Line2 line);

	/**
	 * @param xPoints
	 * @param yPoints
	 * @param nPoints
	 */
	void drawPolygon(int[] xPoints, int[] yPoints, int nPoints);
	
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
	 * @param layer
	 */
	void fillRect(GeometricLayer layer);

	void fillRect(Layer layer);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	void fillRect(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	void fillRect(float x, float y, float w, float h);


	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param raised
	 */
	void fill3DRect(int x, int y, int w, int h, boolean raised);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param raised
	 */
	void fill3DRect(float x, float y, float w, float h, boolean raised);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	void fillArc(int x, int y, int w, int h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	void fillArc(float x, float y, float w, float h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	void drawRect(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	void drawRect(double x, double y, double w, double h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	void drawOval(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	void drawOval(float x, float y, float w, float h);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	void drawCircle(int cx, int cy, int radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	void drawCircle(float cx, float cy, float radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	void drawCircle(double cx, double cy, double radius);

	/**
	 * 
	 * @param point
	 * @param radius
	 */
	void drawCircle(Point2D point, int radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	void fillCircle(int cx, int cy, int radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	void fillCircle(float cx, float cy, float radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	void fillCircle(double cx, double cy, double radius);

	/**
	 * 
	 * @param point
	 * @param radius
	 */
	void fillCircle(Point2D point, int radius);
	
	/**
	 * 
	 * @param point
	 * @param radius
	 */
	void fillCircle(Vector2i point, int radius);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	void fillOval(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	void fillOval(float x, float y, float w, float h);

	/**
	 * @param xPoints
	 * @param yPoints
	 * @param nPoints
	 */
	void fillPolygon(int[] xPoints, int[] yPoints, int nPoints);

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

	/**
	 * 
	 * @param x
	 * @param y
	 */
	void translate(int x, int y);

	/**
	 * 
	 * @param x
	 * @param y
	 */
	void translate(double x, double y);

	void rotate(double angle);

	void setBackground(Color color);

	void clearRect(int x, int y, int width, int height);

	void setPaint(Paint paint);

	void resetPaint();
	
	void setShadowColor(Color shadowColor);
	
	void dispose();

	void setCamera(Camera camera);

	void resetCamera(Camera camera);

	void drawArrow(Point2D p, Point2D q, int arrowSize);
	
	void drawArrow(Point2D p, Point2D q);

	void setClip(int x, int y, int w, int h);
	
	void resetClip();

}