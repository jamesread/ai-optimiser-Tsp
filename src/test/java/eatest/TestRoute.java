package eatest;

import org.junit.Assert;
import org.junit.Test;

import ea.City;
import ea.Route;

public class TestRoute {
	@Test
	public void testSwap() {
		City a = new City("a");
		City b = new City("b");

		Route route = new Route(a, b);

		Assert.assertEquals(route.getStep(0).name, "a");
		Assert.assertEquals(route.getStep(1).name, "b");
		Assert.assertEquals(route.getDistance(), 0);

		route.swapSteps(0, 1);

		Assert.assertEquals(route.getStep(0).name, "b");
		Assert.assertEquals(route.getStep(1).name, "a");
	}
}
