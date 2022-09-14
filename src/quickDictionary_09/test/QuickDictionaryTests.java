package quickDictionary_09.test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import quickDictionary_09.QuickDictionaryArrayListsSolution;
import quickDictionary_09.QuickDictionaryNodesSolution;

class QuickDictionaryTests {

	/* solution with two ArrayLists */
//	QuickDictionaryArrayListsSolution dic1 = new QuickDictionaryArrayListsSolution();

	/* solution with Node structure */
	QuickDictionaryNodesSolution dic1 = new QuickDictionaryNodesSolution();

	String mammals = "cat, gog, whale";
	String birds = "eagle, chicken, papugay";
	String fish = "shark, clownfish, catfish";
	String reptiles = "lizard, snake, amphisbaenia";

	String[] arrayOfValues = { mammals, birds, fish, reptiles };
	String[] arrayOfKeys = { "MAMMALS", "BIRDS", "FISH", "REPTILES" };

	String[] valuesPT = generateStringArray(1000);
	String[] keysPT = generateStringArray(1000);
	int repeats = 1000;

	@Test
	void dictionaryTest() {

		for (int i = 0; i < arrayOfKeys.length; i++) {
			String put = dic1.put(arrayOfKeys[i], arrayOfValues[i]);
			String get = dic1.get(arrayOfKeys[i]);

			assertNull(put);

			assertTrue(get.equals(arrayOfValues[i]));

			assertEquals(get, dic1.put(arrayOfKeys[i], arrayOfValues[i]));

			System.out.println(dic1.get(arrayOfKeys[i]));
		}
	}

	@Test
	void dictionaryPerformanceTest() {

		for (int j = 0; j < repeats; j++) {
			for (int i = 0; i < keysPT.length; i++) {
				dic1.put(keysPT[i], valuesPT[i]);
				dic1.get(keysPT[i]);
			}
		}
	}

	String[] generateStringArray(int numberOfElements) {
		
		String[] res = new String[numberOfElements];
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < numberOfElements; i++) {
			int length = (int) ((Math.random() * ((20 - 1) + 1)) + 1);
			
			for (int j = 0; j < length; j++) {
				
				char randomChar = (char) ((Math.random() * ((90 - 65) + 1)) + 65);
				sb.append(randomChar);
			}
			res[i] = sb.toString();
			sb = new StringBuilder();
		}
		return res;
	}

}
