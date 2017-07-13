package com.harium.etyl.layer;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.commons.math.EtyllicaMath;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.core.input.mouse.Mouse;
import com.harium.etyl.loader.image.ImageLoader;

/**
 * @author yuripourre
 */

public class ImageLayer extends StaticLayer {

    protected int srcX = 0;
    protected int srcY = 0;
    protected int srcW = 0;
    protected int srcH = 0;

    public ImageLayer() {
        super();
    }

    /**
     * @param x
     * @param y
     */
    public ImageLayer(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public ImageLayer(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.srcW = w;
        this.srcH = h;
        setOriginCenter();
    }

    /**
     * @param x
     * @param y
     * @param w
     * @param h
     * @param path
     */
    public ImageLayer(int x, int y, int w, int h, String path) {
        super(x, y, w, h, path);
        this.srcW = w;
        this.srcH = h;
        setOriginCenter();
    }

    /**
     * @param x
     * @param y
     * @param w
     * @param h
     * @param srcX
     * @param srcY
     * @param path
     */
    public ImageLayer(int x, int y, int w, int h, int srcX, int srcY, String path) {
        super(x, y, w, h, path);
        this.srcX = srcX;
        this.srcY = srcY;
        this.srcW = w;
        this.srcH = h;
        setOriginCenter();
    }

    /**
     * @param x
     * @param y
     * @param path
     */
    public ImageLayer(int x, int y, String path) {
        super(path);
        setLocation(x, y);
    }

    /**
     * @param path
     */
    public ImageLayer(String path) {
        this(0, 0, path);
    }

    public ImageLayer(String path, boolean absolute) {
        this(0, 0, path, absolute);
    }

    public ImageLayer(int x, int y, String path, boolean absolute) {
        super(path, absolute);
        setLocation(x, y);
    }

    /**
     * @return srcX
     */
    public int getSrcX() {
        return srcX;
    }

    /**
     * @param srcX
     */
    public void setSrcX(int srcX) {
        this.srcX = srcX;
    }

    /**
     * @return srcY
     */
    public int getSrcY() {
        return srcY;
    }

    /**
     * @param srcY
     */
    public void setSrcY(int srcY) {
        this.srcY = srcY;
    }

    public int getSrcW() {
        return srcW;
    }

    public void setSrcW(int srcW) {
        this.srcW = srcW;
    }

    public int getSrcH() {
        return srcH;
    }

    public void setSrcH(int srcH) {
        this.srcH = srcH;
    }

    /**
     * @param srcX
     * @param srcY
     */
    public void setLocationSrc(int srcX, int srcY) {
        this.srcX = srcX;
        this.srcY = srcY;
    }

    /**
     * @param srcX
     * @param srcY
     */
    public void setSrc(int srcX, int srcY, int srcW, int srcH) {
        this.srcX = srcX;
        this.srcY = srcY;
        this.srcW = srcW;
        this.srcH = srcH;
    }

    public int getImageWidth() {
        return w;
    }

    public int getImageHeight() {
        return h;
    }

    // Src methods
    @Override
    public int getW() {
        return getSrcW();
    }

    @Override
    public int getH() {
        return getSrcH();
    }

    @Override
    public void setW(int w) {
        this.srcW = w;
    }

    @Override
    public void setH(int h) {
        this.srcH = h;
    }

    public boolean colideRetangular(int bx, int by, int bw, int bh) {
        return colideRectRect(bx, by, bw, bh);
    }

    public boolean colideRetangular(Layer b) {
        return colideRetangular(b.getX(), b.getY(), b.getW(), b.getH());
    }

    // Based on code at: http://developer.coronalabs.com/code/checking-if-point-inside-rotated-rectangle
    public boolean colideRotated(int mx, int my) {
        double sx = EtyllicaMath.mod(scaleX);
        double sy = EtyllicaMath.mod(scaleY);

        int scaledOriginX = (int) (originX * sx);
        int scaledOriginY = (int) (originY * sy);

        //Pivot Point of rotation
        int px = x + scaledOriginX;
        int py = y + scaledOriginY;

        double c = Math.cos(angle);
        double s = Math.sin(angle);

        // Unrotate the point depending on the rotation of the rectangle
        double rotatedX = px + c * (mx - px) - s * (my - py);
        double rotatedY = py + s * (mx - px) + c * (my - py);

        // perform a normal check if the new point is inside the
        // bounds of the unrotated rectangle
        int leftX = px - scaledOriginX;
        int rightX = px + scaledOriginX;
        int topY = py - scaledOriginY;
        int bottomY = py + scaledOriginY;

        return (leftX <= rotatedX && rotatedX <= rightX && topY <= rotatedY && rotatedY <= bottomY);
    }

    @Override
    public void draw(Graphics g) {
        draw(g, 0, 0);
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        if (!visible) {
            return;
        }

        if (opacity < 0xff) {
            g.setOpacity(opacity);
        }

        g.setTransform(getTransform(offsetX, offsetY));
        simpleDraw(g);
        g.resetTransform();

        if (opacity < 0xff) {
            g.resetOpacity();
        }
    }

    @Override
    public void simpleDraw(Graphics g) {
        simpleDraw(g, x, y);
    }

    public void simpleDraw(Graphics g, int x, int y) {
        simpleDraw(g, x, y, getW(), getH());
    }

    public void simpleDraw(Graphics g, int x, int y, int w, int h) {
        if (path.isEmpty()) {
            return;
        }
        g.drawImage(ImageLoader.getInstance().getImage(path), x, y, x + w, y + h,
                srcX, srcY, srcX + w, srcY + h, null);
    }

    public boolean onMouse(Mouse mouse) {
        return colideRectPoint(mouse.getX(), mouse.getY());
    }

    public void copy(ImageLayer b) {
        super.copy(b);

        this.srcX = b.srcX;
        this.srcY = b.srcY;
        this.srcW = b.srcW;
        this.srcH = b.srcH;

        this.path = b.path;
    }

}
