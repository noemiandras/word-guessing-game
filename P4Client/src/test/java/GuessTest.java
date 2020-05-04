import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class GuessTest {

	String word;
	GameLogicClient logic;

	@BeforeEach
	void init() {
		logic = new GameLogicClient();
	}

	@Test
	void evaluateDidLose() {
		logic.category1WordAttempts = 3;
		logic.category1WordsCorrect = 0;
		logic.category2WordAttempts = 1;
		logic.category2WordsCorrect = 1;
		logic.category3WordAttempts = 2;
		logic.category3WordsCorrect = 1;

		assertTrue(logic.didLose());
	}

	@Test
	void evaluateDidWin() {
		logic.category1WordAttempts = 1;
		logic.category1WordsCorrect = 1;
		logic.category2WordAttempts = 1;
		logic.category2WordsCorrect = 1;
		logic.category3WordAttempts = 2;
		logic.category3WordsCorrect = 1;

		assertTrue(logic.didWin());
	}

	@Test
	void evaluateDidLoseReverse() {
		logic.category1WordAttempts = 1;
		logic.category1WordsCorrect = 1;
		logic.category2WordAttempts = 1;
		logic.category2WordsCorrect = 1;
		logic.category3WordAttempts = 2;
		logic.category3WordsCorrect = 1;

		assertFalse(logic.didLose());
	}

	@Test
	void evaluateDidWinReverse() {
		logic.category1WordAttempts = 3;
		logic.category1WordsCorrect = 0;
		logic.category2WordAttempts = 1;
		logic.category2WordsCorrect = 1;
		logic.category3WordAttempts = 2;
		logic.category3WordsCorrect = 1;

		assertFalse(logic.didWin());
	}

	@Test
	void evaluateNoGuessLose() {
		assertFalse(logic.didLose());
	}

	@Test
	void evaluateNoGuessWin() {
		assertFalse(logic.didWin());
	}


}
