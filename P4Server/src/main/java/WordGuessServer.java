/*AUTHOR: Noemi Andras
		  Ameesha Saxena
 * PROJECT: CS 342 Hangman
 * DESCRIPTION: This is the server side of the hangman project where all 
 * 				clients who are playing hangman are handled. 
 */



import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.HashMap;

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
	HashMap<String, Scene> sceneMap = new HashMap<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Word Guess Start Server");
		
		bStartServer = new Button("Start Server");
		bStartServer.setStyle("-fx-font: 14px 'Times New Roman'");
		text1 = new Text();
		text2 = new Text();
		textField = new TextField();
		listItems = new ListView<String>(); //for debugging purposes in this scene
		sceneMap.put("GameScene", CreateGameScene(primaryStage, listItems));

		text1.setText("Welcome to Word Guess Game!");
		text1.setStyle("-fx-font: 18px 'Times New Roman';" + "-fx-font-weight: bold;");
		text1.setText("Welcome to Word Guess!");
		text2.setText("Please enter port number in between 5000 and 6000 to start:");
		text2.setStyle("-fx-font: 18px 'Times New Roman';" + "-fx-font-weight: bold;");
		
		hBoxStartServer = new HBox(textField, bStartServer );
		vBoxMenu = new VBox(20, text1, text2, hBoxStartServer);// , listItems);
		vBoxMenu.setStyle("-fx-padding: 60;"+"-fx-background-color: #ffe57c;");
		
		//start server
		this.bStartServer.setOnAction(e->{ int port = Integer.parseInt(textField.getText());
											System.out.println(port);
																				
											serverConnection = new Server(port);
											System.out.println("ServerConnection created");
											
											//sets up callBack so that information is sent through runnable
											serverConnection.setCallBack(data-> {
												Platform.runLater(() -> {
													listItems.getItems().add(data.toString());
												});						  
									});

											primaryStage.setScene(sceneMap.get("GameScene"));
		});

		//pane.setPadding(new Insets(70));
		Scene scene = new Scene(vBoxMenu,600,600);
		sceneMap.put("StartServer", scene);
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


	public Scene CreateGameScene(Stage primaryStage, ListView listItems){
		primaryStage.setTitle("Word Guess Server Screen");

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-font: 14px 'Times New Roman';" + "-fx-background-color: #ffe57c;");
		//pane.setStyle("-fx-background-color: #ffe57c");

		//HBox clientData = new HBox(20, clientNumbers, clients);

		pane.setCenter(listItems);

		pane.setPadding(new Insets(70));

		return new Scene(pane, 500, 400);

	}

}
