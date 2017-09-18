package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.interpolation.Interpolator;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.script.complex.HorizontalShakeAnimation;
import com.harium.etyl.core.animation.script.complex.VerticalShakeAnimation;
import com.harium.etyl.layer.ImageLayer;

public class LayerAnimation extends AnimationScript {

    protected Layer target;
    protected LayerAnimation root = this;

    public LayerAnimation(long time) {
        super(time);
    }

    public LayerAnimation(Layer target) {
        super();
        setTarget(target);
    }

    public LayerAnimation(Layer target, long time) {
        this(target);
        this.duration = time;
    }

    public LayerAnimation(long delay, long time) {
        super(delay, time);
    }

    public Layer getLayer() {
        return target;
    }

    public void setLayer(ImageLayer layer) {
        this.target = layer;
    }


    public Layer getTarget() {
        return target;
    }

    public void setTarget(Layer target) {
        this.target = target;
    }

    @Override
    public void calculate(double factor) {
        // TODO Auto-generated method stub
    }

    @Override
    public LayerAnimation startAt(long delay) {
        super.startAt(delay);
        return this;
    }

    @Override
    public LayerAnimation during(long duration) {
        super.during(duration);
        return this;
    }

    @Override
    public LayerAnimation interpolate(Interpolator interpolator) {
        super.interpolate(interpolator);
        return this;
    }

    @Override
    public LayerAnimation twice() {
        super.twice();
        return this;
    }

    @Override
    public LayerAnimation loop(int loop) {
        super.loop(loop);
        return this;
    }

    public MovementAnimation move(long time) {
        MovementAnimation script = new MovementAnimation(target, time);
        concatenate(script);

        return script;
    }

    public MovementAnimation move() {
        MovementAnimation script = new MovementAnimation(target);
        concatenate(script);

        return script;
    }

    public HorizontalAnimation moveX(int duration) {
        HorizontalAnimation script = new HorizontalAnimation(target, duration);
        concatenate(script);

        return script;
    }

    public VerticalAnimation moveY(int duration) {
        VerticalAnimation script = new VerticalAnimation(target, duration);
        concatenate(script);

        return script;
    }

    public FadeInAnimation fadeIn() {
        FadeInAnimation script = new FadeInAnimation(target);
        concatenate(script);

        return script;
    }

    public FadeInAnimation fadeIn(long duration) {
        FadeInAnimation script = new FadeInAnimation(target);
        script.during(duration);
        concatenate(script);

        return script;
    }

    public FadeOutAnimation fadeOut() {
        FadeOutAnimation script = new FadeOutAnimation(target);
        concatenate(script);

        return script;
    }

    public FadeOutAnimation fadeOut(long duration) {
        FadeOutAnimation script = new FadeOutAnimation(target);
        script.during(duration);
        concatenate(script);

        return script;
    }

    public ScaleUniformAnimation scale(int duration) {
        ScaleUniformAnimation script = new ScaleUniformAnimation(target, duration);
        concatenate(script);

        return script;
    }

    public ScaleUniformAnimation scale() {
        ScaleUniformAnimation script = new ScaleUniformAnimation(target);
        concatenate(script);

        return script;
    }

    public RotateAnimation rotate(int duration) {
        RotateAnimation script = new RotateAnimation(target, duration);
        concatenate(script);

        return script;
    }

    public RotateAnimation rotate() {
        RotateAnimation script = new RotateAnimation(target);
        concatenate(script);

        return script;
    }

    public OrbitAnimation orbit() {
        OrbitAnimation script = new OrbitAnimation(target);
        concatenate(script);

        return script;
    }

    public LayerAnimation concatenate(LayerAnimation script) {
        addNext(script);
        setupRoot(script);
        return script;
    }

    public VerticalShakeAnimation shakeVertical(long duration) {
        VerticalShakeAnimation script = new VerticalShakeAnimation(target, duration);
        concatenate(script);

        return script;
    }

    public HorizontalShakeAnimation shakeHorizontal(long duration) {
        HorizontalShakeAnimation script = new HorizontalShakeAnimation(target, duration);
        concatenate(script);

        return script;
    }

    private void setupRoot(LayerAnimation script) {
        script.root = getRoot();
    }

    private LayerAnimation getRoot() {
        if (root != null) {
            return this;
        } else {
            return root;
        }
    }

    public LayerAnimation and() {
        return root;
    }

    public LayerAnimation and(LayerAnimation script) {
        root.addNext(script);
        return this;
    }

    public LayerAnimation then() {
        LayerAnimation animation = new DummyAnimation(getLayer());
        animation.root = this;
        animation.delay = duration;
        addNext(animation);
        return animation;
    }

    public LayerAnimation then(LayerAnimation script) {
        addNext(script);
        return this;
    }

    public LayerAnimation start() {
        root.startChildren();
        return this;
    }

    protected void startChildren() {
        onStart(0);

        if (next != null) {
            for (AnimationScript s : next) {
                Animation.getInstance().add(s);
            }
        }
    }

}
