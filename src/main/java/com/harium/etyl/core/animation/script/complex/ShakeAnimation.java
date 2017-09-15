package com.harium.etyl.core.animation.script.complex;

import com.harium.etyl.core.animation.script.RepeatedAnimation;
import com.harium.etyl.commons.layer.Layer;

public abstract class ShakeAnimation extends RepeatedAnimation {
	protected int startValue = UNDEFINED;

	protected int strength = 10;

	public ShakeAnimation(Layer target) {
		super(target);
	}

	public ShakeAnimation(Layer target, long time) {
		super(target, time);		
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public ShakeAnimation strength(int strength) {
		this.strength = strength;
		return this;
	}
}
