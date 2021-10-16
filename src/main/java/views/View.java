//@Author Christian Dummer
package views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import controller.Controller;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
	    private ComboBox<SaveableShape> spriteShapeDropdown;

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

