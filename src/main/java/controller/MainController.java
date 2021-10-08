package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import collisionUtility.CollisionDetection;
import game.engine.GameEngine;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import layout.CustomLayout;
import layout.FormLayouts;
import model.Model;
import sprite.Sprite;

public class MainController {

	private static double orgSceneX, orgSceneY;
	private static double orgTranslateX, orgTranslateY;

	private static CustomLayout customLayout;
	private static Button currentEnabled;

	private static Rectangle2D primScreenBounds;
	private static Stage primaryStage;
	private static Canvas gameCanvas;
	private static Button playBtn;
	private static Button stopBtn;
	
	private static FlowPane gameFlow;
	
	public static Color stageColor;

	private static EventsButtonController eventsController;
	private static final List<Button> BUTTON_LIST = new ArrayList<>();
	private static final String CLICKED_BUTTON_STYLE = "-fx-background-color: lightblue; -fx-font-size:17";
	private static final String NORMAL_BUTTON_STYLE = "-fx-background-color: blue; -fx-font-size:17";
	private static final String PLAY_BUTTON_STYLE = "-fx-font: 24 arial; -fx-base: #00FF00;";
	private static final String STOP_BUTTON_STYLE = "-fx-font: 24 arial; -fx-base: #ee2211;";

	private static final HashMap<String, GridPane> BUTTON_FORM_MAP = new HashMap<>();

	//TODO
	//WHY DOES THE CONTROLLER HAVE ALL OF THIS JAVAFX STUFF?????
	//-Isaiah
	//This system isn't MVC; it's controller,controller,controller-with-a-view-in-it,ArrayList-being-called-a-model
	//We have our work cut out for us 
	
	//literally nothing about this file is a controller, this is a view
	//and a hideous one at that
	public void start(Stage primaryStage) {

		MainController.primaryStage = primaryStage;
		eventsController = new EventsButtonController();
		instantiateButtonFormMap();

		primScreenBounds = Screen.getPrimary().getVisualBounds();

		//gameCanvas = new Canvas(primScreenBounds.getWidth() / 2, primScreenBounds.getHeight());
		drawGameObjects(FormLayouts.makerController.getGameModel().getGameObjects());

		BorderPane pane = new BorderPane();
		
		GridPane playStopGridPane = new GridPane();
		playBtn = new Button("Play");
		stopBtn = new Button("Stop");
		playBtn.setStyle(PLAY_BUTTON_STYLE);
		playBtn.setOnAction(e -> didTapPlayButton());
		stopBtn.setStyle(STOP_BUTTON_STYLE);
		stopBtn.setDisable(true);
		stopBtn.setOnAction(e -> didTapStopButton());
		playStopGridPane.add(playBtn, 0, 1);
		playStopGridPane.add(stopBtn, 0, 2);
		playStopGridPane.setPadding(new Insets(1, 1, 1, 1));
		playStopGridPane.setHgap(10);
		playStopGridPane.setVgap(10);

		gameFlow = new FlowPane();
		gameFlow.setStyle("-fx-background-color:black;");

		gameFlow.setPrefWidth(primScreenBounds.getWidth() / 2);
		gameFlow.setPrefHeight(primScreenBounds.getHeight());

		customLayout = new CustomLayout(30, 10);
		customLayout.addNewChild(createText("MAKER"), 3, 8);
		customLayout.addNewChild(playStopGridPane, 3, 10);
		customLayout.addNewChild(createButton("Sprites"), 1, 14);
		customLayout.addNewChild(createButton("Events"), 3, 14);
		customLayout.addNewChild(createButton("Sounds"), 4, 14);
		customLayout.addNewChild(createButton("Stages"), 5, 14);
		customLayout.addNewChild(createButton("Save"), 6, 14);

		//gameFlow.getChildren().add(gameCanvas);
	
		pane.setLeft(customLayout);
		pane.setRight(gameFlow);

		// Build scene and give it root
		Scene gameScene = new Scene(pane, primScreenBounds.getWidth(), primScreenBounds.getHeight());
		GameEngine.sharedInstance.setGameScene(gameScene);
		CollisionDetection.scene = gameScene;
		primaryStage.setScene(gameScene);
		primaryStage.setTitle("Welcome");
		primaryStage.show();
	}
	
