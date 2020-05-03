
public class GameLogicClient {
	
	int category1WordAttempts;
	int category2WordAttempts;
	int category3WordAttempts;
	
	int category1WordsCorrect;
	int category2WordsCorrect;
	int category3WordsCorrect;
	
	GameLogicClient() {
		category1WordAttempts = 0;
		category2WordAttempts = 0;
		category3WordAttempts = 0;
		
		category1WordsCorrect = 0;
		category2WordsCorrect = 0;
		category3WordsCorrect = 0;
	}
	
	public boolean didWin() {
		
		if (category1WordsCorrect == 1 && category2WordsCorrect == 1 && category3WordsCorrect == 1) 
			return true;
		else
			return false;
	}
	
	public boolean didLose() {
		if ( (category1WordAttempts >= 3 && category1WordsCorrect == 0) || 
			 (category2WordAttempts >= 3 && category2WordsCorrect == 0) || 
			 (category3WordAttempts >= 3 && category3WordsCorrect == 0) ) 
			return true;
		else
			return false;
	}
	
}
