import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.lang.Object;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextBoundsType;
//import sun.jvm.hotspot.debugger.cdbg.TemplateType;


import javax.swing.*;

import java.awt.*;

public class WordGuessClient extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	Client clientConnection;
	GameLogicClient gameData;
	
	ListView<String> gameState;

	String clientIP;
	int clientPort;
	Label welcomeLabel;
	Label welcomeLabel2;
	Rectangle mainRectangle;
	TextField portText;
	TextField ipText;
	Button startButton;
	Scene scene2;
	HBox mainBox2;
	Label generalCategory;
	Button beachActivities;
	Button iceCreamFlavors;
	Button outdoorSports;

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Client) Word Guess!!!");

		Image bgimage = new Image("background.jpg");
		BackgroundImage backgroundimage = new BackgroundImage(bgimage,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);

		AudioClip bgSound = new AudioClip(this.getClass().getResource("music.mp3").toExternalForm());
		bgSound.play();
		
		gameData = new GameLogicClient();
		gameState = new ListView<String>();

		welcomeLabel = new Label("Welcome to Word Guess!");
		welcomeLabel.setFont(Font.font("Courier", 50));

		welcomeLabel2 = new Label("Summer Edition");
		welcomeLabel2.setFont(Font.font("Zapfino", 50));
		welcomeLabel2.setTextFill(Color.GREEN);

		mainRectangle = new Rectangle(400, 200);
		mainRectangle.setFill(Color.BEIGE);
		mainRectangle.setStroke(Color.ORANGE);

		ipText = new TextField("Enter your IP Address");
		ipText.setFont(Font.font("Courier"));
		ipText.setPrefWidth(190);

		portText = new TextField("Enter your Port Number");
		portText.setFont(Font.font("Courier"));
		portText.setPrefWidth(190);
		portText.setDisable(true);

		startButton = new Button("Start");
		startButton.setFont(Font.font("Courier"));
		startButton.setDisable(true);

		ipText.setOnKeyPressed(new EventHandler<KeyEvent>()  {
			public void handle(KeyEvent keyEvent)
			{
				if(keyEvent.getCode() == KeyCode.ENTER)
				{
					clientIP = ipText.getText();
					ipText.setDisable(true);
					portText.setDisable(false);
					startButton.setDisable(false);
					System.out.println("IP Address: " + clientIP);
				}
			}
		});

		portText.setOnKeyPressed(new EventHandler<KeyEvent>()  {
			public void handle(KeyEvent keyEvent)
			{
				if(keyEvent.getCode() == KeyCode.ENTER)
				{
					clientPort = Integer.parseInt(portText.getText());

					portText.setDisable(true);
					startButton.setDisable(false);
					System.out.println("Port: " + clientPort);
				}
			}
		});

		HBox box1 = new HBox(welcomeLabel, welcomeLabel2, mainRectangle, ipText, portText, startButton);
		box1.setMargin(welcomeLabel, new Insets(100, 10, 10, 250));
		box1.setMargin(welcomeLabel2, new Insets(65, 10, 10, -350));
		box1.setMargin(mainRectangle, new Insets(250, 10, 10, -350));
		box1.setMargin(ipText, new Insets(280, 10, 10, -300));
		box1.setMargin(portText, new Insets(350, 10, 10, -200));
		box1.setMargin(startButton, new Insets(410, 10, 10, -140));

		HBox mainBox = new HBox(box1);

		mainBox.setBackground(background);

		Scene scene1 = new Scene(mainBox,1200,900);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});

		generalCategory = new Label("Pick your Category:");
		generalCategory.setFont(Font.font("Courier", 50));

		beachActivities = new Button("Beach Activities");
		beachActivities.setFont(Font.font("DJB Scruffy Angel", 20));
		beachActivities.setStyle("-fx-text-fill: green");

		iceCreamFlavors = new Button("Ice Cream Flavors");
		iceCreamFlavors.setFont(Font.font("DJB Scruffy Angel", 20));
		iceCreamFlavors.setStyle("-fx-text-fill: blue");

		outdoorSports = new Button("Outdoor Sports");
		outdoorSports.setFont(Font.font("DJB Scruffy Angel", 20));
		outdoorSports.setStyle("-fx-text-fill: purple");

		HBox box2 = new HBox(generalCategory, beachActivities, iceCreamFlavors, outdoorSports);
		box2.setMargin(generalCategory, new Insets(100, 10, 10, 250));
		box2.setMargin(beachActivities, new Insets(200, 10, 10, -400));
		box2.setMargin(iceCreamFlavors, new Insets(346, 10, 10, -10));
		box2.setMargin(outdoorSports, new Insets(500, 10, 10, -10));

		mainBox2 = new HBox(box2);
		mainBox2.setBackground(background);

		scene2 = new Scene(mainBox2, 1200,  900);

		Scene beachScene = new Scene(new HBox(), 1200, 900);

		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				clientConnection = new Client(data->{
					  Platform.runLater(()->{
						  gameState.getItems().add(data.toString());
					  });
				  }, clientPort, clientIP);
				
				clientConnection.start();
				
				primaryStage.setTitle("(Client) Make your Selection!");
				primaryStage.setScene(scene2);
				primaryStage.show();

			}
		});

		primaryStage.setScene(scene1);
		primaryStage.show();
	}

}