	private static void didTapPlayButton() {
		playBtn.setDisable(true);
		stopBtn.setDisable(false);
		GameEngine.sharedInstance.initiateGameLoop();
	}
	
	public static void changeBackground(String color) {
		gameFlow.setStyle("-fx-background-color:" + color.toLowerCase());
	}
	
	private static void didTapStopButton() {
		playBtn.setDisable(false);
		stopBtn.setDisable(true);
		GameEngine.sharedInstance.stopGameLoop();
	}

	public static void performDraw(List<Sprite> sprites) {
		drawGameObjects(sprites);
	}

	private static void drawGameObjects(List<Sprite> sprites) {
		if (gameFlow != null && gameFlow.getChildren() != null) gameFlow.getChildren().clear();
		
		sprites.forEach(gameObject -> {
			gameObject.performDraw(gameFlow);
		});
	}
	
	public static void redrawAll() {
		fillBackground();
		drawGameObjects(FormLayouts.makerController.getGameModel().getGameObjects());
	}

	public static void fillBackground(Color color) {
		gameFlow.getChildren().clear();
		
//		getGameCanvas().getGraphicsContext2D().setFill(color);
//		getGameCanvas().getGraphicsContext2D().fillRect(0, 0, primScreenBounds.getWidth() / 2, primScreenBounds.getHeight());
	}

	private static Canvas getGameCanvas() {
		return gameCanvas;
	}

	public static void fillBackground() {
		fillBackground(stageColor);
	}

	public static EventsButtonController getEventsController() {
		return eventsController;
	}

	private static void instantiateButtonFormMap() {
		BUTTON_FORM_MAP.put("Sprites", FormLayouts.getShapeFormLayout(getPrimaryStage()));
		BUTTON_FORM_MAP.put("Events", FormLayouts.getEventsFormLayout());
		BUTTON_FORM_MAP.put("Stages", FormLayouts.getStagesFormLayout());
		BUTTON_FORM_MAP.put("Sounds", FormLayouts.getSoundsFormLayout());
	}

	private static GridPane createButton(String actionType) {
		GridPane gridPane = new GridPane();
		Button button = new Button(actionType);
		button.setOnAction(event -> {
			setEnableCurrentButton(button);
			BUTTON_FORM_MAP.put("Sounds", FormLayouts.getSoundsFormLayout());
			customLayout.addNewChildPane(BUTTON_FORM_MAP.get(button.getText()), 0, 15);
		});

		button.setOnMousePressed(buttonMousePressedEventHandler);
		button.setOnMouseDragged(buttonOnMouseDraggedEventHandler);
		button.setStyle(NORMAL_BUTTON_STYLE);
		button.setTextFill(Color.WHITE);
		button.setFocusTraversable(false);

		BUTTON_LIST.add(button);

		gridPane.add(button, 1, 0);

		return gridPane;
	}

	private static void setEnableCurrentButton(Button button) {
		if (currentEnabled != null)
			currentEnabled.setStyle(NORMAL_BUTTON_STYLE);
		currentEnabled = button;
		button.setStyle(CLICKED_BUTTON_STYLE);

	}

	private static GridPane createText(String actionType) {
		GridPane gridPane = new GridPane();
		Text text = new Text();
		text.setText(actionType);
		text.setStyle("-fx-font-size: 24");
		gridPane.setStyle("-fx-background-color: pink;-fx-padding: 10px;\n"
				+ "-fx-background-radius: 10px;\n");

		gridPane.add(text, 1, 0);

		return gridPane;
	}

	static EventHandler<MouseEvent> buttonMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			orgTranslateX = ((Button) (t.getSource())).getTranslateX();
			orgTranslateY = ((Button) (t.getSource())).getTranslateY();

			((Button) (t.getSource())).toFront();
		}
	};

	static EventHandler<MouseEvent> buttonOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((Button) (t.getSource())).setTranslateX(newTranslateX);
			((Button) (t.getSource())).setTranslateY(newTranslateY);
		}
	};

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
}