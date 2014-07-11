package eatest;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import ea.City;
import ea.Route;
import ea.WrappingReader;

public class TestWrappingReader {

	@Test
	public void testWrap() {
		Route r = new Route(false, 0);

		r.add(new City("a"));
		r.add(new City("b"));
		r.add(new City("c"));
		r.add(new City("d"));
		r.add(new City("e"));
		r.add(new City("f"));
		r.add(new City("g"));

		WrappingReader<Route, City> reader = new WrappingReader<Route, City>(r);
		Iterator<City> red = reader.read(3, 2).iterator();

		Assert.assertEquals("d", red.next().name);
		Assert.assertEquals("e", red.next().name);
		Assert.assertEquals("f", red.next().name);
		Assert.assertEquals("g", red.next().name);
		Assert.assertEquals("a", red.next().name);
		Assert.assertEquals("b", red.next().name);
	}
}
