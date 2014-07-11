package ea;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import eatest.Main;

public class Route implements ArrayAccess<City> {
	public Vector<City> steps = new Vector<City>();
	public Route p1 = null;
	public Route p2 = null;

	public Route(boolean init, int size) {
		this.steps = new Vector<>(size);

		if (init) {
			Vector<City> cities = Main.map.getCities();

			Collections.shuffle(cities);
			Iterator<City> it = cities.iterator();

			while (it.hasNext()) {
				this.steps.add(it.next());
			}
		}
	}

	public Route(City... cities) {
		for (City city : cities) {
			this.steps.add(city);
		}
	}

	public Route(String... cities) {
		for (String citie : cities) {
			this.steps.add(new City(citie));
		}
	}

	public void add(City city) {
		this.steps.add(city);
	}

	public boolean contains(City search) {
		for (City city : this.steps) {
			if (city.equals(search)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public City get(int idx) {
		return this.steps.get(idx);
	}

	public Vector<City> getAll() {
		return this.steps;
	}

	public int getCityCount() {
		int count = 0;

		for (City c : this.steps) {
			if (c != null) {
				count++;
			}
		}

		return count;
	}

	public int getDistance() {
		int distance = 0;

		City current = null;

		for (City next : this.steps) {
			if (current == null) {
				current = next;
				continue;
			}

			distance += current.distanceTo(next);

			current = next;
		}

		return distance;
	}

	public City getRandom() {
		int pos = (int) (Math.random() * this.steps.size());

		return this.steps.elementAt(pos);
	}

	public City getStart() {
		return this.steps.firstElement();
	}

	public City getStep(int i) {
		return this.steps.elementAt(i);
	}

	@Override
	public int length() {
		return this.steps.size();
	}

	public void push(City step) {
		this.steps.add(step);
	}

	@Override
	public void set(int index, City t) {
		this.steps.set(index, t);
	}

	public void setStep(int i, City city) {
		if (i < this.steps.size()) {
			this.steps.set(i, city);
		} else {
			this.steps.add(city);
		}
	}

	public List<City> sublist(int cutS, int cutF) {
		return this.steps.subList(cutS, cutF);
	}

	public void swapSteps(int positionA, int positionB) {
		City tmp = this.steps.elementAt(positionA);

		this.steps.set(positionA, this.steps.elementAt(positionB));
		this.steps.set(positionB, tmp);
	}

	@Override
	public String toString() {
		return this.steps.toString() + " = " + this.getDistance();
	}
}
