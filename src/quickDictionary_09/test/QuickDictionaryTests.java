package quickDictionary_09.test;

import static org.junit.jupiter.api.Assertions.*;
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

	String[] listOfValues = { mammals, birds, fish, reptiles};
	String[] listOfKeys = { "MAMMALS", "BIRDS", "FISH", "REPTILES"};

	@Test
	void dictionaryTest() {

		for (int i = 0; i< listOfKeys.length; i++) {
			String put = dic1.put(listOfKeys[i], listOfValues[i]);
			String get = dic1.get(listOfKeys[i]);
			
			assertNull(put);
			
			assertTrue(get.equals(listOfValues[i]));
			
			assertEquals(get, dic1.put(listOfKeys[i], listOfValues[i]));
			
			System.out.println(dic1.get(listOfKeys[i]));
		}
	}
}
