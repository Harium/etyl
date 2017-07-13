package com.harium.etyl.core.animation.script.complex;

import com.harium.etyl.commons.layer.Layer;

public class VerticalShakeScript extends ShakeScript {
	
	private int initialY = 0;
		
	public VerticalShakeScript(Layer target, long time) {
		super(target, time);
		
		initialY = target.getY();
	}
	
	@Override
	public void calculate(double factor) {
		
		double division = 1/(double)repeatTimes;
				
		int interval = (int)(factor/division);
				
		double part = interval+1 - (factor/division);
		
		double slice = part;
		
		if(interval%2 == 0) {
			
			slice = 1-slice;
		}
		
		double startValue = initialY-strength;
		
		double endValue = initialY+strength;
		
		double value = startValue+(endValue-startValue)*slice;		
		
		target.setY((int)value);		
	}
	
}
