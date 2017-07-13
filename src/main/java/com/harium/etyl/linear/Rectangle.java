package com.harium.etyl.linear;

/**
 * 
 * @author yuripourre
 *
 */

public class Rectangle extends Point2D {

	protected double w;
	protected double h;

	public Rectangle(double x, double y) {
		super(x,y);
		w = 0;
		h = 0;
	}

	public Rectangle(double x, double y, double w, double h) {
		super(x,y);
		this.w = w;
		this.h = h;
	}	

	public boolean colideRetangulo(Rectangle rect) {

		if(rect.x + rect.w < x)	return false;
		if(rect.x > x + w)		return false;

		if(rect.y + rect.h < y)	return false;
		if(rect.y > h + h)		return false;

		return true;	
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}
	
}
