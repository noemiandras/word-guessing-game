import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	private Consumer<Serializable> callback;
	
	int portNum;
	String ipAddr;
	
	Client(Consumer<Serializable> call, int portNum, String ipAddr){
	
		callback = call;
		this.portNum = portNum;
		this.ipAddr = ipAddr;
	}
	
	public void run() {
		
		try {
		socketClient= new Socket(ipAddr,portNum);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}
		
		while(true) {
			 
			try {
			String message = in.readObject().toString();
			callback.accept(message);
			}
			catch(Exception e) {
				e.printStackTrace();
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
