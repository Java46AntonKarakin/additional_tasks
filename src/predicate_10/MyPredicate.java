package predicate_10;

public interface MyPredicate<T> {

	boolean test(T t);

	default MyPredicate<T> negate() {
		return t -> !test(t);
	}

	default MyPredicate<T> or(MyPredicate<T> other) {
		return t -> test(t) || other.test(t);
	}

	default MyPredicate<T> and(MyPredicate<T> other) {
		return t -> test(t) && other.test(t);
	}

	static <T> MyPredicate<T> isEqual(Object targetRef) {
		return t -> targetRef.equals(t);
	}

	static <T> MyPredicate<T> not(MyPredicate<T> target) {
		return t -> !target.test(t);
	}
}