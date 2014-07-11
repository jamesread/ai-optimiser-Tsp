package ea;

import java.util.Arrays;
import java.util.List;

public class Util {
	public static int random(int i, int cityCount) {
		return (int) (i + (Math.random() * cityCount));
	}

	public static <T> T randomChoice(List<T> list) {
		int idx = (int) ((Math.random()) * list.size());

		return list.get(idx);

	}

	public static <T> T randomChoice(T... list) {
		return randomChoice(Arrays.asList(list));
	}
}
