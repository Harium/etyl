package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class VerticalMovementScript extends SingleIntervalAnimation {
		
	public VerticalMovementScript(long time) {
		super(time);
	}
	
	public VerticalMovementScript(Layer target) {
		super(target);
	}
	
	public VerticalMovementScript(Layer target, long time) {
		super(target, time);
	}
		
	@Override
	protected void update(double value) {
		target.setY((int)value);
	}

}
