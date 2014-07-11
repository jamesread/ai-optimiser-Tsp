package ea;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Algorithm {
	static final Logger logger = LoggerFactory.getLogger(Algorithm.class);

	private static final double MUTATION_RATE = 0.05;
	private static final int TOURNAMENT_SIZE = 3;

	private static Route crossover(Route p1, Route p2) {
		return crossover0x1(p1, p2);
	}

	private static Route crossover0x1(Route p1, Route p2) {
		int cut1 = Util.random(0, p1.getCityCount());
		int cut2 = Util.random(0, p1.getCityCount());
		int cutS = Math.min(cut1, cut2);
		int cutF = Math.max(cut1, cut2);

		return crossoverOx1(p1, p2, cutS, cutF);
	}

	public static Route crossoverOx1(Route p1, Route p2, int cutS, int cutF) {
		City[] offspringCities = new City[p1.getCityCount()];
		int countAdded = 0;

		logger.debug("" + p1);
		logger.debug("" + p2);
		logger.debug("cut offs: " + cutS + " -> " + cutF);

		while (countAdded < p1.getCityCount()) {
			List<City> p1Cut = p1.sublist(cutS, cutF);
			logger.debug("cut acts: " + p1Cut + " (from p1)");

			List<City> p2cities = new WrappingReader<Route, City>(p2).read(cutF, cutS + p1Cut.size());
			p2cities.removeAll(p1Cut);
			logger.debug("p2 cities: " + p2cities);

			for (int i = cutS; i < cutF; i++) {
				offspringCities[i] = p1.get(i);
				countAdded++;
				logger.debug("Added at " + i + " cut " + offspringCities[i]);
			}

			for (int i = 0; i < (p2cities.size()); i++) {
				int index = cutF + i;

				if (index >= (p1.getCityCount())) {
					index -= (p1.getCityCount());
				}

				City c = p2cities.get(i);
				offspringCities[index] = c;
				countAdded++;
				logger.debug("Added at " + index + " p2  " + offspringCities[index]);
			}
		}

		logger.debug("o: " + Arrays.toString(offspringCities));

		if (offspringCities.length < p1.getCityCount()) {
			logger.debug("found problem" + Arrays.toString(offspringCities));
		}

		Route offspring = new Route(offspringCities);
		offspring.p1 = p1;
		offspring.p2 = p2;

		logger.trace("final cc: " + Arrays.toString(offspringCities) + p1.getCityCount());

		return offspring;
	}

	public static Population evolve(Population population) {
		Population next = new Population(population.getCountRoutes());

		for (int i = 0; i < population.getCountRoutes(); i++) {
			Route a = tournamentSelection(population);
			Route b = tournamentSelection(population);

			Route c = crossover(a, b);
			c = mutate(c);

			next.save(i, c);
		}

		return next;
	}

	public static Route mutate(Route mutant) {
		for (int i = 0; i < mutant.getCityCount(); i++) {
			if (Math.random() < MUTATION_RATE) {
				int positionA = (int) (Math.random() * mutant.getCityCount());
				int positionB = (int) (Math.random() * mutant.getCityCount());

				mutant.swapSteps(positionA, positionB);
			}
		}

		return mutant;
	}

	private static Route tournamentSelection(Population source) {
		Population competition = new Population(TOURNAMENT_SIZE);

		for (int i = 0; i < competition.getCountRoutes(); i++) {
			Route rnd = source.getRandom();

			competition.save(i, rnd);
		}

		return competition.getBest();
	}

}
