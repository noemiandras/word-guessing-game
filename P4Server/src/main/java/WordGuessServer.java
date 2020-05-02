/*AUTHOR: Noemi Andras
 * PROJECT: CS 342 Hangman
 * DESCRIPTION: This is the server side of the hangman project where all 
 * 				clients who are playing hangman are handled. 
 */



import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WordGuessServer extends Application {

	Text text1; 
	Text text2;
	TextField textField;
	VBox vBoxMenu;
	HBox hBoxStartServer;
	Button bStartServer;
	
	//server-client-socket related objects
	Server serverConnection;
	ListView<String> listItems; //this is where all the string server-client communication will happen
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(server) Playing word guess!!!");
		
		bStartServer = new Button("Start Server");
		text1 = new Text();
		text2 = new Text();
		textField = new TextField();
		listItems = new ListView<String>(); //for debugging purposes in this scene
		
		text1.setText("Welcome to Word Guess!");
		text2.setText("Please enter port number in between 5000 and 6000 to start:");
		
		hBoxStartServer = new HBox(textField, bStartServer );
		vBoxMenu = new VBox(20, text1, text2, hBoxStartServer, listItems);
		
		//start server
		this.bStartServer.setOnAction(e->{ int port = Integer.parseInt(textField.getText());
											System.out.println(port);
																				
											serverConnection = new Server(port);
											System.out.println("Serverconnection created");
											
											//sets up callBack so that information is sent through runnable
											serverConnection.setCallBack(data-> {
												Platform.runLater(() -> {
													listItems.getItems().add(data.toString());
												});						  
									});
		});
		
		Scene scene = new Scene(vBoxMenu,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//needed for when the window is closed
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
	}

}
