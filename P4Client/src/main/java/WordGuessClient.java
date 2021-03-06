import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
import java.util.concurrent.TimeUnit;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextBoundsType;
//import sun.jvm.hotspot.debugger.cdbg.TemplateType;


import javax.swing.*;

import java.awt.*;

import static javafx.scene.layout.VBox.setMargin;

public class WordGuessClient extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	Client clientConnection;
	GameLogicClient gameData;
	WordInfo info;

	int lengthOfWord = 0;

	ListView<String> gameMessages;

	String clientIP;
	int clientPort;
	Label welcomeLabel;
	Label welcomeLabel2;
	Rectangle mainRectangle;
	TextField portText;
	TextField ipText;
	Button startButton;
	HBox mainBox2;
	Label generalCategory;
	Button beachActivities;
	Button iceCreamFlavors;
	Button outdoorSports;

	Background background;

	Button playAgain = new Button("Play Again");
	Button quit = new Button("Quit");
	Button backButton = new Button("Back");
	Button nextButton;

	String blanks;

	int category1Lost = 0;
	int category2Lost = 0;
	int category3Lost = 0;

	ImageView beachActivitiesHeart1;
	ImageView beachActivitiesHeart2;
	ImageView beachActivitiesHeart3;

	ImageView iceCreamFlavorsHeart1;
	ImageView iceCreamFlavorsHeart2;
	ImageView iceCreamFlavorsHeart3;

	ImageView outdoorSportsHeart1;
	ImageView outdoorSportsHeart2;
	ImageView outdoorSportsHeart3;



	Scene scene1;
	Scene scene2;
	Scene finalScene;

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Client) Word Guess!!!");

		beachActivitiesHeart1 = new ImageView(new Image("heartSmall.png"));
		beachActivitiesHeart2 = new ImageView(new Image("heartSmall.png"));
		beachActivitiesHeart3 = new ImageView(new Image("heartSmall.png"));

		iceCreamFlavorsHeart1 = new ImageView(new Image("heartSmall.png"));
		iceCreamFlavorsHeart2 = new ImageView(new Image("heartSmall.png"));
		iceCreamFlavorsHeart3 = new ImageView(new Image("heartSmall.png"));

		outdoorSportsHeart1 = new ImageView(new Image("heartSmall.png"));
		outdoorSportsHeart2 = new ImageView(new Image("heartSmall.png"));
		outdoorSportsHeart3 = new ImageView(new Image("heartSmall.png"));

		Image bgimage = new Image("background.jpg");
		BackgroundImage backgroundimage = new BackgroundImage(bgimage,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		background = new Background(backgroundimage);

		AudioClip bgSound = new AudioClip(this.getClass().getResource("music.mp3").toExternalForm());
		bgSound.play();

		gameData = new GameLogicClient();
		gameMessages = new ListView<String>();

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

		nextButton = new Button("Next");
		nextButton.setFont(Font.font("Courier", 20));
		nextButton.setDisable(true);

		ipText.setOnKeyPressed(new EventHandler<KeyEvent>()  {
			public void handle(KeyEvent keyEvent)
			{
				if(keyEvent.getCode() == KeyCode.ENTER)
				{
					clientIP = ipText.getText();
					ipText.setDisable(true);
					portText.setDisable(false);
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

		scene1 = new Scene(mainBox,1200,900);

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

		beachActivities.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				//sends category request to server
				info = new WordInfo();
				info.category = 1;
				info.message = "Beach Activities Category picked!";
				clientConnection.send(info);

				lengthOfWord = info.lengthOfWord;

				gameData.category1WordAttempts++;

				if (gameData.category1WordAttempts == 1) {
					beachActivitiesHeart3.setVisible(false);
				}
				else if (gameData.category1WordAttempts == 2) {
					beachActivitiesHeart2.setVisible(false);
				}
				else
				{
					beachActivitiesHeart1.setVisible(false);
					if (gameData.category1WordsCorrect == 0) {
						//display final screen since lost
						primaryStage.setTitle("(Client) Play Again? or Quit?");
						primaryStage.setScene(gameResultScreen());
						primaryStage.show();
					}
				}

				//--------change to scene 3------
				primaryStage.setTitle("(Client) Beach Activities");
				primaryStage.setScene(showScene3());
				primaryStage.show();

				/*
				//disable category button once player already got a word correct from that category
				if (gameData.category1WordsCorrect == 1) {
					beachActivities.setStyle("-fx-background-color: green");
					beachActivities.setDisable(true);
				}
				*/

			}
		});

		iceCreamFlavors.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				//sends category request to server
				info = new WordInfo();
				info.category = 2;
				info.message = "Ice Cream Category picked!";
				clientConnection.send(info);

				lengthOfWord = info.lengthOfWord;

				gameData.category2WordAttempts++;

				if (gameData.category2WordAttempts == 1) {
					iceCreamFlavorsHeart3.setVisible(false);
				}
				else if (gameData.category2WordAttempts == 2) {
					iceCreamFlavorsHeart2.setVisible(false);
				}
				else
				{
					iceCreamFlavorsHeart1.setVisible(false);
					if (gameData.category2WordsCorrect == 0) {
						//display final screen since lost
						primaryStage.setTitle("(Client) Play Again? or Quit?");
						primaryStage.setScene(gameResultScreen());
						primaryStage.show();
					}
				}

				//--------change to scene 3------
				primaryStage.setTitle("(Client) Ice Cream Flavors");
				primaryStage.setScene(showScene3());
				primaryStage.show();

				/*
				//disable category button once player already got a word correct from that category
				if (gameData.category2WordsCorrect == 1) {
					iceCreamFlavors.setStyle("-fx-background-color: green");
					iceCreamFlavors.setDisable(true);
				}
				*/

			}
		});

		outdoorSports.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				//sends category request to server
				info = new WordInfo();
				info.category = 3;
				info.message = "Sports Category picked!";
				clientConnection.send(info);

				lengthOfWord = info.lengthOfWord;

				gameData.category3WordAttempts++;

				if (gameData.category3WordAttempts == 1) {
					outdoorSportsHeart3.setVisible(false);
				}
				else if (gameData.category3WordAttempts == 2) {
					outdoorSportsHeart2.setVisible(false);
				}
				else
				{
					outdoorSportsHeart1.setVisible(false);
					if (gameData.category3WordsCorrect == 0) {
						//display final screen since lost
						primaryStage.setTitle("(Client) Play Again? or Quit?");
						primaryStage.setScene(gameResultScreen());
						primaryStage.show();
					}
				}

				//--------change to scene 3------
				primaryStage.setTitle("(Client) Outdoor Sports");
				primaryStage.setScene(showScene3());
				primaryStage.show();

				/*
				//disable category button once player already got a word correct from that category
				if (gameData.category3WordsCorrect == 1) {
					outdoorSports.setStyle("-fx-background-color: green");
					outdoorSports.setDisable(true);
				}
				*/

			}
		});

		HBox box2 = new HBox(generalCategory, beachActivities, beachActivitiesHeart1, beachActivitiesHeart2, beachActivitiesHeart3,
				iceCreamFlavors, iceCreamFlavorsHeart1, iceCreamFlavorsHeart2, iceCreamFlavorsHeart3,
				outdoorSports, outdoorSportsHeart1, outdoorSportsHeart2, outdoorSportsHeart3, nextButton);

		box2.setMargin(generalCategory, new Insets(100, 10, 10, 250));

		box2.setMargin(beachActivities, new Insets(200, 10, 10, -400));
		box2.setMargin(beachActivitiesHeart1, new Insets(250, 0, 10, -180));
		box2.setMargin(beachActivitiesHeart2, new Insets(250, 5, 10, 10));
		box2.setMargin(beachActivitiesHeart3, new Insets(250, 10, 10, 10));

		box2.setMargin(iceCreamFlavors, new Insets(346, 10, 10, -2));
		box2.setMargin(iceCreamFlavorsHeart1, new Insets(396, 0, 10, -180));
		box2.setMargin(iceCreamFlavorsHeart2, new Insets(396, 5, 10, 10));
		box2.setMargin(iceCreamFlavorsHeart3, new Insets(396, 10, 10, 10));

		box2.setMargin(outdoorSports, new Insets(500, 10, 10, -4));
		box2.setMargin(outdoorSportsHeart1, new Insets(550, 0, 10, -180));
		box2.setMargin(outdoorSportsHeart2, new Insets(550, 5, 10, 10));
		box2.setMargin(outdoorSportsHeart3, new Insets(550, 10, 10, 10));

		box2.setMargin(nextButton, new Insets(600, 10, 10, -100));

		mainBox2 = new HBox(box2);
		mainBox2.setBackground(background);

		scene2 = new Scene(mainBox2, 1200,  900);

		//beachScene = new Scene(new HBox(), 1200, 900);

		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				clientConnection = new Client(data->{
					Platform.runLater(()->{
						gameMessages.getItems().add(data.toString());
					});
				}, clientPort, clientIP);

				clientConnection.start();


				primaryStage.setTitle("(Client) Make your Selection!");
				primaryStage.setScene(scene2);
				primaryStage.show();

			}
		});


		playAgain.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				primaryStage.setTitle("(Client) Make your Selection!");
				primaryStage.setScene(scene2);
				primaryStage.show();

				gameData = new GameLogicClient();

				beachActivitiesHeart1.setVisible(true);
				beachActivitiesHeart2.setVisible(true);
				beachActivitiesHeart3.setVisible(true);
				iceCreamFlavorsHeart1.setVisible(true);
				iceCreamFlavorsHeart2.setVisible(true);
				iceCreamFlavorsHeart3.setVisible(true);
				outdoorSportsHeart1.setVisible(true);
				outdoorSportsHeart2.setVisible(true);
				outdoorSportsHeart3.setVisible(true);

				nextButton.setDisable(true);

				beachActivities.setDisable(false);

				iceCreamFlavors.setDisable(false);

				outdoorSports.setDisable(false);

				info.message = "Starting a New Game";
				info.startNewGame = true;
				clientConnection.send(info);

			}
		});

		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Stage stage = (Stage) quit.getScene().getWindow();
				//stage.close();
				Platform.exit();
				System.exit(0);
			}
		});

		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if ((gameData.category1WordsCorrect == 1) && (gameData.category2WordsCorrect == 1) && (gameData.category3WordsCorrect == 1)) {
					nextButton.setDisable(false);
				}

				primaryStage.setTitle("(Client) Make your Selection!");
				primaryStage.setScene(scene2);
				primaryStage.show();
			}
		});

		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setTitle("(Client) Play Again? or Quit?");
				primaryStage.setScene(gameResultScreen());
				primaryStage.show();
			}
		});


		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	public Scene gameResultScreen() {

		Text resultMessage = new Text();

		resultMessage.setFont(Font.font("DJB Scruffy Angel", FontWeight.BOLD, FontPosture.REGULAR, 50));
		resultMessage.setStyle("-fx-fill: blue; -fx-padding: 50 0 0 0;");

		if (gameData.didWin()) {
			resultMessage.setText("Yayy! You Win!");
		}
		else if (gameData.didLose()) {
			resultMessage.setText("Oh No! You Lost! Maybe next time...");
		}

		//playAgain = new Button("Play Again");
		playAgain.setFont(Font.font("DJB Scruffy Angel", 20));
		playAgain.setStyle("-fx-text-fill: blue");
		playAgain.setMinSize(150, 100);

		//quit = new Button("Quit");
		quit.setFont(Font.font("DJB Scruffy Angel", 20));
		quit.setStyle("-fx-text-fill: blue");
		quit.setMinSize(150, 100);

		HBox endBtns = new HBox(playAgain, quit);
		endBtns.setAlignment(Pos.CENTER);

		//endBtns.setMargin(resultMessage, new Insets(350, 10, 10, 10));
		endBtns.setMargin(playAgain, new Insets(350, 20, 10, 10));
		endBtns.setMargin(quit, new Insets(350, 10, 10, 20));

		VBox endDisplay = new VBox(resultMessage, endBtns);
		endDisplay.setAlignment(Pos.CENTER);

		VBox endDisplayBox = new VBox(endDisplay);
		endDisplayBox.setBackground(background);

		finalScene = new Scene(endDisplayBox, 1200, 900);

		return finalScene;
	}

	public Scene showScene3() {
		// ------- SCENE 3 ------------------------------------------------------------------------------------------

		Label numGuesses = new Label("Guesses: ");
		numGuesses.setFont(Font.font("Courier", 20));

		ImageView guessHeart1 = new ImageView(new Image("heartSmall.png"));
		ImageView guessHeart2 = new ImageView(new Image("heartSmall.png"));
		ImageView guessHeart3 = new ImageView(new Image("heartSmall.png"));
		ImageView guessHeart4 = new ImageView(new Image("heartSmall.png"));
		ImageView guessHeart5 = new ImageView(new Image("heartSmall.png"));
		ImageView guessHeart6 = new ImageView(new Image("heartSmall.png"));

		guessHeart6.setVisible(true);
		guessHeart5.setVisible(true);
		guessHeart4.setVisible(true);
		guessHeart3.setVisible(true);
		guessHeart2.setVisible(true);
		guessHeart1.setVisible(true);

		Rectangle rectangle3 = new Rectangle(900, 500);
		rectangle3.setFill(Color.BEIGE);
		rectangle3.setStroke(Color.ORANGE);

		String clientCategory = "";

		if (info.category == 1) {
			clientCategory = "Beach Activities";
		}
		else if (info.category == 2) {
			clientCategory = "Ice Cream Flavors";
		}
		else if (info.category == 3) {
			clientCategory = "Outdoor Sports";
		}

		backButton.setFont(Font.font("Courier", 20));
		backButton.setDisable(true);

		Label categoryLabel = new Label("Category: ");
		categoryLabel.setFont(Font.font("Courier", 20));

		Label categoryPick = new Label(clientCategory);
		categoryPick.setFont(Font.font("DJB Scruffy Angel", 20));
		categoryPick.setTextFill(Color.GREEN);

		Rectangle blanksRectangle = new Rectangle(700, 200);
		blanksRectangle.setFill(Color.WHITE);
		blanksRectangle.setStroke(Color.LIGHTCORAL);

		Label guessLabel = new Label("Input your guess: ");
		guessLabel.setFont(Font.font("Courier", 20));

		TextField guess = new TextField();
		guess.setPrefWidth(100);
		guess.setFont(Font.font("DJB Scruffy Angel", 20));

		Button guessSend = new Button("Send");
		guessSend.setFont(Font.font("DJB Scruffy Angel", 20));

		blanks = "";

		for (int i = 0; i < clientConnection.gameState.lengthOfWord; i++) {
			blanks += "-";
		}

		System.out.println("Length of word: " + clientConnection.gameState.lengthOfWord);
		System.out.println(blanks);

		Label word = new Label(blanks);
		word.setFont(Font.font("Courier", 30));

		guessSend.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Alert lengthAlert = new Alert(Alert.AlertType.ERROR);
				lengthAlert.setTitle("Error!");
				lengthAlert.setHeaderText("Guess Length Error");
				lengthAlert.setContentText("You must guess one letter!");

				if ((guess.getText().length() > 1) || (guess.getText().isEmpty())){
					lengthAlert.showAndWait();
				}
				else {
					WordInfo newInfo = new WordInfo();
					newInfo.letterGuess = guess.getText().charAt(0);

					clientConnection.send(newInfo);

//					info.letterGuess = guess.getText().charAt(0);
					guess.clear();

					//----------------------noemi
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//-------------------------

					System.out.println("Client guessed: " + newInfo.letterGuess);

					if (clientConnection.gameState.charInWord) { // letter guessed is in word!
						System.out.println("Letter is in word!");

						ArrayList<Integer> positions = clientConnection.gameState.letterPositions;

						for (int i = 0; i < positions.size(); i++) {
							StringBuilder blankWord = new StringBuilder(blanks);
							System.out.println("positions: " + positions.get(i));
							blankWord.setCharAt(positions.get(i), newInfo.letterGuess);
							blanks = blankWord.toString();
						}

						int blankCount = 0;

						for (int i = 0; i < blanks.length(); i++) {
							if (blanks.charAt(i) == '-') {
								blankCount++;
							}
						}

						if (blankCount == 0) { // client guessed the whole word!
							Alert winAlert = new Alert(Alert.AlertType.CONFIRMATION);
							winAlert.setTitle("Congrats!");
							winAlert.setHeaderText("Congratulations");
							winAlert.setContentText("You guessed the whole word!");

							if (info.category == 1) {
								gameData.category1WordAttempts++;
								gameData.category1WordsCorrect++;
								beachActivities.setDisable(true);
							}
							else if (info.category == 2) {
								gameData.category2WordAttempts++;
								gameData.category2WordsCorrect++;
								iceCreamFlavors.setDisable(true);
							}
							else {
								gameData.category3WordAttempts++;
								gameData.category3WordsCorrect++;
								outdoorSports.setDisable(true);
							}

							guess.setDisable(true);
							guessSend.setDisable(true);

							backButton.setDisable(false);

							winAlert.showAndWait();

						}

						word.setText(blanks);

						System.out.println(blanks);

						System.out.println("finished here!");

					}
					else  { // letter is not in word

						info.numGuesses++;

						System.out.println("Client has made " + info.numGuesses + " guesses");

						if (info.numGuesses == 1) {
							System.out.println("Got here!");
							guessHeart6.setVisible(false);
						}
						else if (info.numGuesses == 2) {
							guessHeart6.setVisible(false);
							guessHeart5.setVisible(false);
						}
						else if (info.numGuesses == 3) {
							guessHeart6.setVisible(false);
							guessHeart5.setVisible(false);
							guessHeart4.setVisible(false);
						}
						else if (info.numGuesses == 4) {
							guessHeart6.setVisible(false);
							guessHeart5.setVisible(false);
							guessHeart4.setVisible(false);
							guessHeart3.setVisible(false);
						}
						else if (info.numGuesses == 5) {
							guessHeart6.setVisible(false);
							guessHeart5.setVisible(false);
							guessHeart4.setVisible(false);
							guessHeart3.setVisible(false);
							guessHeart2.setVisible(false);
						}
						else if (info.numGuesses == 6) {
							guessHeart6.setVisible(false);
							guessHeart5.setVisible(false);
							guessHeart4.setVisible(false);
							guessHeart3.setVisible(false);
							guessHeart2.setVisible(false);
							guessHeart1.setVisible(false);

							// all guesses have been used up!

							Alert loseAlert = new Alert(Alert.AlertType.ERROR);
							loseAlert.setTitle("Oh no!");
							loseAlert.setHeaderText("You lose...");
							loseAlert.setContentText("All 6 guesses have been used...");

							if (info.category == 1) {
								gameData.category1WordAttempts++;
								category1Lost++;
							}
							else if (info.category == 2) {
								gameData.category2WordAttempts++;
								category2Lost++;
							}
							else {
								gameData.category3WordAttempts++;
								category3Lost++;
							}

							System.out.println("#1 attempts: " + category1Lost);
							System.out.println("#2 attempts: " + category2Lost);
							System.out.println("#3 attempts: " + category3Lost);

							if ((category1Lost >= 3) || (category2Lost >= 3) || (category3Lost >= 3)) {

								if (category1Lost >= 3) {
									beachActivitiesHeart1.setVisible(false);
									beachActivitiesHeart2.setVisible(false);
									beachActivitiesHeart3.setVisible(false);
								}
								else if (category2Lost >= 3) {
									iceCreamFlavorsHeart1.setVisible(false);
									iceCreamFlavorsHeart2.setVisible(false);
									iceCreamFlavorsHeart3.setVisible(false);
								}
								else {
									outdoorSportsHeart1.setVisible(false);
									outdoorSportsHeart2.setVisible(false);
									outdoorSportsHeart3.setVisible(false);
								}

								beachActivities.setDisable(true);
								iceCreamFlavors.setDisable(true);
								outdoorSports.setDisable(true);
								nextButton.setDisable(false);

							}

							backButton.setDisable(false);

							guess.setDisable(true);
							guessSend.setDisable(true);
							loseAlert.showAndWait();

						}
					}
				}
			}
		});

		HBox rectangle = new HBox(blanksRectangle);

		HBox box3 = new HBox(rectangle3, categoryLabel, categoryPick, rectangle, guessLabel, guess, guessSend, numGuesses, guessHeart1, guessHeart2, guessHeart3, guessHeart4, guessHeart5, guessHeart6, word, backButton);
		box3.setMargin(rectangle3, new Insets(100, 20, 10, 175));
		box3.setMargin(categoryLabel, new Insets(150, 20, 10, -800));
		box3.setMargin(categoryPick, new Insets(130, 20, 10, 0));
		box3.setMargin(rectangle, new Insets(200, 20, 10, -380));
		box3.setMargin(guessLabel, new Insets(450, 20, 10, -700));
		box3.setMargin(guess, new Insets(450, 20, 10, 0));
		box3.setMargin(guessSend, new Insets(450, 20, 10, 10));
		box3.setMargin(numGuesses, new Insets(40, 20, 10, -700));
		box3.setMargin(guessHeart1, new Insets(30, 20, 10, 0));
		box3.setMargin(guessHeart2, new Insets(30, 20, 10, -10));
		box3.setMargin(guessHeart3, new Insets(30, 20, 10, -10));
		box3.setMargin(guessHeart4, new Insets(30, 20, 10, -10));
		box3.setMargin(guessHeart5, new Insets(30, 20, 10, -10));
		box3.setMargin(guessHeart6, new Insets(30, 20, 10, -10));
		box3.setMargin(word, new Insets(300, 20, 10, -250));
		box3.setMargin(backButton, new Insets(300, 20, 10, 400));

		HBox mainBox3 = new HBox(box3);
		mainBox3.setBackground(background);

		Scene scene3 = new Scene(mainBox3, 1200, 900);

		return scene3;

		// ----------------------------------------------------------------------------------------------------------
	}


}