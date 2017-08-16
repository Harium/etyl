package com.harium.etyl.core.animation.script;

import java.util.ArrayList;
import java.util.List;

import com.harium.etyl.core.animation.OnAnimationFinishListener;
import com.harium.etyl.commons.interpolation.Interpolator;


public abstract class AnimationScript {

	protected long startedAt = 0;
	protected long duration = 0;
	protected long delay = 0;
	protected int loop = 0;

	private boolean started = false;
	private boolean stopped = false;
	private boolean referenced = false;
	
	protected long endDelay = 0;

	protected List<AnimationScript> next;
	
	public static final int REPEAT_FOREVER = -1;
	
	private OnAnimationFinishListener listener;
	
	protected Interpolator interpolator = Interpolator.LINEAR;

	public AnimationScript(long time) {
		super();

		this.duration = time;
	}

	public AnimationScript(long delay, long time) {
		super();

		this.delay = delay;
		this.duration = time;
	}

	public AnimationScript() {
		super();
	}

	public void restart() {
		started = false;
		stopped = false;
	}

	public void start(long now) {
		this.startedAt = now;
	}

	public void preAnimate(long now) {

		if(!started) {
			started = true;
			stopped = false;
			startedAt = now;
		}

		if(started && !stopped) {

			long elapsedTime = now-startedAt-delay;
			
			if(elapsedTime >= duration) {
				
				if(elapsedTime >= duration+endDelay) {
					stopped = true;
				}
				
			} else {
				if (now-startedAt >= delay) {
					this.animate(now);
				}
			}

		}

	}

	public boolean animate(long now) {
		long timeElapsed = now-startedAt-delay;
		float factor = (float)timeElapsed/duration;

		if (factor < 1) {
			calculate(factor);
		} else {
			calculate(1);
			return true;
		}
		
		return false;
	}
	
	public abstract void calculate(double factor);
	
	public boolean isStopped() {
		return stopped;
	}

	public int getRepeat() {
		return loop;
	}

	public void setRepeat(int repeat) {
		this.loop = repeat;
	}
	
	public AnimationScript twice() {
		this.loop = 2;
		return this;
	}
	
	public AnimationScript loop(int loop) {
		this.loop = loop;
		return this;
	}
	
	public List<AnimationScript> getNext() {
		return next;
	}

	public void addNext(AnimationScript next) {
		if (this.next == null) {
			this.next = new ArrayList<AnimationScript>();
		}
		this.next.add(next);
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	public void setEndDelay(long endDelay) {
		this.endDelay = endDelay;
	}

	public long getDuration() {
		return duration;
	}

	public int getLoop() {
		return loop;
	}

	public OnAnimationFinishListener getListener() {
		return listener;
	}

	public void setListener(OnAnimationFinishListener listener) {
		this.listener = listener;
	}

	public Interpolator getInterpolator() {
		return interpolator;
	}

	public void setInterpolator(Interpolator interpolator) {
		this.interpolator = interpolator;
	}
	
	public boolean isReferenced() {
		return referenced;
	}

	public void setReferenced(boolean referenced) {
		this.referenced = referenced;
	}

	public void finish(long now) {
		if(listener==null)
			return;
		
		listener.onAnimationFinish(now);
	}
}
