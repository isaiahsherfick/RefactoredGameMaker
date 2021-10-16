//@Author Christian Dummer
package views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import controller.Controller;
import input.KeyPolling;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saveandload.SaveableRectangle;
import saveandload.SaveableShape;
import sprite.Sprite;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text; 

public class View implements Observer
{

	
		private Scene makerScene;
		private Stage makerStage;
		private Scene playerScene;
		private Stage playerStage;
		private Controller controller;
		private Sprite currentlySelectedSprite;

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
		
		public void initializeUIElements() {
			
			//Initializes slider layouts since there is no ChangeListener in fxml
			spriteWidthSlider.valueProperty().addListener(new ChangeListener<Object>() {
				@Override
				public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
					widthSliderChanged();
				}
			});
			spriteHeightSlider.valueProperty().addListener(new ChangeListener<Object>() {

				@Override
				public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
					heightSliderChanged();
					
				}
				
			});
			
			//Adds items to the possible shapes dropdown
			spriteShapeDropdown.getItems().add(new SaveableRectangle());
			
			spriteBehaviorTypeDropdown.getItems().add("On Click Behavior");
			spriteBehaviorTypeDropdown.getItems().add("On Key Press Behavior");
			spriteBehaviorTypeDropdown.getItems().add("Timed Behavior");
		}
		
		public void showMaker()
		{
			makerStage.show();
			initializeUIElements();
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
			if(playStopButton.getText().equals("Play")) {
				//If play is pressed, switch buttons to Play Context
				playStopButton.setText("Stop");
				undoPauseButton.setText("Pause");
				redoRestartButton.setText("Restart");
				saveButton.setVisible(false);
				saveButton.setDisable(true);
				loadButton.setVisible(false);
				loadButton.setDisable(true);
				controller.play();
			}
			else if(playStopButton.getText().equals("Stop")) {
				//If Stop is pressed, switch buttons back to maker context
				playStopButton.setText("Play");
				undoPauseButton.setText("Undo");
				redoRestartButton.setText("Redo");
				saveButton.setVisible(true);
				saveButton.setDisable(false);
				loadButton.setVisible(true);
				loadButton.setDisable(false);
				//TODO leave play
			}
		}
		
		
		
		// Event Listener on Button[#undoPauseButton].onAction
		@FXML
		public void undoPauseButtonClicked(ActionEvent event) 
		{
			if(undoPauseButton.getText().equals("Undo")) {
				controller.undo();
			}
			else if(undoPauseButton.getText().equals("Pause")) {
				controller.pause();
				undoPauseButton.setText("Resume");
			}
			else if(undoPauseButton.getText().equals("Resume")) {
				controller.resume();
				undoPauseButton.setText("Pause");
			}
			
		}
		// Event Listener on Button[#redoRestartButton].onAction
		@FXML
		public void redoRestartButtonClicked(ActionEvent event) {
			if(redoRestartButton.getText().equals("Redo")) {
				//TODO redo is a stretch goal
			}
			else if(redoRestartButton.getText().equals("Restart")) {
				//TODO restart
			}
		}
		// Event Listener on Button[#saveButton].onAction
		@FXML
		public void saveButtonClicked(ActionEvent event) {
			controller.save();
		}
		// Event Listener on Button[#loadButton].onAction
		@FXML
		public void loadButtonClicked(ActionEvent event) {
			controller.load();
			currentlySelectedSprite  = controller.getSpriteList().get(controller.getSpriteList().size() - 1);
		}
		
		//On the canvas clicked, check to see if the click intersects with a sprite's hitbox, and if so make it the currently
		//selected Sprite.
		@FXML
		public void canvasClicked(MouseEvent event) {
			double clickedX = event.getX();
			double clickedY = event.getY();
			for(Sprite s: controller.getSpriteList()) {
				if(clickedX >= s.getHitBox().getTopLeft().getX() && clickedY>= s.getHitBox().getTopLeft().getY()) {
					if(clickedX <= s.getHitBox().getBottomRight().getX() && clickedY <= s.getHitBox().getBottomRight().getY()) {
						//If click is within the hitbox, then make it currently selected sprite and adjust the properties pane;
						currentlySelectedSprite = s;
						setSpritePropertiesPane();
					}
				}
			}
		}
		
		//When the canvas is dragged, get the sprite and adjust it's x/y
		@FXML 
		public void canvasDragged(MouseEvent event) {
			//TODO This is inefficient, but without a check for the new currently selected sprite it bugs out
			canvasClicked(event);
			
			//Get the events x/y and set it to the sprite
			double newX = event.getX() - (currentlySelectedSprite.getAppearance().getWidth() * .5);
			double newY = event.getY() - (currentlySelectedSprite.getAppearance().getHeight() * .5);
			currentlySelectedSprite.setX(newX);
			currentlySelectedSprite.setY(newY);
			modifySpriteCommand();
		}
		
		//Sets the Sprite Properties pane values to the values of CurrentlySelectedSprite
		public void setSpritePropertiesPane() {
			//Set all labels to corresponding value of the sprite
			spriteIdLabel.setText("" + currentlySelectedSprite.getSpriteId());
			spriteXLabel.setText("" + currentlySelectedSprite.getX());
			spriteYLabel.setText("" + currentlySelectedSprite.getY());
			
			//Set the color selector
			spriteColorPicker.setValue(currentlySelectedSprite.getAppearance().getColor());
			
			//Set width and height slider values
			spriteWidthSlider.setValue(currentlySelectedSprite.getAppearance().getWidth());
			spriteHeightSlider.setValue(currentlySelectedSprite.getAppearance().getHeight());
			
			
		}
		
		//Controls for elements in MakerView.fxml
		//Root Anchor Pane
	    @FXML
	    private AnchorPane makerPane;
	 
	    //Sprite Behavior tab fields

	    @FXML
	    private AnchorPane spriteBehaviorEditPane;

	    @FXML
	    private AnchorPane spriteBehaviorEditPane1;

	    @FXML
	    private ScrollPane spriteBehaviorList;

	    @FXML
	    private ComboBox<String> spriteBehaviorTypeDropdown;

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
	    private ComboBox<SaveableShape> spriteShapeDropdown;

	    @FXML
	    private Slider spriteWidthSlider;

	    @FXML
	    private Label spriteXLabel;

	    @FXML
	    private Label spriteYLabel;
	  
	    @FXML
	    private Button createNewSpriteButton;

	    @FXML
	    private Button duplicateSpriteButton;

	    
	    //Fields for the timed behavior pane
	    @FXML
	    private AnchorPane timeBehaviorPane;
	 
	    @FXML 
	    private TextField intervalField;
	    
	    @FXML 
	    private CheckBox continuousInterval;
	    
	    @FXML
	    private ChoiceBox<?> timeBehaviorAction;

	    @FXML
	    private CheckBox timeBehaviorContinousCheck;

	    @FXML
	    private Text timeBehaviorIntervalInput;
	    
	    @FXML
	    private Button addTimedBehaviorButton;
	    
	    //Fields for the mouse behavior pane
	    @FXML
	    private AnchorPane mouseBehaviorPane;
	    
	    @FXML
	    private ChoiceBox<?> clickBehaviorAction;
	    
	    @FXML
	    private Button addClickBehaviorButton;
	    
	    //Fields for the key behavior pane
	    @FXML
	    private AnchorPane keyBehaviorPane;
	    
	    @FXML
	    private ChoiceBox<?> keyBehaviorAction;

	    @FXML
	    private Text keyBehaviorKeyInput;
	    
	    @FXML
	    private Button addKeyBehaviorButton;

	    @FXML
	    private ListView keyList;
	    
	    //Fields for the collision behavior tab
	    @FXML
	    private Button newCollisionBehaviorButton;
	   
	    @FXML
	    private ChoiceBox<?> collisionAction;

	    @FXML
	    private ChoiceBox<?> collisionObjectType;
	    
	    //Fields for the Game Properties tab

	    @FXML
	    private Button addGamePropertyButton;
	    @FXML
	    private Button removeGamePropertyButton;



	    @FXML
	    public void addKeyButtonClicked(ActionEvent event) {
	    	
	    }
	    
	    //if time behavior continuous checkbox is selected, disable the interval field
	    @FXML 
	    public void continuousIntervalSelected(ActionEvent event) {
	    	if(continuousInterval.isSelected()) {
	    		intervalField.setDisable(true);
	    	}
	    	else {
	    		intervalField.setDisable(false);
	    	}
	    }
	    @FXML
	    public void addBehaviorButtonClicked(ActionEvent event) {
	    	if(event.getSource().equals(addClickBehaviorButton)) {
	    		//TODO
	    		System.out.println("Click behavior clicked");
	    	}
	    	else if(event.getSource().equals(addKeyBehaviorButton)) {
	    		//TODO

	    		System.out.println("Key behavior clicked");
	    	}
	    	else if(event.getSource().equals(addTimedBehaviorButton)) {
	    		//TODO
	    		System.out.println("Timed behavior clicked");
	    	}

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
	    	 currentlySelectedSprite = controller.getSpriteList().get(controller.getSpriteList().size() - 1);
	    	 setSpritePropertiesPane();
	    }

	    @FXML
	    public void duplicateSpriteButtonClicked(ActionEvent event) {
	    	//TODO
	    	controller.duplicateSprite(currentlySelectedSprite.copy());
	    	currentlySelectedSprite = controller.getSpriteList().get(controller.getSpriteList().size() - 1);
	    	setSpritePropertiesPane();
	    }
	    
	    @FXML
	    void spriteBehaviorTypeSelected(ActionEvent event) {
	    	timeBehaviorPane.setVisible(false);
	    	timeBehaviorPane.setDisable(true);
	    	mouseBehaviorPane.setVisible(false);
	    	mouseBehaviorPane.setDisable(true);
	    	keyBehaviorPane.setVisible(false);
	    	keyBehaviorPane.setDisable(true);
	    	
	    	switch(spriteBehaviorTypeDropdown.getSelectionModel().getSelectedItem()) {
	    	case "Timed Behavior": timeBehaviorPane.setVisible(true); timeBehaviorPane.setDisable(false); break;
	    	case "On Click Behavior": mouseBehaviorPane.setVisible(true); mouseBehaviorPane.setDisable(false); break;
	    	case "On Key Press Behavior": keyBehaviorPane.setVisible(true); keyBehaviorPane.setDisable(false); break;
	    	}
	    }

	    @FXML
	    public void removeGamePropertyButtonClicked(ActionEvent event) {
	    	//TODO
	    }

	    @FXML
	    public void spriteAppearanceSelected(ActionEvent event) {
	    	if(event.getSource().equals(spriteShapeDropdown)) {
	    		currentlySelectedSprite.getAppearance().setShape(spriteShapeDropdown.getValue());
	    		modifySpriteCommand();
	    	}
	    	else if(event.getSource().equals(spriteChooseImageButton)) {
	    		FileChooser fileChooser = new FileChooser();
	    		File file = fileChooser.showOpenDialog(makerStage);
				if (file != null) {
					currentlySelectedSprite.getAppearance().setImage(file.toURI().toString());
					modifySpriteCommand();
				}
	    	}
	    }

	    @FXML
	    public void spriteColorSelected(ActionEvent event) {
	    	currentlySelectedSprite.getAppearance().setColor(spriteColorPicker.getValue());
	    	modifySpriteCommand();
	    }

	    @FXML
	    public void usesLevelsSelected(ActionEvent event) {
	    	
	    }

	    @FXML
	    public void usesWallsSelected(ActionEvent event) {

	    }

	    
	    public void heightSliderChanged() {
	    	currentlySelectedSprite.setHeight(spriteHeightSlider.getValue());
	    	modifySpriteCommand();
	    }
	    
	    public void widthSliderChanged() {
	    	currentlySelectedSprite.setWidth(spriteWidthSlider.getValue());
	    	modifySpriteCommand();
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
	    
	    public void modifySpriteCommand() {
	    	controller.modifySprite(currentlySelectedSprite);
	    	setSpritePropertiesPane();
	    }

		@Override
		public void update() 
		{
			drawAll();
		}

	

		
}

