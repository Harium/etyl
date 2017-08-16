package com.harium.etyl.core.animation.script;

import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.script.LayerAnimation;
import com.harium.etyl.core.animation.script.RotateAnimation;
import com.harium.etyl.util.PathHelper;
import org.junit.Assert;
import org.junit.Test;

public class LayerAnimationTest {

	@Test
	public void testSetDuration() {
		LayerAnimation animation = Animation.animate(null).during(600);
		Assert.assertEquals(600, animation.getDuration());
	}

	@Test
	public void testSetLoop() {
		LayerAnimation animation = Animation.animate(null).loop(3);
		Assert.assertEquals(3, animation.getLoop());
	}

	@Test
	public void testSetLoopTwice() {
		LayerAnimation animation = Animation.animate(null).twice();
		Assert.assertEquals(2, animation.getLoop());
	}

}
