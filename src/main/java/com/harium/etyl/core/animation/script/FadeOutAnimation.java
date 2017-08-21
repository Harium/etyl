package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class FadeOutAnimation extends OpacityAnimation {

	public FadeOutAnimation(long time){
		super(time);				
	}
	
	public FadeOutAnimation(long delay, long time){
		super(delay, time);
	}
	
	public FadeOutAnimation(Layer target, long time){
		super(target, time);
	}
	
	public FadeOutAnimation(Layer target, long delay, long time){
		super(target, delay, time);
	}
	
	public FadeOutAnimation(Layer target) {
		super(target);
	}

	@Override
	public void setTarget(Layer target) {
		super.setTarget(target);
	}
	
	@Override
	protected void update(double value){
		target.setOpacity((int)value);
	}

	@Override
	public void onStart() {
		super.onStart();
		startValue = Layer.MAX_OPACITY;
		endValue = 0;
	}

}
