package ea;

import java.util.ArrayList;

public class WrappingReader<T extends ArrayAccess<V>, V> {
	private final T t;

	public WrappingReader(T t) {
		this.t = t;
	}

	public ArrayList<V> read(int cutF, int cutS) {
		ArrayList<V> red = new ArrayList<V>();

		int diff = (this.t.length() - cutF) + (cutS);
		diff = +diff;

		for (int i = 0; i < diff; i++) {
			int index = cutF + i;

			if (index >= this.t.length()) {
				index -= this.t.length();
			}

			red.add(this.t.get(index));
		}

		return red;
	}
}
