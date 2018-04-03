package com.harium.etyl.core.graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import com.harium.etyl.awt.camera.Camera;
import com.harium.etyl.geometry.Line2;
import com.harium.etyl.geometry.Point2D;
import com.harium.etyl.commons.math.Vector2i;
import com.harium.etyl.commons.layer.GeometricLayer;
import com.harium.etyl.commons.layer.Layer;

/**
 * 
 * @author yuripourre
 *
 */

public interface Graphics extends TextGraphics {

	public void setFastImage(BufferedImage image);

	public void setImage(BufferedImage image);

	public void setFps(float fps);

	public float getFps();

	public void resetImage();

	public int getWidth();
	
	public int getHeight();
	
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
	public void drawImage (Image img, int dx1, int dy1,int dx2, int dy2, int sx1 , int sy1, int sx2, int sy2, ImageObserver observer);

	public void drawImage (Image img, float dx1, float dy1, float dx2, float dy2, float sx1 , float sy1, float sx2, float sy2, ImageObserver observer);

	public Graphics2D getGraphics();

	/**
	 * 
	 * @param layer
	 */
	public void drawRect(GeometricLayer layer);

	/**
	 * 
	 * @param layer
	 */
	public void drawRect(Layer layer);

	/**
	 * 
	 * @param layer
	 */
	public void fillOval(GeometricLayer layer);

	/**
	 * 
	 * @param layer
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(GeometricLayer layer, int startAngle, int arcAngle);

	/**
	 * 
	 * @param width
	 */
	public void setLineWidth(float width);

	public AffineTransform getTransform();

	/**
	 * 
	 * @param tx
	 */
	public void setTransform(AffineTransform tx);

	/**
	 * 
	 * @param tx
	 */
	public void transform(AffineTransform tx);

	public void resetTransform();

	/**
	 * Set basic stroke with width 1f 
	 */
	public void resetStroke();

	/**
	 * 
	 * @param stroke
	 */
	public void setStroke(Stroke stroke);

	/**
	 * 
	 * @param color
	 */
	public void setColor(int color) ;

	/**
	 * 
	 * @param color
	 */
	public void setColor(Color color);

	public void setColor(com.harium.etyl.commons.graphics.Color color);

	/**
	 * 
	 * @param percent
	 */
	public void setAlpha(int percent);
	
	public void resetAlpha();

	public void setComposite(AlphaComposite composite);

	public void setClearAlpha(int percent);

	/**
	 * 
	 * @param opacity
	 */
	public void setOpacity(int opacity);

	public void resetOpacity();

	/**
	 * Method to prepare graphics to draw images ONLY
	 */
	public void beginImageBatch();
	
	/**
	 * Method to end images ONLY mode
	 */
	public void endImageBatch();
	
	/**
	 * 
	 * @param img
	 * @param x
	 * @param y
	 */
	public void drawImage(Image img, int x, int y);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void drawArc(float x, float y, float w, float h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x1,int y1,int x2,int y2);

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(float x1,float y1,float x2,float y2);

	/**
	 * 
	 * @param p
	 * @param q
	 */
	public void drawLine(Point2D p, Point2D q);
	
	/**
	 * 
	 * @param line
	 */
	public void drawLine(Line2 line);

	/**
	 * @param xPoints
	 * @param yPoints
	 * @param nPoints
	 */
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints);
	
	/**
	 * 
	 * @param polygon
	 */
	public void drawPolygon(Polygon polygon);

	/**
	 * 
	 * @param polygon
	 */
	public void fillPolygon(Polygon polygon);

	/**
	 * 
	 * @param layer
	 */
	public void fillRect(GeometricLayer layer);

	public void fillRect(Layer layer);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillRect(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillRect(float x, float y, float w, float h);


	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param raised
	 */
	public void fill3DRect(int x, int y, int w, int h, boolean raised);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param raised
	 */
	public void fill3DRect(float x, float y, float w, float h, boolean raised);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(int x, int y, int w, int h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(float x, float y, float w, float h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawRect(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawRect(double x, double y, double w, double h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawOval(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawOval(float x, float y, float w, float h);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(int cx, int cy, int radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(float cx, float cy, float radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(double cx, double cy, double radius);

	/**
	 * 
	 * @param point
	 * @param radius
	 */
	public void drawCircle(Point2D point, int radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(int cx, int cy, int radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(float cx, float cy, float radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(double cx, double cy, double radius);

	/**
	 * 
	 * @param point
	 * @param radius
	 */
	public void fillCircle(Point2D point, int radius);
	
	/**
	 * 
	 * @param point
	 * @param radius
	 */
	public void fillCircle(Vector2i point, int radius);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillOval(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillOval(float x, float y, float w, float h);

	/**
	 * @param xPoints
	 * @param yPoints
	 * @param nPoints
	 */
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints);

	/**
	 * 
	 * @param shape
	 */
	public void draw(Shape shape);

	/**
	 * 
	 * @param shape
	 */
	public void fill(Shape shape);

	/*public void setGraphics(GLGraphics2D graphics) {
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

	public void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {
		vimg..setRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}*/

	public void drawImage(BufferedImage image, int x, int y);

	public void drawImage(BufferedImage image, float x, float y);

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void translate(int x, int y);

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void translate(double x, double y);

	public void rotate(double angle);

	public void setBackground(Color color);

	public void clearRect(int x, int y, int width, int height);

	public void setPaint(Paint paint);

	public void resetPaint();
	
	public void setShadowColor(Color shadowColor);
	
	public void dispose();

	public void setCamera(Camera camera);

	public void resetCamera(Camera camera);

	public void drawArrow(Point2D p, Point2D q, int arrowSize);
	
	public void drawArrow(Point2D p, Point2D q);

	public void setClip(int x, int y, int w, int h);
	
	public void resetClip();
	
}