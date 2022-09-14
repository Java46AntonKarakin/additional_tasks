package quickDictionary_09;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class QuickDictionaryArrayListsSolution {
	private static class Node {
		Object obj;
		Node[] children = new Node[26];

		Node(String obj) {
			this.obj = obj;
		}
	}
	Node root = null;
	
	/*
	 * The semantics of methods put and get are the same as Map<String, String> has.
	 * 
	 * The QuickDictionary key consists only from letters [A-Z] and its max length
	 * is 20 letters
	 * 
	 * Main Requirement: the complexity of QuickDictionary methods must be always
	 * the fixed value, depending only on length of key parameter: O(length(key))
	 */
	

	private ArrayList<String> keys = new ArrayList<>();
	private ArrayList<String> values = new ArrayList<>();

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

	public String get(String key) {
		if (!key.matches("([A-Z])*")) {
			throw new IllegalArgumentException("wrong input value");
		}
		return keys.contains(key) ? values.get(keys.indexOf(key)) : null;
	}
}
