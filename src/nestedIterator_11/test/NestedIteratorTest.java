package nestedIterator_11.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import nestedIterator_11.NestedIterator;

public class NestedIteratorTest {

	public static void main(String[] args) {
		
		// prepare nested collections
		LinkedList<Object> stringDoubleList = new LinkedList<>();
		stringDoubleList.add(new ArrayList<String>(Arrays.asList("qwe11", null, "qwe12", "qwe13")));
		stringDoubleList.add(new LinkedList<String>(Arrays.asList("qwe21", "qwe22")));
		stringDoubleList.add("qwe31");
		stringDoubleList.add(new String[]{"qwe41", "qwe42","qwe43"});
		String[][] array2d = new String[][]{{"asd1","asd2"},{"asd3"}};
		
		NestedIterator<String> it = new NestedIterator<>(Arrays.asList("xxx","yyy"), stringDoubleList, null, array2d);
		LinkedList<String> collector = new LinkedList<>();
		for (String s : it) {
			//System.out.println(s);
			collector.add(s);
		}
		
		List<String> target = Arrays.asList("xxx","yyy","qwe11", null,"qwe12","qwe13","qwe21","qwe22","qwe31","qwe41","qwe42","qwe43", null,"asd1","asd2","asd3");
		assertListsEquals(collector, target);
		System.out.println("Success");
	}
	
	private static <E> void assertListsEquals(List<E> source, List<E> target){
		if (source.size() != target.size()) {
			throw new AssertionError("Different size. Source: " + source.size() + ". Target: " + target.size());
		}
		Iterator<E> it1 = source.iterator();
		Iterator<E> it2 = target.iterator();
		while (it1.hasNext()) {
			E e1 = it1.next();
			E e2 = it2.next();
			if (e1 == null && e2 == null) {
				continue;
			}
			if (e1 == null  || !e1.equals(e2)){
				throw new AssertionError("Different values. Source: " + e1 + ". Target: " + e2);
			}
		}
	}
}
