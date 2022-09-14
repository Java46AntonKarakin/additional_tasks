package quickDictionary_09;

public class QuickDictionaryNodesSolution {
	private static class Node<String> {
		String obj;
		Node<String>[] children = new Node [26];
		Node() {
		}
		Node(String obj) {
			this.obj = obj;
		}
	}

	Node<String> root = new Node<String>();

	public String put(String key, String value) {
		String res = get(key);
		addArticle(key, value);
		return res;
	}

	private void addArticle(String key, String value) {
		char[] keyCharArr = key.toCharArray();
		Node<String> current = root;
		for (char ch : keyCharArr) {
			int index = ch - 65;
			if (current.children[index] == null) {
				current.children[index] = new Node<String>();
			}
			current = current.children[index];
		}
		current.obj = value;
	}

	public String get(String key) {
		char[] keyCharArr = key.toCharArray();
		Node<String> current = root;
		for (char ch : keyCharArr) {
			int index = ch - 65;
			if (current.children[index] == null) {
				current.children[index] = new Node<String>();
			}
			current = current.children[index];
		}
		return current.obj;
	}
}