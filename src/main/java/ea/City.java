package ea;

import java.awt.Point;

public class City {
	public final String name;
	public final Point position;

	public City(String string) {
		this.name = string;
		this.position = new Point(0, 0);
	}

	public City(String name, int x, int y) {
		this.name = name;
		this.position = new Point(x, y);
	}

	public double distanceTo(City next) {
		return this.position.distance(next.position);
	}

	public boolean equals(City city) {
		return this.name.equals(city.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof City) {
			return ((City) obj).name.equals(this.name);
		}

		return false;
	}

	@Override
	public String toString() {
		return this.name + "{" + this.position.x + "," + this.position.y + "}";
	}
}
