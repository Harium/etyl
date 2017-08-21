package com.harium.etyl.core.animation.script;

import com.harium.etyl.commons.interpolation.Interpolator;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
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

    public LayerAnimation startAt(long delayValue) {
        this.delay = delayValue;
        return this;
    }

    public LayerAnimation during(long time) {
        this.duration = time;
        return this;
    }

    public LayerAnimation interpolate(Interpolator interpolator) {
        this.interpolator = interpolator;
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

    public MovementScript move(long time) {
        MovementScript script = new MovementScript(target, time);
        concatenate(script);

        return script;
    }

    public MovementScript move() {
        MovementScript script = new MovementScript(target);
        concatenate(script);

        return script;
    }

    public HorizontalMovementScript moveX(int duration) {
        HorizontalMovementScript script = new HorizontalMovementScript(target, duration);
        concatenate(script);

        return script;
    }

    public VerticalMovementScript moveY(int duration) {
        VerticalMovementScript script = new VerticalMovementScript(target, duration);
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

    public LayerAnimation then(LayerAnimation script) {
        addNext(script);
        return this;
    }

    public LayerAnimation start() {
        root.startChildren();
        return this;
    }

    private void startChildren() {
        onStart();

        if (next != null) {
            for (AnimationScript s : next) {
                Animation.getInstance().add(s);
            }
        }
    }

}
