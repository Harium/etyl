package com.harium.etyl.core.context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.harium.etyl.commons.Drawable;
import com.harium.etyl.commons.Updatable;
import com.harium.etyl.commons.graphics.Graphics;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.script.AnimationScript;
import com.harium.etyl.commons.particle.Emitter;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.ui.UIComponent;
import com.harium.etyl.commons.layer.Layer;

public class Scene implements UIComponent {
	
	protected List<Updatable> updatables = new ArrayList<Updatable>();
	protected List<Drawable> drawables = new ArrayList<Drawable>();
	
	protected Map<Layer, List<Layer>> graph = new LinkedHashMap<Layer, List<Layer>>();
	
	public Scene() {
		super();
	}
	
	public void addAnimation(AnimationScript animation) {
		Animation.getInstance().add(animation);
	}
	
	public void addEmitter(Emitter emitter) {
		updatables.add(emitter);
		drawables.add(emitter);
	}
	
	public void add(Layer layer) {
		graph.put(layer, new ArrayList<Layer>());
	}
	
	public void addChild(Layer layer, Layer child) {
				
		if(!graph.containsKey(layer)) {
			add(layer);
		}
		
		List<Layer> children = graph.get(layer);
		children.add(child);
	}
	
	public Map<Layer, List<Layer>> getGraph() {
		return graph;
	}

	@Override
	public void update(long now) {
		for(Updatable updatable: updatables) {
			updatable.update(now);
		}
	}
	
	/**
	 * Draw Scene method with recurrency
	 */
	public void draw(Graphics g) {
		
		for(Layer layer: graph.keySet()) {
			drawLayer(g, layer);
		}

		for(Drawable drawable: drawables) {
			drawable.draw(g);
		}
	}

	private void drawLayer(Graphics g, Layer layer) {
		
		layer.draw(g);
		
		List<Layer> children = graph.get(layer);
		
		for(Layer child: children) {
			drawLayer(g, child);
		}
	}

	@Override
	public void updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub

	}

}
