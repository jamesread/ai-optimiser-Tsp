package eatest;

import org.junit.Assert;
import org.junit.Test;

import ea.Route;

public class TestMutation {
	@Test
	public void test() {
		Route r = new Route("a", "b", "c");

		r.swapSteps(0, 1);

		Assert.assertEquals("b", r.getStep(0).name);
		Assert.assertEquals("a", r.getStep(1).name);
		Assert.assertEquals("c", r.getStep(2).name);
	}
}
