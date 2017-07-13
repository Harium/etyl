package com.harium.etyl.core.effect;

import com.harium.etyl.core.animation.script.SingleIntervalAnimation;
import com.harium.etyl.commons.layer.Layer;

/**
 * 
 * @author yuripourre
 *
 */

public abstract class GlobalEffect extends Layer {

	protected SingleIntervalAnimation script;
	
	public GlobalEffect(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	public SingleIntervalAnimation getScript() {
		return script;
	}

}