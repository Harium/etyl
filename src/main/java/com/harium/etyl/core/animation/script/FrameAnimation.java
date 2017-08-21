package com.harium.etyl.core.animation.script;

import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.layer.AnimatedLayer;

public class FrameAnimation extends SingleIntervalAnimation {
		
	protected AnimatedLayer target;
	
	public FrameAnimation(long time) {
		super(time);
	}
	
	public FrameAnimation(AnimatedLayer target) {
		super(target.getSpeed()*target.getFrames());
		
		setTarget(target);
		this.loop = Animation.REPEAT_FOREVER;
	}
	
	public void setTarget(AnimatedLayer target) {
		
		this.target = target;
		
		this.startValue = 0;
		this.endValue = target.getFrames();
		
		this.restart();
	}	
		
	@Override
	protected void update(double value) {
		target.animateWithFrame((int)(value%target.getFrames()));
	}

}
