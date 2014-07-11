package eatest;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import org.joda.time.Instant;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ea.Algorithm;
import ea.City;
import ea.Map;
import ea.Population;
import ea.RenderingWindow;
import ea.Route;

public class Main {
	public static final Map map = new Map();

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

	public static final Instant startTime = Instant.now();

	public static void loadMapLondonUnderground() throws Exception {
		CSVReader r = new CSVReader(new FileReader(new File("/home/xconspirisist/Downloads/London stations.csv")));
		r.readNext();

		String[] line;

		while ((line = r.readNext()) != null) {
			System.out.println(Arrays.toString(line));
			map.addCity(new City(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2])));
		}
	}

	public static void loadMapUk(RenderingWindow wnd) {
		map.addCity(new City("Brighton", 920, 1350));
		map.addCity(new City("Exeter", 632, 1372));
		map.addCity(new City("Southampton", 841, 1333));
		map.addCity(new City("Bournemouth", 804, 1359));
		map.addCity(new City("London", 963, 1235));
		map.addCity(new City("Liverpool", 677, 938));
		map.addCity(new City("Manchester", 735, 927));
		map.addCity(new City("Birmingham", 764, 1098));
		map.addCity(new City("Bristol", 708, 1257));
		map.addCity(new City("Reading", 867, 1250));
		map.addCity(new City("Maidstone", 1019, 1268));
		map.addCity(new City("Guildford", 933, 1276));
		map.addCity(new City("Winchester", 855, 1293));
		map.addCity(new City("Taunton", 667, 1319));
		map.addCity(new City("Truro", 475, 1439));
		map.addCity(new City("Dorchester", 740, 1365));
		map.addCity(new City("Trowbridge", 762, 1280));
		map.addCity(new City("Gloucester", 743, 1197));
		map.addCity(new City("Oxford", 827, 1192));
		map.addCity(new City("Lewes", 977, 1328));
		map.addCity(new City("Aylesbury", 887, 1171));
		map.addCity(new City("Chelmsford", 1022, 1199));
		map.addCity(new City("Ipswitch", 1084, 1152));
		map.addCity(new City("Norwich", 1096, 1122));
		map.addCity(new City("Cambridge", 990, 1126));
		map.addCity(new City("Bedford", 925, 1134));
		map.addCity(new City("Lincoln", 907, 965));
		map.addCity(new City("Hereford", 701, 1144));
		map.addCity(new City("Shrewsbury", 692, 1057));
		map.addCity(new City("Cheshire", 691, 976));

		wnd.addBackgroundImage("src/main/resources/UnitedKingdom.jpg");
	}

	public static void main(String[] args) throws Exception {
		Logger root = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);

		RenderingWindow renderer = new RenderingWindow();

		loadMapUk(renderer);

		Population population = new Population(50);
		population.fillRandom();

		int generation = 0;

		Route last = new Route(true, 6);

		renderer.init();

		while (true) {
			generation++;

			for (Route route : population.getAll()) {
				// logger.info("" + route);
			}

			population = Algorithm.evolve(population);
			Route best = population.getBest();

			if (best != last) {
				if (best.getDistance() < last.getDistance()) {
					last = best;
				}
			}

			// logger.info("Generation " + generation + " Best: " + best);

			renderer.update(generation, last);
			// Thread.sleep(100);
		}

	}
}
