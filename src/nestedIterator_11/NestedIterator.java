package nestedIterator_11;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TODO: implements methods below to support the following functionality...
 * 
 * This is Nested Flatten Iterator, which is able to iterate over specified type of objects,
 * extracting them from nested collection.
 * (Example: iterate over all Strings in the list of sets of arrays of Strings. etc.)
 * - The iterator could be constructed from any list of objects, 
 *   including other Iterators, Iterable-s (Collections, etc.) and arrays of any dimensions.
 * - The iterator is Iterable itself.
 * - The iterator permits usage of null values in the nested collections
 * - The implementation does not use recursion.
 * - Note: actually this is not final solution because it is type-unsafe (heap poluition is possible)
 */
public class NestedIterator<E> implements Iterator<E>, Iterable<E> {

	private E next;
	private boolean hasNext;

	public NestedIterator(Object... params) {
		// TODO: implement
	}
	
	@Override
	public boolean hasNext() {
		return hasNext;
	}

	@Override
	public E next() {
		if (!hasNext) {
			throw new NoSuchElementException();
		}
		E result = next;
		hasNext = findNext();
		return result;
	}

	private boolean findNext() {
		// TODO: implement
		return false;
	}

	@Override
	public Iterator<E> iterator() { // implementation of Iterable imnterface
		return this;
	}
}


