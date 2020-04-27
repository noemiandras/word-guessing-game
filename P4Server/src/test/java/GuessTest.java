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
	
	@BeforeEach
	void init() {
		logic = new GameLogic();
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
	void isWordInList() {
		logic.setWordsPlayed("hi");
		logic.setWordsPlayed("hello");
		
		logic.currentWord = "hello";
		
		assertTrue(logic.isInList(), "the word 'hello' is not in the wordsPlayed Arraylist");
	}

}
