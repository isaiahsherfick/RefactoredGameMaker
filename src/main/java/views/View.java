//@Author Christian Dummer
package views;

import java.io.IOException;

import controller.Controller;
import game.engine.GameEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
import javafx.scene.text.Text; 

public class View {

	
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
				makerStage.show();
				
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
				playerStage.show();	
			}
			catch(IOException ex) {
				System.out.println("In View.java constructor: " + ex);
			}
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

		// Event Listener on Button[#playStopButton].onAction
		@FXML
		public void playStopButtonClicked(ActionEvent event) {
			// TODO Autogenerated
		}
		// Event Listener on Button[#undoPauseButton].onAction
		@FXML
		public void undoPauseButtonClicked(ActionEvent event) {
			// TODO Autogenerated
		}
		// Event Listener on Button[#redoRestartButton].onAction
		@FXML
		public void redoRestartButtonClicked(ActionEvent event) {
			// TODO Autogenerated
		}
		// Event Listener on Button[#saveButton].onAction
		@FXML
		public void saveButtonClicked(ActionEvent event) {
			// TODO Autogenerated
		}
		// Event Listener on Button[#loadButton].onAction
		@FXML
		public void loadButtonClicked(ActionEvent event) {
			// TODO Autogenerated
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
	    void addBehaviorButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    void addCollisionBehaviorButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    void addGamePropertyButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    void backgroundOptionSelected(ActionEvent event) {

	    }

	    
	    @FXML
	    //@author Ramya 
	    // Requests the controller to add new sprite 
	    void createSpriteButtonClicked(ActionEvent event) 
	    {
	 
	    	 controller.createSprite();

	    }

	    @FXML
	    void duplicateSpriteButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    void heightSliderChanged(DragEvent event) {

	    }

	    @FXML
	    void removeGamePropertyButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    void spriteAppearanceSelected(ActionEvent event) {

	    }

	    @FXML
	    void spriteColorSelected(ActionEvent event) {

	    }

	    @FXML
	    void usesLevelsSelected(ActionEvent event) {

	    }

	    @FXML
	    void usesWallsSelected(ActionEvent event) {

	    }

	    @FXML
	    void widthSliderChanged(DragEvent event) {

	    }

	

		
}
