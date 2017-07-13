package com.harium.etyl.core.animation;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.script.LayerAnimation;

public class Animation {

	public static LayerAnimation animate(Layer layer) {
		LayerAnimation script = new LayerAnimation(layer);
		return script;
	}
		
}
