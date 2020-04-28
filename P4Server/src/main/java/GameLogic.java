import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
	
	private ArrayList<String> category1; //beach
	private ArrayList<String> category2; //iceCream
	private ArrayList<String> category3; //sports
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
	
	/*
	 * CONSTRUCTOR
	 */
	public GameLogic(){
		category1 = new ArrayList<String>();
		category2 = new ArrayList<String>();
		category3 = new ArrayList<String>();
		lettersPlayed = new ArrayList<Character>();
		wordsPlayed = new ArrayList<String>();
		
		//initialize category list
		addCategory1_beach();
		addCategory2_iceCream();
		addCategory3_sports();
		
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
	 * Method: takes in category number and assigns a word picked
	 * Test: passed
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
		if(wordsPlayed.contains(currentWord)) {
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
		
		wordsPlayed.clear();
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
	
	/*
	 * Private methods that assign data to each category
	 */
	private void addCategory1_beach() {
		category1.add("tanning");
		category1.add("surfing");
		category1.add("sand");
		category1.add("ocean");
		category1.add("sea");
		category1.add("seashell");
		category1.add("sun");
		category1.add("sunscreen");
		category1.add("umbrella");
		category1.add("bikini");
		category1.add("wave");
		category1.add("seaweed");
		category1.add("crabs");
		category1.add("dolphin");
	}
	
	/*
	 * Private methods that assign data to each category
	 */
	private void addCategory2_iceCream() {
		category2.add("coconut");
		category2.add("chocolate");
		category2.add("vanilla");
		category2.add("strawberry");
		category2.add("caramel");
		category2.add("coffee");
		category2.add("oreo");
		category2.add("pistachio");
		category2.add("mint");
		category2.add("banana");
		category2.add("mango");
		category2.add("raspberry");
		category2.add("cookie");
		category2.add("matcha");
	}
	
	/*
	 * Private methods that assign data to each category
	 */
	private void addCategory3_sports() {
		category3.add("soccer");
		category3.add("tennis");
		category3.add("basketball");
		category3.add("golf");
		category3.add("swimming");
		category3.add("diving");
		category3.add("cycling");
		category3.add("football");
		category3.add("running");
		category3.add("badminton");
		category3.add("baseball");
		category3.add("kayaking");
		category3.add("fishing");
		category3.add("canoeing");
	}
	
	

}
