package quickDictionary_09.test;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import quickDictionary_09.Parent;
import quickDictionary_09.QuickDictArrayList;
import quickDictionary_09.QuickDictNodes;

class QuickDictionaryTests {
	
	static String mammals = "cat, gog, whale";
	static String birds = "eagle, chicken, papugay";
	static String fish = "shark, clownfish, catfish";
	static String reptiles = "lizard, snake, amphisbaenia";

	static String[] arrayOfValues = { mammals, birds, fish, reptiles };
	static String[] arrayOfKeys = { "MAMMALS", "BIRDS", "FISH", "REPTILES" };

	/*
	 * "Stream" style 
	 * values of valuesPT and keysPT should be < 10000 
	 * otherways tests will take more than 10 sec per iteration for "dictArrList"
	 * and more than 20 sec for "dictNodes"
	 */

	
//	static String[] valuesPT = getPseudoWordsArray(10000);
//	static String[] keysPT = getPseudoWordsArray(10000);

	/* "Double for" style */
	static String[] valuesPT = getPseudoWordsArrayOld(10000);
	static String[] keysPT = getPseudoWordsArrayOld(10000);

	static final int N_REPEATS = 5;

	@Test
	void dictionaryTest() {
		dictFunctionalTest(new QuickDictArrayList());
		dictFunctionalTest(new QuickDictNodes());
	}

	private void dictFunctionalTest(Parent dictionary) {
		for (int i = 0; i < arrayOfKeys.length; i++) {
			String put = dictionary.put(arrayOfKeys[i], arrayOfValues[i]);
			String get = dictionary.get(arrayOfKeys[i]);
			assertNull(put);
			assertTrue(get.equals(arrayOfValues[i]));
			assertEquals(get, dictionary.put(arrayOfKeys[i], arrayOfValues[i]));
		}
		assertNull(dictionary.get("SOMETHING"));
	}

	@Test
	void dictionaryPerformanceTest() {
		int iterationCounter = 1;
		System.out.println("*".repeat(10) + " dictArrList - performanceTest " + "*".repeat(10));
		for (int j = 0; j < N_REPEATS; j++) {
			System.out.printf("iteration #%d:     ", iterationCounter++);
			performanceTest(new QuickDictArrayList());
		}

		System.out.println("*".repeat(10) + " dictNodes - performanceTest " + "*".repeat(10));
		iterationCounter = 1;
		for (int j = 0; j < N_REPEATS; j++) {
			System.out.printf("iteration #%d:     ", iterationCounter++);
			performanceTest(new QuickDictNodes());
		}
	}

	private static void performanceTest(Parent dictionary) {
		var startOfIteration = LocalDateTime.now();

		for (int i = 0; i < keysPT.length; i++) {
			dictionary.put(keysPT[i], valuesPT[i]);
		}

		var putTestLength = ChronoUnit.MILLIS.between(startOfIteration, LocalDateTime.now());
		var startOfGetTest = LocalDateTime.now();

		for (int i = 0; i < keysPT.length; i++) {
			dictionary.get(keysPT[i]);
		}

		var getTestLength = ChronoUnit.MILLIS.between(startOfGetTest, LocalDateTime.now());
		var commonLength = putTestLength + getTestLength;
		System.out.printf("put = %s ms, get = %s ms, common time = %s ms;\n", putTestLength, getTestLength,
				commonLength);

	}

	private static String[] getPseudoWordsArray(int numberOfElements) {
		String[] res = new String[numberOfElements];
		IntStream.range(0, numberOfElements).forEach(x -> res[x] = getWord());
		return res;
	}

	private static String[] getPseudoWordsArrayOld(int numberOfElements) {

		String[] res = new String[numberOfElements];

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < numberOfElements; i++) {
			int length = getRandomLength();

			for (int j = 0; j < length; j++) {

				char randomChar = getRandomChar();
				sb.append(randomChar);
			}
			res[i] = sb.toString();
			sb = new StringBuilder();
		}
		return res;
	}

	private static String getWord() {
		StringBuilder sb = new StringBuilder();
		IntStream.range(0, getRandomLength()).map(x -> getRandomChar()).forEach(x -> sb.append((char) x));
		return sb.toString();
	}

	private static int getRandomLength() {
		return (int) (Math.random() * ((20 - 1) + 1) + 1);
	}

	private static char getRandomChar() {
		return (char) ((Math.random() * ((90 - 65) + 1)) + 65);
	}
}
