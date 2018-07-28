package com.harium.etyl.animation.script;

import java.awt.Color;

import com.harium.etyl.core.animation.script.AnimationScript;


public class ColorAnimation extends AnimationScript {
	
	private Color initialColor;
	
	private Color endColor;
	
	private Color currentColor;
	
	public ColorAnimation(long time) {
		super(time);
	}
	
	public ColorAnimation(long delay, long time) {
		super(delay, time);
	}
	
	@Override
	public void calculate(float factor) {
		
		double redValue = initialColor.getRed()+(endColor.getRed()-initialColor.getRed())*factor;
		
		double greenValue = initialColor.getGreen()+(endColor.getGreen()-initialColor.getGreen())*factor;
		
		double blueValue = initialColor.getBlue()+(endColor.getBlue()-initialColor.getBlue())*factor;
		
		currentColor = new Color((int)redValue, (int)greenValue, (int)blueValue);
	}

	public Color getInitialColor() {
		return initialColor;
	}

	public void setInitialColor(Color initialColor) {
		this.initialColor = initialColor;
	}

	public Color getEndColor() {
		return endColor;
	}

	public void setEndColor(Color endColor) {
		this.endColor = endColor;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

}
