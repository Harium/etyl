package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class StretchVerticalAnimation extends SingleIntervalAnimation {
		
	protected int originalY;
	
	public StretchVerticalAnimation(long time) {
		super(time);
	}
	
	public StretchVerticalAnimation(long delay, long time) {
		super(delay, time);
	}
	
	public StretchVerticalAnimation(Layer target, long time) {
		super(target, time);
	}
	
	@Override
	public void setTarget(Layer target) {
		super.setTarget(target);

		originalY = (int)(target.getY());
	}
		
	@Override
	public void update(double value) {
		target.setScaleY(value);
		target.setY((int)(originalY-(target.getH()/2)*value));
	}	

}
