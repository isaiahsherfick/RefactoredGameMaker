package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.event.EventHandler;
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

public class MainController {
	
	private static double orgSceneX, orgSceneY;
	private static double orgTranslateX, orgTranslateY;
	
	private static CustomLayout customLayout;
	private static Button currentEnabled;
	
	private static Stage primaryStage;
	private static Canvas gameCanvas;

	private static final List<Button> BUTTON_LIST = new ArrayList<>();
	private static final String CLICKED_BUTTON_STYLE = "-fx-background-color: lightblue; -fx-font-size:17";
	private static final String NORMAL_BUTTON_STYLE = "-fx-background-color: blue; -fx-font-size:17";
	
	private static final HashMap<String, GridPane> BUTTON_FORM_MAP = new HashMap<>();
    
    public static void start(Stage primaryStage) {

    	MainController.primaryStage = primaryStage;
		EventsButtonController eventsController = new EventsButtonController();
		instantiateButtonFormMap();
		
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		
		gameCanvas = new Canvas(primScreenBounds.getWidth()/2, primScreenBounds.getHeight());

		BorderPane pane = new BorderPane();
		FlowPane gameFlow = new FlowPane();

		customLayout = new CustomLayout(30,10);
		customLayout.addNewChild(createText("Maker"), 3, 8);
//		customLayout.addNewChild(createButton("Sprites"), 0, 14);
		customLayout.addNewChild(createButton("Sprites"), 1, 14);
		customLayout.addNewChild(createButton("Actions"), 2, 14);
		customLayout.addNewChild(createButton("Events"), 3, 14);
		customLayout.addNewChild(createButton("Sounds"), 4, 14);
		customLayout.addNewChild(createButton("Stages"), 5, 14);
		customLayout.addNewChild(createButton("Save"), 6, 14);

		gameFlow.getChildren().add(gameCanvas);
		gameFlow.setStyle("-fx-background-color: black");

		pane.setLeft(customLayout);
		pane.setRight(gameFlow);

		// Build scene and give it root
		Scene gameScene = new Scene(pane, primScreenBounds.getWidth(), primScreenBounds.getHeight());
		
		primaryStage.setScene(gameScene);
		primaryStage.setTitle("Welcome to the Breakout game");
		primaryStage.show();
    
    }
    
    private static void instantiateButtonFormMap() {
    	BUTTON_FORM_MAP.put("Sprites", FormLayouts.getShapeFormLayout(getPrimaryStage()));
    	BUTTON_FORM_MAP.put("Events", FormLayouts.getEventsFormLayout());
    	BUTTON_FORM_MAP.put("Sounds", FormLayouts.getSoundsFormLayout());
	}
    
    private static GridPane createButton(String actionType) {
		GridPane gridPane = new GridPane();
		Button button = new Button(actionType);
		button.setOnAction(event -> {
			setEnableCurrentButton(button);
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
		if (currentEnabled != null) currentEnabled.setStyle(NORMAL_BUTTON_STYLE);
		currentEnabled = button;
		button.setStyle(CLICKED_BUTTON_STYLE);
		
	}

	private static GridPane createText(String actionType) {
		GridPane gridPane = new GridPane();
		Text text = new Text();
		text.setText(actionType);
		text.setStyle("-fx-font-size: 30");
		gridPane.setStyle("-fx-background-color: orange");

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
	
	public static Canvas getGameCanvas() {
		return gameCanvas;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
}