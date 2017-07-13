package com.harium.etyl.animation.transformation;

import java.awt.geom.AffineTransform;

public class Transform {

	private double x = 0;
	private double y = 0;
	
	private double angle = 0;
		
	private double scaleX = 1;
	private double scaleY = 1;
	
	public Transform() {
		super();
	}
	
	public Transform(double angle) {
		super();
		
		this.angle = angle;
	}
	
	public Transform(Transform transform) {
		super();
		
		copy(transform);
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void offsetAngle(double offset) {
		this.angle += offset;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void offsetX(double x) {
		this.x += x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void offsetY(double y) {
		this.y += y;
	}

	public double getScaleX() {
		return scaleX;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	public boolean isFlippedX() {
		return scaleX < 0;
	}
	
	public boolean isFlippedY() {
		return scaleY < 0;
	}
	
	public void copy(Transform copy) {
				
		this.x = copy.x;
		this.y = copy.y;
		
		this.angle = copy.angle;
		
		this.scaleX = copy.scaleX;
		this.scaleY = copy.scaleY;
	}
	
	public AffineTransform getIncompleteTransform(double pivotX, double pivotY) {
		AffineTransform transform = AffineTransform.getScaleInstance(scaleX, scaleY);
		transform.concatenate(AffineTransform.getRotateInstance(angle, pivotX, pivotY));
		
		return transform;
	}
	
	public AffineTransform getTransformorm(double pivotX, double pivotY) {
		AffineTransform transform = AffineTransform.getTranslateInstance(x, y);
		
		transform.concatenate(AffineTransform.getRotateInstance(angle, pivotX, pivotY));
		transform.concatenate(AffineTransform.getScaleInstance(scaleX, scaleY));
		
		return transform;
	}
		
}
