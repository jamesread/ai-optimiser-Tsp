package eatest;

import ea.City;
import ea.RenderingWindow;
import ea.Route;

public class TestRenderingWindow {
	public static void main(String[] args) {
		Main.map.addCity(new City("a", 10, 10));
		Main.map.addCity(new City("b", 200, 200));
		RenderingWindow window = new RenderingWindow();

		Route route = new Route(true, 6);

		window.update(0, route);

	}
}
