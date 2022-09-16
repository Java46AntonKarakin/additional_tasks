package quickDictionary_09;

import java.util.ArrayList;

public class QuickDictArrayList implements Parent{

	private ArrayList<String> keys = new ArrayList<>();
	private ArrayList<String> values = new ArrayList<>();

	@Override
	public String put(String key, String value) {
		if (!key.matches("([A-Z])*")) {
			throw new IllegalArgumentException("wrong input value");
		}

		String res = null;
		if (keys.contains(key)) {
			int index = keys.indexOf(key);
			res = values.get(index);
			values.set(index, value);
		} else {
			keys.add(key);
			values.add(value);
		}
		return res;
	}

	@Override
	public String get(String key) {
		if (!key.matches("([A-Z])*")) {
			throw new IllegalArgumentException("wrong input value");
		}
		return keys.contains(key) ? values.get(keys.indexOf(key)) : null;
	}
}
