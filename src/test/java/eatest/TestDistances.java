package eatest;

import org.junit.Assert;
import org.junit.Test;

import ea.City;

public class TestDistances {
	@Test
	public void testTwoCities() {
		City a = new City("a", 0, 0);
		City b = new City("b", 1, 1);

		Assert.assertEquals(1.41, a.distanceTo(b), 0.1);
	}
}
