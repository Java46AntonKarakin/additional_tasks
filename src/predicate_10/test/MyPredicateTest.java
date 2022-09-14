package predicate_10.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

import predicate_10.MyPredicate;

class EvenNumbersPredicate implements MyPredicate<Integer> {

	@Override
	public boolean test(Integer t) {
		return t % 2 == 0;
	}
}

class Person {
	int legs;

	Person(int legs) {
		this.legs = legs;
	}
}

class MyPredicateTest {

	EvenNumbersPredicate evenNumPr = new EvenNumbersPredicate();
	int[] source = IntStream.rangeClosed(0, 99).distinct().toArray();
	Person p3 = new Person(3);
	Person p7 = new Person(7);

	@Test
	void testTest() {
		assertTrue(evenNumPr.test(2));
		assertFalse(evenNumPr.test(3));

		var p2 = evenNumPr.negate();
		assertFalse(p2.test(2));
		assertTrue(p2.test(3));

		var p3 = p2.negate();
		assertTrue(p3.test(2));
		assertFalse(p3.test(3));
	}

	@Test
	void orTest() {
		int[] expected = Arrays.stream(source).filter(x -> x % 2 == 0 || x % 3 == 0).toArray();
		int[] actual = Arrays.stream(source).filter(x -> evenNumPr.or(t -> t % 3 == 0).test(x)).toArray();

		assertArrayEquals(actual, expected);
	}

	@Test
	void andTest() {
		int[] expected = Arrays.stream(source).filter(x -> x % 2 == 0 && x % 3 == 0).toArray();
		int[] actual = Arrays.stream(source).filter(x -> evenNumPr.and(t -> t % 3 == 0).test(x)).toArray();

		assertArrayEquals(actual, expected);
	}

	@Test
	void isEqualTest() {
		MyPredicate<Person> predicateP3 = MyPredicate.isEqual(p3);

		assertFalse(predicateP3.test(p7));
		assertFalse(predicateP3.test(new Person(3)));
		assertTrue(predicateP3.test(p3));
	}

	@Test
	void notTest() {
		int[] expected = Arrays.stream(source).filter(x -> x % 2 == 1).toArray();
		int[] actual = Arrays.stream(source).filter(x -> MyPredicate.not(evenNumPr).test(x)).toArray();

		assertArrayEquals(actual, expected);
	}
}
