package quickDictionary_09;

public class QuickDictNodes implements Parent{
	
	private static class Node<String> {
		String obj;
		Node<String>[] children = new Node[26];
	}

	Node<String> root = new Node<String>();

	@Override
	public String put(String key, String value) {
		try {
			if (!key.matches("[A-Z]*")) {
				throw new IllegalArgumentException(String.format(" <<%s>> doesn't match ([A-Z])*", key));
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		String res = get(key);
		addArticle(key, value);
		return res;
	}

	@Override
	public String get(String key) {
		char[] keyCharArr = key.toCharArray();
		Node<String> current = root;
		for (char ch : keyCharArr) {
			int index = ch - 65;
			if (current.children[index] == null) {
				return null;
			}
			current = current.children[index];
		}
		return current.obj;
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
}