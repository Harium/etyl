package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.interpolation.Interpolator;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.OnAnimationFinishListener;

import java.util.ArrayList;
import java.util.List;


public abstract class AnimationScript {

    protected static final int UNDEFINED = -1;

    protected long startedAt = 0;
    protected long duration = 0;
    protected long delay = 0;
    protected int loop = 0;

    private boolean started = false;
    private boolean stopped = false;
    private boolean referenced = false;

    protected long endDelay = 0;

    protected List<AnimationScript> next;

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
        started = true;
        stopped = false;
        this.startedAt = now;
    }

    public void stop() {
        started = true;
        stopped = true;
    }

    /**
     * Method to update the animation
     * @param now - the time in milliseconds
     */
    public void tick(long now) {
        if (!started) {
            start(now);
        }

        if (started && !stopped) {
            long elapsedTime = now - startedAt - delay;

            if (elapsedTime >= duration) {
                if (elapsedTime >= duration + endDelay) {
                    stopped = true;
                }
            } else if (now - startedAt >= delay) {
                this.animate(now);
            }
        }
    }

    /**
     * Method to effectively animate
     * @param now - the time in milliseconds
     * @return animation is ended
     */
    public boolean animate(long now) {
        float factor = factor(now);

        if (factor < 1) {
            calculate(factor);
        } else if (loop == Animation.REPEAT_FOREVER || loop > 0) {
            calculate(factor - 1);
            return true;
        } else {
            calculate(1);
            return true;
        }

        return false;
    }

    public float factor(long now) {
        long timeElapsed = now - startedAt - delay;
        return (float) timeElapsed / duration;
    }

    public abstract void calculate(double factor);

    public AnimationScript startAt(long delay) {
        this.delay = delay;
        return this;
    }

    public AnimationScript during(long duration) {
        this.duration = duration;
        return this;
    }

    public AnimationScript interpolate(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public AnimationScript twice() {
        this.loop = 2;
        return this;
    }

    public AnimationScript loop(int loop) {
        this.loop = loop;
        return this;
    }

    public boolean isStopped() {
        return stopped;
    }

    public int getRepeat() {
        return loop;
    }

    public void setRepeat(int repeat) {
        this.loop = repeat;
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

    public void setDuration(long duration) {
        this.duration = duration;
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

    public void onFinish(long now) {
        if (listener == null)
            return;

        listener.onAnimationFinish(now);
    }

    public void onStart(long now) {
    }
}
