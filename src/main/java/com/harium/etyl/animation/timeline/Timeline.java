package com.harium.etyl.animation.timeline;

import java.util.ArrayList;
import java.util.List;

public abstract class Timeline<T, F> {

	protected int cursor = 0;

	protected List<KeyFrame<F>> frames = new ArrayList<KeyFrame<F>>();

	public Timeline() {
		super();
	}

	public void nextFrame() {
		if(cursor<frames.size()-1) {
			cursor++;
			reloadFrame(currentFrame());
		}
	}

	public void nextFrameLoop() {
		cursor++;
		cursor%=frames.size();
		reloadFrame(currentFrame());
	}

	public void previousFrame() {

		if(cursor > 0) {
			cursor--;

			reloadFrame(currentFrame());
		}
	}

	public KeyFrame<F> currentFrame() {
		return frames.get(cursor);
	}

	public int frameCount() {
		return frames.size();
	}

	public int getCursor() {
		return cursor;
	}

	public abstract void reloadFrame(KeyFrame<F> frame);

}
