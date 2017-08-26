package com.harium.etyl.core.animation.script.complex;

import com.harium.etyl.core.animation.script.RepeatedScript;
import com.harium.etyl.commons.layer.Layer;

public abstract class ShakeScript extends RepeatedScript {
	protected static final int UNDEFINED = -1;
	protected int startValue = UNDEFINED;

	protected int strength = 10;

	public ShakeScript(Layer target) {
		super(target);
	}

	public ShakeScript(Layer target, long time) {
		super(target, time);		
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public ShakeScript strength(int strength) {
		this.strength = strength;
		return this;
	}
}
