package collector_32;

import java.util.*;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

/**
 * This collector collects only those elements of Stream<T> 
 * which have the maximal rate due to specified 'rater' function.
 * The collector is built using static method MaxRatedWinnersCollector.of(...)
 */
public class MaxRatedWinnersCollector {
	private MaxRatedWinnersCollector() {} // hidden constructor

	/**
	 * Constructs the Collector using specified 'rater' instance
	 */
	public static <T> Collector<T, ?, List<T>> of(ToLongFunction<T> rater) {
		return Collector.of(
				() -> new RateAccumulator<T>(rater), 
				RateAccumulator::accumulate,
				RateAccumulator::combine,
				RateAccumulator::getWinnersList,
				Characteristics.UNORDERED);
	}

	/**
	 * The internal accumulating class for implementation of Collector
	 */
	private static class RateAccumulator<T> {
		ToLongFunction<T> rater;
		long currentMax;
		List<T> winners = new ArrayList<T> ();
		
		/**
		 * Constructor
		 * @param <T> rater - functional object, giving the long 'rate' value to each T object
		 * .substring(ind)
		 */
		public RateAccumulator(ToLongFunction<T> rater) {
			this.rater = rater;
		}
		
		/**
		 * Gets list of collected 'winners' - the rate champions
		 * @return returns list of winners (the T instances having max rate)
		 */
		public List<T> getWinnersList() {
			return winners;
		}

		/**
		 * Accumulate 'champions', using the following algorithm:
		 * - calculates rate value, applying the 'rater' to specified 'value'
		 * - makes the decision: 
		 *    - if rate < currentMax then do nothing
		 *    - if rate == currentMax then collect
		 *    - if rate > currentMax then make it new champion
		 * @param value - next element from stream
		 */
		public void accumulate(T value) {
			if (this.rater != null) {
				var tmpRate = rater.applyAsLong(value);
				if (currentMax == tmpRate) {
					winners.add(value);
				} else if (tmpRate > currentMax) {
					winners.clear();
					winners.add(value);
					currentMax = tmpRate;
				}
			} else {
				currentMax = rater.applyAsLong(value);
			}
		}

		/**
		 * Updates this accumulator using data from other:
		 * - if one of rates are greater, then only greater remains
		 * - else this appends other's list of champions to it's own list
		 * @param other - other accumulator
		 * @return this accumulator
		 */
		public RateAccumulator<T> combine(RateAccumulator<T> other) {
			return other.currentMax > this.currentMax ? other : other.currentMax < this.currentMax ?
					this : combineAccumulators(other, this);
		}

		private RateAccumulator<T> combineAccumulators(RateAccumulator<T> first,
				RateAccumulator<T> second) {
			first.winners.addAll(second.winners);
			first.currentMax = second.currentMax;
			return first;
		}
	}
}
