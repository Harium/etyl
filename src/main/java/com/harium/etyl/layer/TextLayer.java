package com.harium.etyl.layer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.loader.FontLoader;

/**
 * 
 * @author yuripourre
 *
 */

public class TextLayer extends ImageLayer {

	private String text;

	private int style = Font.PLAIN;

	private float size = 16;

	private Font font = null;

	private String fontName = "";

	private Color color = Color.BLACK;

	private Color borderColor = Color.GRAY;

	private boolean border = false;

	private float borderWidth = 4f;

	private boolean antiAliased = true;

	private boolean fractionalMetrics = false;

	public TextLayer(String text) {
		this(0,0,text);
	}

	public TextLayer(int x, int y, String text) {
		super(x,y);

		setText(text);
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
		this.font = FontLoader.getInstance().loadFont(fontName).getFont().deriveFont(style, size);
	}

	@Override
	public void simpleDraw(Graphics g, int x, int y) {

		if (font == null) {
			font = g.getFont().deriveFont(style, size);
		}

		g.setFont(font);
		
		if (!border) {
			g.setColor(color);
			g.drawString(text, x, y);
		} else {

			FontRenderContext frc = new FontRenderContext(null, antiAliased, fractionalMetrics);
			
			TextLayout layout = new TextLayout(text, font, frc);

			Shape sha = layout.getOutline(AffineTransform.getTranslateInstance(x,y));

			g.setStroke(new BasicStroke(borderWidth));

			g.setColor(borderColor);
			g.draw(sha);

			g.setColor(color);
			g.fill(sha);
		}

	}

	@Override
	public AffineTransform getTransform() {
		
		if(font == null)
			return AffineTransform.getScaleInstance(1, 1);
		
		
		AffineTransform transform = new AffineTransform();

		FontRenderContext frc = new FontRenderContext(null, antiAliased, fractionalMetrics);

		TextLayout layout = new TextLayout(text, font, frc);

		Rectangle2D bounds = layout.getBounds();

		float height = size;
		float width = (float) Math.ceil(bounds.getWidth());

		if(angle != 0) {
			transform.concatenate(AffineTransform.getRotateInstance(Math.toRadians(angle),x+w/2, y+h/2));
		}

		if(scaleX != 1 || scaleY != 1) {

			double sw = width*scaleX;
			double sh = height*scaleY;

			double dx = sw/2-width/2;
			double dy = sh/2-height/2;

			transform.translate(x-width/2-dx, y-height/2+dy);

			AffineTransform tr2 = new AffineTransform();

			tr2.translate(width/2, height/2);
			tr2.scale(scaleX, scaleY);
			tr2.translate(-x, -y);

			transform.concatenate(tr2);

		}

		return transform;
	}

	public void setColor(int r, int g, int b) {
		color = new Color(r%256,g%256,b%256);
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getColor() {
		return color;
	}

	public void setText(String text) {
		if(!text.isEmpty()) {
			this.text = text;
			computeBoundingBox();
		}else{
			this.text = " ";
		}
	}

	private void computeBoundingBox() {

		Font font;

		if(this.font!=null) {
			font = this.font;
		}else{
			font = new Font(Font.DIALOG, Font.PLAIN, (int)size);
		}

		//Compute Bounding box
		FontRenderContext frc = new FontRenderContext(null, antiAliased, fractionalMetrics);
		TextLayout layout = new TextLayout(text, font, frc);

		Rectangle2D bounds = layout.getBounds();

		this.h = (int)size;
		this.w = (int)Math.ceil(bounds.getWidth());


	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isBorder() {
		return border;
	}

	public String getText() {
		return text;
	}

	public boolean isAntiAliased() {
		return antiAliased;
	}

	public void setAntiAliased(boolean antiAliased) {
		this.antiAliased = antiAliased;
	}

	public boolean isFractionalMetrics() {
		return fractionalMetrics;
	}

	public void setFractionalMetrics(boolean fractionalMetrics) {
		this.fractionalMetrics = fractionalMetrics;
	}

	public float getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}

}