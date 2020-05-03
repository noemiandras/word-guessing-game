/* AUTHOR: Noemi Andras
 * PROJECT: CS 342 Hangman
 * DESCRIPTION: this file handles all the server and separate client threads 
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	
	int port;
	TheServer server;
	private Consumer<Serializable> callback;
	
	Server(int port){
		this.port = port;
		server = new TheServer();
		server.start();
	}
	
	//sets up runnable so outside communication between server and client is seen
	public void setCallBack(Consumer<Serializable> call) {
		callback = call;
		System.out.println("callback was set");
	}
	
	//theServer doesn't handle game logic since client threads don't depend on each other
	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(port);){
				callback.accept("Server launched!");
		  
			    while(true) {
			
					ClientThread c = new ClientThread(mysocket.accept(), count);
					callback.accept("client has connected to server: " + "client #" + count);
					clients.add(c);
					c.start();
					
					count++;
					
				}
			}
			catch(Exception e) {
				callback.accept("Server socket did not launch");
			}
		}
	}
	

	class ClientThread extends Thread{
		
	
		Socket connection;
		int count;
		ObjectInputStream in;
		ObjectOutputStream out;
		
		//Game information for the specific client
		private GameLogic logic;
		WordInfo info;
		
		ClientThread(Socket s, int count){
			this.connection = s;
			this.count = count;	
			logic = new GameLogic();
		}
		
		/*
		public void updateClients(String message) {
			for(int i = 0; i < clients.size(); i++) {
				ClientThread t = clients.get(i);
				try {
				 t.out.writeObject(message);
				}
				catch(Exception e) {}
			}
		}
		*/
		
		/*
		 * Method: takes care of scenario when new category is indicated
		 * 			including clearing previous information, choosing word
		 * 			and sending letter number information 
		 * Test: not tested yet if it works
		 */
		private void startNewWord(int category) {
						
			logic.resetRound();
			logic.pickWord(category);
			
			info = new WordInfo();
			info.lengthOfWord = logic.getNumLettersInWord(); //or do logic.currentWord.length()
			info.numGuesses = logic.numLoss; //number of attempts = 6
			info.message = "There are " + logic.getNumLettersInWord() + " letters in word.";
			send(info);
		}
		
		
		/*
		 * Method: evaluates if letter is in word 
		 * 			sends back list of letter positions
		 * 			sends back if player won round 
		 * 			sends number of attempts left
		 * Test: not tested yet if it works 
		 */
		private void continueWord(char letter) {
						
			ArrayList<Integer> position = logic.getPosition(letter);
			
			info = new WordInfo();
			
			if(position.isEmpty()) { 
				info.charInWord = false;
				info.message = "Letter '" + letter + "' is not in the word";
			}
			else { 
				info.charInWord = true;
				info.message = "Letter '" + letter + "' is in the word";
			}
			
			info.letterPositions = position;
			info.numGuesses = logic.numLoss;
			info.lengthOfWord = logic.getNumLettersInWord(); //or do logic.currentWord.length()
			
			send(info);
		}
		
		/*
		 * Method: starts new game with all variables reset
		 */
		public void startNewGame() {
			//logic = new GameLogic();
		}
		
		public void run(){
				
			try {
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);	
			}
			catch(Exception e) {
				System.out.println("Streams not open");
			}
							
			//this is where game logic should be handled 
			 while(true) {
				    try {
				    	WordInfo data = (WordInfo) in.readObject();
				    	
				    	//print message sent by the client
				    	callback.accept("Client " + count + " message: " + data.message);
				    	
				    	//divide reading data into cases:
				    	if(data.category == -1) {
				    		//this means the client is sending a letter to a continuing word
				    		continueWord(data.letterGuess);
				    	}
				    	else {
				    		
				    		//client chose a new category
				    		startNewWord(data.category);
				    	}
				    	
				    	//need an if statement for checking if the client wants to start a new game 
				    	
				    }
					
				    catch(Exception e) {
				    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
				    	clients.remove(this);
				    	break;
				    }
				}
			}
		
		public void send(WordInfo data) {
			
			try {
				out.writeObject(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}


	
	

	
