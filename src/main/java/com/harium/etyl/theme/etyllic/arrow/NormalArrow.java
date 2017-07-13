package com.harium.etyl.theme.etyllic.arrow;

import java.awt.Color;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.theme.Theme;
import com.harium.etyl.ui.theme.ThemeManager;
import com.harium.etyl.ui.theme.cursor.arrow.MouseArrow;
import com.harium.etyl.ui.theme.cursor.arrow.PolygonalArrow;


public class NormalArrow extends PolygonalArrow implements MouseArrow {
		
	protected int cursorX = 0;
	protected int cursorY = 0;

	protected int size = 24;

	private Color color = null;
	private Color borderColor = null;

	public NormalArrow(int size) {

		this.size = size;

		defPoints();
	}

	protected void defPoints() {
		addPoint(0, 0);
		addPoint(0, (int)(size*0.8));
		addPoint((int)(size*0.18),(int)(size*0.62));

		addPoint((int)(size*0.28),(int)(size*0.88));
		addPoint((int)(size*0.42),(int)(size*0.82));

		addPoint((int)(size*0.30),(int)(size*0.57));
		addPoint((int)(size*0.49),(int)(size*0.57));
	}
	
	public void setLocation(int mx, int my) {
		int difx = mx;
		int dify = my;

		polygon.translate(difx-cursorX, dify-cursorY);

		cursorX = difx;
		cursorY = dify;
	}

	public void draw(Graphics g) {

		Theme theme = ThemeManager.getInstance().getTheme();

		if(color == null) {
			g.setColor(theme.getMouseArrowColor());
		} else {
			g.setColor(color);
		}
		
		g.fillPolygon(polygon);

		if(borderColor == null) {
			g.setColor(theme.getMouseArrowBorderColor());	
		} else {
			g.setColor(borderColor);
		}
		
		g.drawPolygon(polygon);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
