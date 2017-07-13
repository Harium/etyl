package com.harium.etyl.layer;

import com.harium.etyl.awt.helper.ColorHelper;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.loader.image.ImageLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

/**
 * @author yuripourre
 */

public class BufferedLayer extends ImageLayer {
    private static final int MAX_CHANNEL = 0xff;

    protected BufferedImage buffer;
    protected BufferedImage originalBuffer;

    protected Graphics2D graphics;

    /**
     * @param x
     * @param y
     */
    public BufferedLayer(int x, int y) {
        super(x, y);
    }

    /**
     * @param x
     * @param y
     * @param path
     */
    public BufferedLayer(int x, int y, String path) {
        super(x, y, path);
        copy(ImageLoader.getInstance().getImage(path));
    }

    /**
     * @param x
     * @param y
     * @param path
     * @param absolute
     */
    public BufferedLayer(int x, int y, String path, boolean absolute) {
        super(x, y, path, absolute);
        copy(ImageLoader.getInstance().getImage(path, absolute));
    }

    /**
     * @param path
     */
    public BufferedLayer(String path) {
        this(0, 0, path);
    }

    /**
     * @param x
     * @param y
     * @param buffer
     */
    public BufferedLayer(int x, int y, BufferedImage buffer) {
        super(x, y);

        this.w = buffer.getWidth();
        this.h = buffer.getHeight();

        copy(buffer);
    }

    /**
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public BufferedLayer(int x, int y, int w, int h) {
        this(x, y);

        this.w = w;
        this.h = h;

        originalBuffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        clearGraphics();
    }

    /**
     * @param x
     * @param y
     * @param w
     * @param h
     * @param imageData - Data as byteArray
     */
    public BufferedLayer(int x, int y, int w, int h, byte[] imageData) {
        this(x, y);

        this.w = w;
        this.h = h;

        originalBuffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        createBuffer(imageData);
    }

    /**
     * @param buffer
     */
    public BufferedLayer(BufferedImage buffer) {
        this(0, 0, buffer);
    }

    /**
     * @param buffer
     */
    public void copy(Image buffer) {

        if (buffer == null)
            return;

        int w = buffer.getWidth(null);
        int h = buffer.getHeight(null);

        this.w = w;
        this.h = h;

        this.originalBuffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        this.graphics = originalBuffer.createGraphics();
        graphics.drawImage(buffer, 0, 0, null);

        resetImage();
    }

    /**
     * @param buffer
     */
    public void copy(BufferedImage buffer) {

        this.originalBuffer = new BufferedImage((int) w, (int) h, BufferedImage.TYPE_INT_ARGB);
        this.originalBuffer.getGraphics().drawImage(buffer, 0, 0, null);

        w = buffer.getWidth();
        h = buffer.getHeight();

        resetImage();
    }

    public void cropImage(int srcX, int srcY, int srcW, int srcH) {
        buffer = new BufferedImage(srcW, srcH, BufferedImage.TYPE_INT_ARGB);

        graphics = buffer.createGraphics();
        graphics.drawImage(originalBuffer, -srcX, -srcY, null);

        this.w = srcW;
        this.h = srcH;
    }


    /**
     * ImagemBuffer back to original state
     */
    public void resetImage() {
        buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        graphics = buffer.createGraphics();
        graphics.drawImage(originalBuffer, 0, 0, null);
    }

    public Graphics2D clearGraphics() {

        Graphics2D g2 = originalBuffer.createGraphics();
        g2.setColor(ColorHelper.convert(com.harium.etyl.commons.graphics.Color.TRANSPARENT));
        g2.clearRect(x, y, w, h);

        graphics = buffer.createGraphics();
        graphics.setBackground(new Color(MAX_CHANNEL, MAX_CHANNEL, MAX_CHANNEL, 0));
        graphics.clearRect(0, 0, w, h);

        return graphics;
    }

    public void refresh() {
        buffer.getGraphics().drawImage(originalBuffer, 0, 0, null);
    }

    /**
     * Offset all pixels to the selected value
     * Image receive color
     *
     * @param red
     * @param green
     * @param blue
     */
    public void offsetRGB(int red, int green, int blue) {
        resetImage();
        offsetPixels(red, green, blue);
    }

