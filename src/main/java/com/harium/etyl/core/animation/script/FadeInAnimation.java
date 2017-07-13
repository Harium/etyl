package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.layer.Layer;


public class FadeInAnimation extends OpacityAnimation {
	
	{
		startValue = 0;
		endValue = 255;
	}
	
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
	public void onStart() {
		target.setOpacity((int) startValue);
	}

}
