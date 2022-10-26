package collector_32.test;

import java.util.*;

import collector_32.MaxRatedWinnersCollector;

public class MaxRatedWinnersCollector_Test {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("Ivanov", 5);
		map.put("Petrov", 5);
		map.put("Sidorov", 3);
		map.put("Golubev", 7);
		map.put("Spiridonov", 1);	
		map.put("Kukushkin", 7);
		map.put("Antonov", 3);
		
		// Expected result is:
		//     Kukushkin=7
		//     Golubev=7
		map.entrySet().parallelStream()
		.collect(MaxRatedWinnersCollector.of(e -> e.getValue()))
		.forEach(System.out::println);				
	}

}
