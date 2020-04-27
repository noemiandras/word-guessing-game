import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
	
	private ArrayList<String> category1;
	private ArrayList<String> category2;
	private ArrayList<String> category3;
	private ArrayList<Character> lettersPlayed;
	private ArrayList<String> wordsPlayed;
	String currentWord;
	int currentCategory;
	int numLoss;
	int countLetter;
	
	/*
	 * Getters and setters
	 */
	public void setWordsPlayed(String word) {
		wordsPlayed.add(word);
	}
	
	public GameLogic(){
		category1 = new ArrayList<String>();
		category2 = new ArrayList<String>();
		category3 = new ArrayList<String>();
		lettersPlayed = new ArrayList<Character>();
		wordsPlayed = new ArrayList<String>();
		
		numLoss = 6;
		countLetter = 0;
		
		currentWord = "";
		currentCategory = 0;
	}
	
	/*
	 * Method: takes in a letter and returns arrayslist of indexes
	 * 			if letter not in word, it returns an empty arraylist
	 * Test: passed 
	 */
	public ArrayList<Integer> getPosition(char letter){
		//save letter because it was played
		lettersPlayed.add(letter);
		
		ArrayList<Integer> pos = new ArrayList<Integer>();
				
		int i = 0;
		while(currentWord.indexOf(letter, i) != -1) {
			//add index where the letter is
			pos.add(i);
			countLetter++;
			//increment index to search for the rest of letters 
		   i = currentWord.indexOf(letter, i) + 1;
		}
		
		//letter is not in the 
		if( i == 0) {
			numLoss--;
		}

		return pos;
	}
	
	
	/*
	 * Method: helper function for pickWord()
	 * Test: passed 
	 */
	public boolean isInList() {
		
		//check if selected word is in the wordplayed list
		for(int i = 0; i < wordsPlayed.size(); i++) {
			if (currentWord.equals(wordsPlayed.get(i))){
				return true;
			}
		}
		
		return false;
	}
	
	/* 
	 * Method: takes in category number and assigns a word picked
	 * 		!!! method doesn't check previous words selected 
	 * Test: needs to be tested based on what categories we pick
	 */
	public void pickWord(int category) {
		currentCategory = category;
		Random rand = new Random(); //object responsible for selecting a random element
		
		//pick a random index for category
		if(category == 1) {
			currentWord = category1.get(rand.nextInt(category1.size()));
		}
		else if (category == 2) {
			currentWord = category2.get(rand.nextInt(category2.size()));
		}
		else if (category == 3) {
			currentWord = category3.get(rand.nextInt(category3.size()));

		}
		
		//check to see if picked currentWord is in the already picked word list
		if(isInList()) {
			pickWord(category);
		}
		else {
			wordsPlayed.add(currentWord);
		}
	}
	
	/*
	 * Method: resets all variables to be ready for a new word round
	 */
	public void reset() {
		currentCategory = 0;
		numLoss = 6;
		countLetter = 0;
		
		lettersPlayed.clear();
		currentWord = "";
	}
	
	/*
	 * Method: evaluates the status of the player regarding the current word
	 */
	public boolean evaluatePlayerWon() {
		//if countletter is the same as the length of the word - true
		if(countLetter == currentWord.length()) {
			return true;
		}
		return false;
	}
	

}
