package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.interpolation.Interpolator;
import com.harium.etyl.core.animation.Animator;

public class CustomAnimation extends AnimationScript {

    private Animator animator;

    public CustomAnimation(Animator animator) {
        this.animator = animator;
    }

    @Override
    public void calculate(double factor) {
        animator.animate(factor);
    }

    @Override
    public CustomAnimation startAt(long delay) {
        super.startAt(delay);
        return this;
    }

    @Override
    public CustomAnimation during(long duration) {
        super.during(duration);
        return this;
    }

    @Override
    public CustomAnimation interpolate(Interpolator interpolator) {
        super.interpolate(interpolator);
        return this;
    }

    @Override
    public CustomAnimation twice() {
        super.twice();
        return this;
    }

    @Override
    public CustomAnimation loop(int loop) {
        super.loop(loop);
        return this;
    }

}
