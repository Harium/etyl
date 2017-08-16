package com.harium.etyl.core.animation.script;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimationScriptTest {

    AnimationScript script;

    @Before
    public void setUp() {
        script = new AnimationScript() {
            @Override
            public void calculate(double factor) {

            }
        };
    }

    @Test
    public void testFactor() {
        script.setDuration(1000);
        float factor = script.factor(1100);
        Assert.assertEquals(1.1f, factor, 0.0001);
    }

    @Test
    public void testFactorWithStart() {
        script.start(200);
        script.setDuration(1000);
        float factor = script.factor(1100);
        Assert.assertEquals(0.9f, factor, 0.0001);
    }

}
