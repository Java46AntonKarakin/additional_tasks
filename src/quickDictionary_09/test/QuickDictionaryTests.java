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

import quickDictionary_09.QuickDictionaryArrayListsSolution;
import quickDictionary_09.QuickDictionaryNodesSolution;

class QuickDictionaryTests {

	/* solution with two ArrayLists */
	QuickDictionaryArrayListsSolution dic1 = new QuickDictionaryArrayListsSolution();

	/* solution with Node structure */
//	QuickDictionaryNodesSolution dic1 = new QuickDictionaryNodesSolution();

	String mammals = "cat, gog, whale";
	String birds = "eagle, chicken, papugay";
	String fish = "shark, clownfish, catfish";
	String reptiles = "lizard, snake, amphisbaenia";

	String[] arrayOfValues = { mammals, birds, fish, reptiles };
	String[] arrayOfKeys = { "MAMMALS", "BIRDS", "FISH", "REPTILES" };
	
	
	/* "Stream" style    		*/
//	String[] valuesPT = getPseudoWordsArray(20000);
//	String[] keysPT = getPseudoWordsArray(20000);
	
	/* "Double for" style   	 */
	String[] valuesPT = getPseudoWordsArrayOld(20000);
	String[] keysPT = getPseudoWordsArrayOld(20000);
	
	static final int N_REPEATS = 5;

	@Test
	void dictionaryTest() {
		for (int i = 0; i < arrayOfKeys.length; i++) {
			String put = dic1.put(arrayOfKeys[i], arrayOfValues[i]);
			String get = dic1.get(arrayOfKeys[i]);
			assertNull(put);
			assertTrue(get.equals(arrayOfValues[i]));
			assertEquals(get, dic1.put(arrayOfKeys[i], arrayOfValues[i]));
		}
	}

	@Test
	void dictionaryPerformanceTest() {
		int iterationCounter = 1;
		for (int j = 0; j < N_REPEATS; j++) {
			
			var startOfIteration = LocalDateTime.now();
			
			for (int i = 0; i < keysPT.length; i++) {
				dic1.put(keysPT[i], valuesPT[i]);
			}
			
			var putTestLength = ChronoUnit.MILLIS.between(startOfIteration, LocalDateTime.now());
			var startOfGetTest = LocalDateTime.now();
			
			for (int i = 0; i < keysPT.length; i++) {
				dic1.get(keysPT[i]);
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
