package ea;

public class Population {
	private final Route[] routes;
	private int index = 0;

	public Population(int size) {
		this.routes = new Route[size];
	}

	private void add(Route route) {
		this.routes[this.index++] = route;
	}

	public void fillRandom() {
		for (int i = 0; i < this.getCountRoutes(); i++) {
			this.add(new Route(true, this.getCountRoutes()));
		}
	}

	public Route[] getAll() {
		return this.routes;
	}

	public Route getBest() {
		Route bestRoute = this.routes[0];

		for (Route route : this.routes) {
			if ((route.getDistance() < bestRoute.getDistance())) {
				bestRoute = route;
			}
		}

		return bestRoute;
	}

	public int getCountRoutes() {
		return this.routes.length;
	}

	public Route getRandom() {
		return Util.randomChoice(this.routes);
	}

	public void save(int i, Route child) {
		this.routes[i] = child;
	}
}
