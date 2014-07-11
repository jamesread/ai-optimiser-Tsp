package eatest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ea.Algorithm;
import ea.City;
import ea.Route;

public class TestOx1Crossover {
	private static final Logger logger = LoggerFactory.getLogger(TestOx1Crossover.class);

	private final Route route1 = new Route(new City("Bath"), new City("Birmingham"), new City("Bradford"), new City("Brighton"), new City("Bristol"));
	private final Route route2 = new Route(new City("Cambridge"), new City("Canterbury"), new City("Carlisle"), new City("Chelmsford"), new City("Chester"));

	@Test
	@Ignore
	public void test() {
		Route offspring = Algorithm.crossoverOx1(this.route1, this.route2, 1, 3);

		Assert.assertEquals(this.route1.getCityCount(), offspring.getCityCount());

		Assert.assertEquals("Cambridge", offspring.get(0).name);
		Assert.assertEquals("Birmingham", offspring.get(1).name);
		Assert.assertEquals("Bradford", offspring.get(2).name);
		Assert.assertEquals("Chelmsford", offspring.get(3).name);
		Assert.assertEquals("Chester", offspring.get(4).name);

		logger.debug("" + offspring);

	}

	@Test
	@Ignore
	public void test2() {
		Route p1 = new Route("Portsmouth", "Exeter", "London", "Farnborough", "Bournemouth", "Liverpool");
		Route p2 = new Route("Bournemouth", "Portsmouth", "Exeter", "Farnborough", "Liverpool", "London");

		for (int i = 1; i < 6; i++) {
			Route of = Algorithm.crossoverOx1(p1, p2, i, i + 1);

			System.out.println("of " + of.toString());
			Assert.assertEquals(p1.getCityCount(), of.getCityCount());
		}

	}
}
