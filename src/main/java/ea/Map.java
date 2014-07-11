package ea;

import java.util.Vector;

public class Map {
	private final Vector<City> cities = new Vector<City>();

	public City addCity(City c) {
		this.cities.add(c);

		return c;
	}

	public Vector<City> getCities() {
		return this.cities;
	}

	public City randomCity() {
		return Util.randomChoice(this.cities);
	}
}