    public void offsetNegativeRed(int red) {
        resetImage();
        offsetPixels(0, -red, -red);
    }

    public void offsetNegativeGreen(int green) {
        resetImage();
        offsetPixels(-green, 0, -green);
    }

    public void offsetNegativeBlue(int blue) {
        resetImage();
        offsetPixels(-blue, -blue, 0);
    }

    private void offsetPixels(int offsetRed, int offsetGreen, int offsetBlue) {
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {

                int rgb = originalBuffer.getRGB(i, j);

                int a = (rgb >> 24) & 0xff;

                int r = ((rgb & 0x00ff0000) >> 16);
                int g = ((rgb & 0x0000ff00) >> 8);
                int b = ((rgb & 0x000000ff) >> 0);

                r += offsetRed;
                g += offsetGreen;
                b += offsetBlue;

                //Verify pixel range 0~255
                if (r > MAX_CHANNEL) {
                    r = MAX_CHANNEL;
                } else if (r < 0) {
                    r = 0;
                }

                //Verify pixel range 0~255
                if (g > MAX_CHANNEL) {
                    g = MAX_CHANNEL;
                } else if (g < 0) {
                    g = 0;
                }

                //Verify pixel range 0~255
                if (b > MAX_CHANNEL) {
                    b = MAX_CHANNEL;
                } else if (b < 0) {
                    b = 0;
                }

                buffer.setRGB(i, j, new Color(r, g, b, a).getRGB());
            }
        }
    }

    public void flipVertical() {
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -h);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        buffer = op.filter(buffer, null);
    }

    public void flipHorizontal() {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-w, 0);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        buffer = op.filter(buffer, null);
    }

    public void flipHorizontalAndVertical() {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
        tx.translate(-w, -h);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        buffer = op.filter(buffer, null);
    }

    public void resize(int width, int height) {
        double scaleW = (double) width / (double) originalBuffer.getWidth();
        double scaleH = (double) height / (double) originalBuffer.getHeight();

        AffineTransform transform = AffineTransform.getScaleInstance(scaleW, scaleH);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        buffer = op.filter(buffer, null);

        this.w = width;
        this.h = height;
    }

    public byte[][][] getBytes() {

        DataBufferInt db = (DataBufferInt) buffer.getRaster().getDataBuffer();

        int[] by = db.getData();

        byte pixels[][][] = new byte[w][h][3];

        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {

                int rgb = by[i + j * w];

                byte r = (byte) ((rgb & 0x00ff0000) >> 16);
                byte g = (byte) ((rgb & 0x0000ff00) >> 8);
                byte b = (byte) (rgb & 0x000000ff);

                pixels[i][j][0] = r;
                pixels[i][j][1] = g;
                pixels[i][j][2] = b;
            }
        }

        return pixels;
    }

    public void setBuffer(BufferedImage image) {
        this.originalBuffer = image;

        this.w = image.getWidth();
        this.h = image.getHeight();

        resetImage();

    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public boolean colideAlphaPoint(int px, int py) {

        if (this.colideRectPoint(px, py)) {

            int mx = px - x;

            int my = py - y;

            if (mx >= buffer.getWidth() || my >= buffer.getHeight()) {
                return false;
            }

            Color color = new Color(buffer.getRGB(mx, my), true);

            return color.getAlpha() == MAX_CHANNEL;
        }

        return false;

    }

    public Color getColor(int px, int py) {
        if (this.colideRectPoint(px, py)) {
            int mx = px - x;
            int my = py - y;

            if (mx >= buffer.getWidth() || my >= buffer.getHeight()) {
                return null;
            }

            Color color = new Color(buffer.getRGB(mx, my), true);

            return color;
        }

        return null;
    }

    public Graphics2D getGraphics() {
        return graphics;
    }

    @Override
    public void simpleDraw(Graphics g, int x, int y) {
        g.drawImage(buffer, x, y, x + w, y + h,
                srcX, srcY, srcX + w, srcY + h, null);
    }

    public void createBuffer(byte[] imageData) {
        DataBufferByte dataBuffer = new DataBufferByte(imageData, imageData.length);
        originalBuffer.setData(Raster.createRaster(originalBuffer.getSampleModel(), dataBuffer, null));
        resetImage();
    }

}
