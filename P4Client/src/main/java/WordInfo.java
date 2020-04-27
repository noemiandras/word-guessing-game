import java.io.Serializable;
import java.util.ArrayList;

public class WordInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int clientNum;
	
	int category;
	char letterGuess;
	int lengthOfWord;
	boolean charInWord;
	ArrayList<Integer> letterPositions;
    int numGuesses;
	
    WordInfo() {
    	category = -1;
    	letterGuess = ' ';
    	lengthOfWord = -1;
    	charInWord = false;
    	letterPositions = new ArrayList<Integer>();
        numGuesses = 0;
    }
    
}