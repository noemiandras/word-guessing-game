import java.io.Serializable;

public class WordInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int clientNum;
	
	String category;
	char letterGuess;
	int lengthOfWord;
	boolean charInWord;
	int letterPos;
    int numGuesses;
	
    WordInfo() {
    	category = "";
    	letterGuess = ' ';
    	lengthOfWord = 0;
    	charInWord = false;
    	letterPos = 0;
        numGuesses = 0;
    }
    
}