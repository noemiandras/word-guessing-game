import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GuessTest {

	String word;
	GameLogic logic;
	ArrayList<String> iceCream; 
	
	/*
	 * Private methods that assign data to each category
	 */
	private void addCategory2_iceCream() {
		iceCream.add("coconut");
		iceCream.add("chocolate");
		iceCream.add("vanilla");
		iceCream.add("strawberry");
		iceCream.add("caramel");
		iceCream.add("coffee");
		iceCream.add("oreo");
		iceCream.add("pistachio");
		iceCream.add("mint");
		iceCream.add("banana");
		iceCream.add("mango");
		iceCream.add("raspberry");
		iceCream.add("cookie");
		iceCream.add("matcha");
	}
	
	@BeforeEach
	void init() {
		logic = new GameLogic();
		iceCream = new ArrayList<String>();
		addCategory2_iceCream();
	}
	
	@Test
	void letterInWord() {
		word = "cat";
		logic.currentWord = word;
		
		ArrayList<Integer> index = logic.getPosition('c');
		
		assertEquals(0, index.get(0), "word letter position 'c' in 'cat' evaluated incorrectly");	
	}
	
	@Test
	void letterNotInWord() {
		word = "cat";
		logic.currentWord = word;
		
		ArrayList<Integer> index = logic.getPosition('f');

		assertTrue(index.isEmpty(), "letter not in word 'cat' was evaluated incorrectly");
	}
	
	@Test
	void lettersInWord() {
		word = "hello";
		logic.currentWord = word;
		
		ArrayList<Integer> index = logic.getPosition('l');
		
		assertEquals(2, index.size(), "Number of times 'l' appears in 'hello' was counted incorrectly");

	}
	
	@Test
	void pickIceCreamWord() {
		
		//pick word from ice cream category which is category 2
		logic.pickWord(2);
		
		assertTrue(iceCream.contains(logic.currentWord), "word was not picked from the ice cream category");
	}
	
	@Test
	void pickIceCreamDuplicateWordCoconut() {
		
		//assign words to the wordsPlayed list without one word
		logic.setWordsPlayed("chocolate");
		logic.setWordsPlayed("vanilla");
		logic.setWordsPlayed("strawberry");
		logic.setWordsPlayed("caramel");
		logic.setWordsPlayed("coffee");
		logic.setWordsPlayed("oreo");
		logic.setWordsPlayed("pistachio");
		logic.setWordsPlayed("mint");
		logic.setWordsPlayed("banana");
		logic.setWordsPlayed("mango");
		logic.setWordsPlayed("raspberry");
		logic.setWordsPlayed("cookie");
		logic.setWordsPlayed("matcha");
		
		//word left should be coconut
		logic.pickWord(2);
		
		assertEquals("coconut", logic.currentWord, "word left to pick is not coconut");	
	}
	
	

}
