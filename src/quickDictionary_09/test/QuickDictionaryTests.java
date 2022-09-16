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

	/* solution with two ArrayLists */
	Parent dicArrList = new QuickDictArrayList();

	/* solution with Node structure */
	Parent dicNodes = new QuickDictNodes();

	static String mammals = "cat, gog, whale";
	static String birds = "eagle, chicken, papugay";
	static String fish = "shark, clownfish, catfish";
	static String reptiles = "lizard, snake, amphisbaenia";

	static String[] arrayOfValues = { mammals, birds, fish, reptiles };
	static String[] arrayOfKeys = { "MAMMALS", "BIRDS", "FISH", "REPTILES" };
	
	
	/*
	 *  "Stream" style  
	 *  values should be > 2000 otherways tests will take more than 10 sec per iteration		
	 */
	
//	static String[] valuesPT = getPseudoWordsArray(2000);
//	static String[] keysPT = getPseudoWordsArray(2000);
	
	/* "Double for" style   	 */
	static String[] valuesPT = getPseudoWordsArrayOld(20000);
	static String[] keysPT = getPseudoWordsArrayOld(20000);
	
	static final int N_REPEATS = 5;

	@Test
	void dictionaryTest() {
		dictFunctionalTest (dicArrList);
		dictFunctionalTest (dicNodes);
	}
	
	private void dictFunctionalTest (Parent dictionary) {
		for (int i = 0; i < arrayOfKeys.length; i++) {
			String put = dictionary.put(arrayOfKeys[i], arrayOfValues[i]);
			String get = dictionary.get(arrayOfKeys[i]);
			assertNull(put);
			assertTrue(get.equals(arrayOfValues[i]));
			assertEquals(get, dictionary.put(arrayOfKeys[i], arrayOfValues[i]));
		}
	}

	@Test
	void dictionaryPerformanceTest() {
		System.out.println("*".repeat(10) + "dicArrList" + "*".repeat(10));
		performanceTest(dicArrList);
		
		System.out.println("*".repeat(10) + "dicNodes" + "*".repeat(10));
		performanceTest(dicNodes);
	}
	
	private static void performanceTest(Parent dictionary) {
		
		int iterationCounter = 1;
		for (int j = 0; j < N_REPEATS; j++) {
			
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
			System.out.printf("iteration #%d:     put = %s ms, get = %s ms, common time = %s ms;\n",
					iterationCounter++, putTestLength, getTestLength, commonLength );
		}
	}
	
	private static String[] getPseudoWordsArray(int numberOfElements) {
		StringBuilder sb = new StringBuilder();
		String[] res = new String[numberOfElements];
		IntStream.range(0, numberOfElements).forEach(x -> res[x] = getWord(sb));
		return res;
	}

	private static String[] getPseudoWordsArrayOld(int numberOfElements) {
		
		String[] res = new String[numberOfElements];
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < numberOfElements; i++) {
			int length =getRandomLength();
			
			for (int j = 0; j < length; j++) {
				
				char randomChar = getRandomChar();
				sb.append(randomChar);
			}
			res[i] = sb.toString();
			sb = new StringBuilder();
		}
		return res;
	}
	
	private static String getWord(StringBuilder sb) {
		IntStream.range(0, getRandomLength())
		.map(x -> getRandomChar())
		.forEach(x -> sb.append((char)x));
		return sb.toString();
	}
		
    private static int getRandomLength () {
    	return (int)(Math.random() * ((20 - 1) + 1) + 1);
    }
    
    private static char getRandomChar () {
    	return (char)((Math.random() * ((90 - 65) + 1)) + 65);
    }
}
