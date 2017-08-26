package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class FadeInAnimation extends OpacityAnimation {

	public FadeInAnimation(Layer target) {
		super(target);
	}
	
	public FadeInAnimation(long time){
		super(time);
	}
	
	public FadeInAnimation(long delay, long time){
		super(delay, time);
	}
	
	public FadeInAnimation(Layer target, long time){
		super(target, time);
	}
	
	public FadeInAnimation(Layer target, long delay, long time){
		super(target, delay, time);
	}
	
	@Override
	public void onStart(long now) {
		super.onStart(now);
		startValue = 0;
		endValue = Layer.MAX_OPACITY;
	}

}
