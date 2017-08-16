package com.harium.etyl.core.animation.script;

import com.harium.etyl.core.animation.Animation;
import javafx.scene.transform.Rotate;
import org.junit.Assert;
import org.junit.Test;

public class RotateAnimationTest {

	@Test
	public void testConstructor() {
		RotateAnimation animation = Animation.animate(null).rotate();
		Assert.assertNotNull(animation);
	}

	@Test
	public void testConstructorWithDuration() {
		RotateAnimation animation = Animation.animate(null).rotate(600);
		Assert.assertEquals(600, animation.getDuration());
	}

	@Test
	public void testInterval() {
		RotateAnimation animation = Animation.animate(null).rotate(600);
		animation.from(10).to(20);
		Assert.assertEquals(10, animation.getStartValue(), 0);
		Assert.assertEquals(20, animation.getEndValue(), 0);
	}

}
