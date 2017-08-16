package com.harium.etyl.core.effect;

import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.script.FrameAnimation;
import com.harium.etyl.layer.AnimatedLayer;

/**
 * 
 * @author yuripourre
 *
 */

public class Effect extends AnimatedLayer {

	private boolean active = false;
	
	private FrameAnimation script = new FrameAnimation(this);
	
	public Effect(int x, int y, int xTile, int yTile) {
		super(x,y,xTile,yTile);
		
		setVisible(false);
	}
	
	public Effect(int x, int y, int xTile, int yTile, String path) {
		super(x, y, xTile, yTile, path);
		
		setVisible(false);
	}
	
	@Override
	protected void notifyAnimationFinishListener(long now) {
		super.notifyAnimationFinishListener(now);
		
		setVisible(false);
		active = false;
	}
	
	@Override
	public void animate(long now) {
		if(!active)
			return;
		
		super.animate(now);
	}
	
	public void startEffect() {
		script.setTarget(this);
		active = true;
		
		setVisible(true);
		restartAnimation();

		Animation.getInstance().add(script);
	}
	
}