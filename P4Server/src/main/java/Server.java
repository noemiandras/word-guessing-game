/* AUTHOR: Noemi Andras
 * PROJECT: CS 342 Hangman
 * DESCRIPTION: this file handles all the server and separate client threads 
 */

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
		
		ClientThread(Socket s, int count){
			this.connection = s;
			this.count = count;	
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
				    	//modify to taking in serializable object
				    	String data = in.readObject().toString();
				    	
				    	}
					
				    catch(Exception e) {
				    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
				    	clients.remove(this);
				    	break;
				    }
				}
			}
		
		
	}
}


	
	

	
