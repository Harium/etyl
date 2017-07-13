package com.harium.etyl.animation.timeline;

import java.util.HashMap;
import java.util.Map;

import com.harium.etyl.animation.transformation.Transform;

public class KeyFrame<T> {
		
	private int time = 0;
	
	private Map<T, Transform> transformations = new HashMap<T, Transform>();
		
	public KeyFrame() {
		super();
	}
	
	public KeyFrame(int time) {
		super();
		
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public void addRotation(T bone, int angle) {
		transformations.put(bone, new Transform(angle));
	}
	
	public void addTransform(T bone, Transform transform) {
		transformations.put(bone, new Transform(transform));
	}

	public Transform getTransformation(T bone) {
		return transformations.get(bone);
	}
	
	public Map<T, Transform> getTransformations() {
		return transformations;
	}
	
}
