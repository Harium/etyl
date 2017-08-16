package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public abstract class SingleIntervalAnimation extends LayerAnimation {
		
	protected double startValue = 0;
	protected double endValue = 0;
	
	public SingleIntervalAnimation(Layer target) {
		super(target);
	}
	
	public SingleIntervalAnimation(long time) {
		super(time);
	}
	
	public SingleIntervalAnimation(long delay, long time) {
		super(delay, time);
	}

	public SingleIntervalAnimation(Layer target, long time) {
		super(target, time);		
	}
	
	public SingleIntervalAnimation(Layer target, long delay, long time) {
		super(delay, time);
		
		setTarget(target);
	}
	
	@Override
	public void calculate(double factor) {
		double value = interpolator.interpolate(startValue, endValue, factor);
		update(value);
	}
	
	/**
	 * This method override is needed
	 */
	@Override
	public SingleIntervalAnimation during(long time) {
		super.during(time);
		return this;
	}
	
	public SingleIntervalAnimation from(double value) {
		setStartValue(value);
		return this;
	}
	
	public SingleIntervalAnimation to(double value) {
		setEndValue(value);
		return this;
	}
	
	protected abstract void update(double value);
	
	public void setInterval(double startValue, double endValue) {
		this.startValue = startValue;
		this.endValue = endValue;
		
		//Update for the first value
		if (target != null) {
			calculate(0);
		}
	}

	public double getStartValue() {
		return startValue;
	}

	public void setStartValue(double startValue) {
		this.startValue = startValue;
	}

	public double getEndValue() {
		return endValue;
	}

	public void setEndValue(double endValue) {
		this.endValue = endValue;
	}
}
