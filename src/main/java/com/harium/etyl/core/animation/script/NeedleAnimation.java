package com.harium.etyl.core.animation.script;

import com.harium.etyl.layer.AnimatedLayer;

public class NeedleAnimation extends AnimationScript {
		
	protected AnimatedLayer target;
	
	private int startNeedleX = 0;
	
	private int startNeedleY = 0;
	
	private int endNeedleX = 0;
	
	private int endNeedleY = 0;
	
	public NeedleAnimation(long time) {
		super(time);
	}
		
	public void setTarget(AnimatedLayer target) {
		
		this.target = target;

		startNeedleX = target.getNeedleX();
		
		startNeedleY = target.getNeedleY();
				
		this.restart();
	}
	
	public void setNeedle(int needleX, int needleY) {
		
		endNeedleX = needleX;
		
		endNeedleY = needleY;
		
	}
	
	protected void update(double value) {
				
		if(value == 0) {
			
			target.setNeedleX(startNeedleX);
			target.setNeedleY(startNeedleY);
			
		} else {
			
			target.setNeedleX(endNeedleX);
			target.setNeedleY(endNeedleY);
		}
	}

	@Override
	public void calculate(double factor) {
		
		if(factor<=0.5) {
			update(0);
		} else {
			update(1);
		}
	}

}
