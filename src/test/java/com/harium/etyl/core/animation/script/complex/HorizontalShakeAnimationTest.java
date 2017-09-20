package com.harium.etyl.core.animation.script.complex;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
import org.junit.Assert;
import org.junit.Test;

public class HorizontalShakeAnimationTest {

    @Test
    public void testConstructor() {
        HorizontalShakeAnimation animation = Animation.animate(null).shakeHorizontal();
        Assert.assertNotNull(animation);
    }

    @Test
    public void testConstructorWithDuration() {
        int duration = 600;
        HorizontalShakeAnimation animation = Animation.animate(null).shakeHorizontal(duration);
        Assert.assertEquals(duration, animation.getDuration());
    }

    @Test
    public void testInterval() {
        int duration = 100;
        Layer layer = new Layer(0, 0, 10, 10);

        HorizontalShakeAnimation animation = Animation.animate(layer).shakeHorizontal(duration);
        animation.loop(1);
        animation.start();
        animation.strength(20);
        Animation.getInstance().update(0);
        Animation.getInstance().update(100);
        Animation.getInstance().update(200);

        Assert.assertTrue(animation.isStopped());
        Assert.assertEquals(0, layer.getY());
    }

}
