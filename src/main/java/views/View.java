//@Author Christian Dummer
package views;

import java.io.IOException;
import java.util.ArrayList;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sprite.Sprite;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text; 

public class View implements Observer
{

	
		private Scene makerScene;
		private Stage makerStage;
		private Scene playerScene;
		private Stage playerStage;
		private Controller controller;

		//Displays both views, called by Main.java when program is launched.
		public View(Stage primaryStage) {
			try {
				//Loads and shows the makerView
				makerStage = primaryStage;
				makerStage.setTitle("Maker View");
				FXMLLoader windowLoader = new FXMLLoader();
				windowLoader.setLocation(View.class.getResource("MakerView.fxml"));
				windowLoader.setController(this);
				AnchorPane makerLayout = (AnchorPane)windowLoader.load();
				makerScene = new Scene(makerLayout);
				makerStage.setScene(makerScene);
				makerStage.setX(300);
				makerStage.setY(50);
				
				//Loads and shows the playerView
				playerStage = new Stage();
				playerStage.setTitle("Player View");
				FXMLLoader playerWindowLoader = new FXMLLoader();
				playerWindowLoader.setLocation(View.class.getResource("PlayerView.fxml"));
				playerWindowLoader.setController(this);
				AnchorPane playerLayout = (AnchorPane)playerWindowLoader.load();
				playerScene = new Scene(playerLayout);
				playerStage.setScene(playerScene);
				playerStage.setX(905);
				playerStage.setY(50);
				//gameCanvas = new Canvas(600,824);
				//playerLayout.getChildren().add(gameCanvas);
			}
			catch(IOException ex) {
				System.out.println("In View.java constructor: " + ex);
			}
		}
		
		//Just for unit tests
		public View()
		{
			gameCanvas = new Canvas();
		}
		
		public void showMaker()
		{
			makerStage.show();
		}
		
		public void showPlayer()
		{
			playerStage.show();
		}
		
		public void showStages()
		{
			showMaker();
			showPlayer();
		}
		
		public void setController(Controller c) {
			this.controller = c;
		}
	
		
		public Scene getPlayerScene() {
			return this.playerScene;
		}
		
		public Scene getMakerScene() {
			return this.makerScene;
		}
		
		public Stage getPlayerStage() {
			return this.playerStage;
		}
		
		public Stage getMakerStage() {
			return this.makerStage;
		}
		
		//Controls for elements in PlayerView.fxml
		@FXML
		private Button playStopButton;
		@FXML
		private Button undoPauseButton;
		@FXML
		private Button redoRestartButton;
		@FXML
		private Button saveButton;
		@FXML
		private Button loadButton;
		@FXML 
		private Canvas gameCanvas;

		// Event Listener on Button[#playStopButton].onAction
		@FXML
		public void playStopButtonClicked(ActionEvent event) {
			// TODO 
		}
		// Event Listener on Button[#undoPauseButton].onAction
		@FXML
		public void undoPauseButtonClicked(ActionEvent event) 
		{
			controller.undo();
		}
		// Event Listener on Button[#redoRestartButton].onAction
		@FXML
		public void redoRestartButtonClicked(ActionEvent event) {
			// TODO
		}
		// Event Listener on Button[#saveButton].onAction
		@FXML
		public void saveButtonClicked(ActionEvent event) {
			// TODO 
		}
		// Event Listener on Button[#loadButton].onAction
		@FXML
		public void loadButtonClicked(ActionEvent event) {
			// TODO 
		}
		
		//Controls for elements in MakerView.fxml
	    @FXML
	    private Button addGamePropertyButton;

	    @FXML
	    private ChoiceBox<?> collisionAction;

	    @FXML
	    private ChoiceBox<?> collisionObjectType;

	    @FXML
	    private Button createNewSpriteButton;

	    @FXML
	    private Button duplicateSpriteButton;

	    @FXML
	    private ChoiceBox<?> keyBehaviorAction;

	    @FXML
	    private Text keyBehaviorKeyInput;

	    @FXML
	    private Pane keyPressedBehaviorPane;

	    @FXML
	    private AnchorPane makerPane;

	    @FXML
	    private Pane mouseEventBehaviorPane;

	    @FXML
	    private Button newBehaviorButton;

	    @FXML
	    private Button newCollisionBehaviorButton;

	    @FXML
	    private Button removeGamePropertyButton;

	    @FXML
	    private AnchorPane spriteBehaviorEditPane;

	    @FXML
	    private AnchorPane spriteBehaviorEditPane1;

	    @FXML
	    private ScrollPane spriteBehaviorList;

	    @FXML
	    private ScrollPane spriteBehaviorList1;

	    @FXML
	    private ChoiceBox<?> spriteBehaviorType;

	    @FXML
	    private Button spriteChooseImageButton;

	    @FXML
	    private ChoiceBox<?> spriteCollisionType;

	    @FXML
	    private ColorPicker spriteColorPicker;

	    @FXML
	    private Slider spriteHeightSlider;

	    @FXML
	    private Label spriteIdLabel;

	    @FXML
	    private ComboBox<?> spriteShapeDropdown;

	    @FXML
	    private Slider spriteWidthSlider;

	    @FXML
	    private Label spriteXLabel;

	    @FXML
	    private Label spriteYLabel;

	    @FXML
	    private ChoiceBox<?> timeBehaviorAction;

	    @FXML
	    private CheckBox timeBehaviorContinousCheck;

	    @FXML
	    private Text timeBehaviorIntervalInput;

	    @FXML
	    private Pane timeBehaviorPane;

	    @FXML
	    public void addBehaviorButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    public void addCollisionBehaviorButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    public void addGamePropertyButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    public void backgroundOptionSelected(ActionEvent event) {

	    }

	    
	    @FXML
	    //@author Ramya 
	    // Requests the controller to add new sprite 
	    public void createSpriteButtonClicked(ActionEvent event) 
	    {
	    	 controller.createSprite();
	    }

	    @FXML
	    public void duplicateSpriteButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    public void heightSliderChanged(DragEvent event) {

	    }

	    @FXML
	    public void removeGamePropertyButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    public void spriteAppearanceSelected(ActionEvent event) {

	    }

	    @FXML
	    public void spriteColorSelected(ActionEvent event) {

	    }

	    @FXML
	    public void usesLevelsSelected(ActionEvent event) {
	    	
	    }

	    @FXML
	    public void usesWallsSelected(ActionEvent event) {

	    }

	    @FXML
	    public void widthSliderChanged(DragEvent event) {

	    }
	    
	    public void drawAll()
	    {
	    	gameCanvas.getGraphicsContext2D().setFill(Color.WHITE);
	    	gameCanvas.getGraphicsContext2D().fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
	    	ArrayList<Sprite> allSprites = controller.getSpriteList();
	    	for (Sprite s : allSprites)
	    	{
	    		s.draw(gameCanvas.getGraphicsContext2D());
	    	}
	    }

		@Override
		public void update() 
		{
			drawAll();
		}

	

		
}

