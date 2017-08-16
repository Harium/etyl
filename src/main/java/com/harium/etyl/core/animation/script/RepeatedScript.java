package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;

public abstract class RepeatedScript extends AnimationScript {

	protected Layer target;

	// It repeats a loop inside the animation
	protected int repeatTimes = 1;
		
	public RepeatedScript(Layer target, long time) {
		super(time);
		
		this.target = target;
	}

	public void repeat(int times) {
		repeatTimes = times;
	}
	
}
