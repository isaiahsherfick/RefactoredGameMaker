package views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import behaviors.collision.BounceCollisionBehaviorX;
import behaviors.collision.BounceCollisionBehaviorXY;
import behaviors.collision.BounceCollisionBehaviorY;
import behaviors.collision.CollisionBehavior;
import behaviors.collision.CustomCollisionPair;
import behaviors.collision.DestroyCollisionBehavior;
import behaviors.event.EventBehavior;
import behaviors.event.EventBehaviorChain;
import behaviors.event.MoveOnGameTickBehavior;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saveandload.SaveableEllipse;
import saveandload.SaveableRectangle;
import saveandload.SaveableShape;
import sprite.HitboxView;

public class MakerView {


	private Scene makerScene;
	private Stage makerStage;
	private View view;
	public MakerView(Stage primaryStage, View v) {
		try {
			view = v;
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
		}
		catch(IOException ioe) {
			System.out.println("Failed to load MakerView");
		}
	}
	
	public Scene getScene() {
		return this.makerScene;
	}
	
	public Stage getStage() {
		return this.makerStage;
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
		hitboxWidthSlider.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				hitboxWidthSliderChanged();
			}
		});
		hitboxHeightSlider.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				hitboxHeightSliderChanged();
			}
		});
		
		
		//Populate dropdown menus
		spriteShapeDropdown.getItems().add(new SaveableRectangle());
		spriteShapeDropdown.getItems().add(new SaveableEllipse());
		
		spriteBehaviorTypeDropdown.getItems().add("On Click Behavior");
		spriteBehaviorTypeDropdown.getItems().add("On Key Press Behavior");
		spriteBehaviorTypeDropdown.getItems().add("On Game Tick Behavior");
		
     	timeBehaviorActions.getItems().add(new MoveOnGameTickBehavior());
		
		collisionBehaviorAction.getItems().add(new BounceCollisionBehaviorX());
		collisionBehaviorAction.getItems().add(new BounceCollisionBehaviorXY());
		collisionBehaviorAction.getItems().add(new BounceCollisionBehaviorY());
		collisionBehaviorAction.getItems().add(new DestroyCollisionBehavior());
	}
	
	public void showMaker()
	{
		makerStage.show();
		initializeUIElements();
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
		    private Slider hitboxHeightSlider;
		    
		    @FXML
		    private Slider hitboxWidthSlider;

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
		    private ComboBox<EventBehavior> timeBehaviorActions;

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
		    private ChoiceBox<EventBehavior> clickBehaviorAction;
		    
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
		    
		    //Fields for the collision behavior tab
		    @FXML
		    private Button newCollisionBehaviorButton;
		   
		    @FXML
		    private ComboBox<CollisionBehavior> collisionBehaviorAction;
		    
		    @FXML 
		    private TextField spriteIdInput;
		    
		    //Fields for the Game Properties tab

		    @FXML
		    private Button addGamePropertyButton;
		    @FXML
		    private Button removeGamePropertyButton;
		    @FXML
		    private CheckBox usesLevelsCheckbox;
		    @FXML 
		    private ScrollPane collisionBehaviorList;
		    
		    
		    
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
		    	try {
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
			    		view.getCurrentlySelectedSprite().addEventBehavior(timeBehaviorActions.getValue());
			    		setPanesForCurrentlySelectedSprite();
			    	}
			    }
		    	catch(Exception ex) {
		    		
		    	}

		    }
		    
		    private void setBehaviorsPane() {
		    	Text behaviors = new Text();
		    	EventBehaviorChain behaviorsList = view.getCurrentlySelectedSprite().getEventBehaviorChain();
		    	for(int i = 0; i < behaviorsList.size(); i++) {
		    		String collisionEntry = behaviorsList.get(i).toString();
		    		behaviors.setText(behaviors.getText() + "\n" + collisionEntry);
		    	}
		    	spriteBehaviorList.setContent(behaviors);
		    }

		    @FXML
		    public void addCollisionBehaviorButtonClicked(ActionEvent event) {
		    	try {
		    		int spriteId = Integer.parseInt(spriteIdInput.getText());
		    		CollisionBehavior toAdd = collisionBehaviorAction.getValue();
		    		view.getCurrentlySelectedSprite().addCustomCollision(spriteId, toAdd);
		    		setCollisionBehaviorsPane();
		    	}
		    	catch(Exception ex) {
		    		System.out.println("A field is missing or invalid");
		    	}
		    }
		    
		    private void setCollisionBehaviorsPane() {
		    	Text collisions = new Text();
		    	ArrayList<CustomCollisionPair> collisionsList = view.getCurrentlySelectedSprite().getCustomCollisionPairs();
		    	for(CustomCollisionPair c: collisionsList) {
		    		String collisionEntry = c.getCollisionBehavior().toString() + " Colliding with Sprite ID: " + Integer.toString(c.getSpriteId());
		    		collisions.setText(collisions.getText() + "\n" + collisionEntry);
		    	}
		    	collisionBehaviorList.setContent(collisions);
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
		    	 view.getController().createSprite();
		    	 view.setCurrentlySelectedSprite(view.getController().getSpriteList().get(view.getController().getSpriteList().size() - 1));
		    	 setPanesForCurrentlySelectedSprite();
		    }

		    @FXML
		    public void duplicateSpriteButtonClicked(ActionEvent event) {
		    	//TODO
		    	view.getController().duplicateSprite(view.getCurrentlySelectedSprite().copy());
		    	view.setCurrentlySelectedSprite(view.getController().getSpriteList().get(view.getController().getSpriteList().size() - 1));
		    	setPanesForCurrentlySelectedSprite();
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
		    	case "On Game Tick Behavior": timeBehaviorPane.setVisible(true); timeBehaviorPane.setDisable(false); break;
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
		    		view.getCurrentlySelectedSprite().getAppearance().setShape(spriteShapeDropdown.getValue());
		    		view.modifySpriteCommand();
		    	}
		    	else if(event.getSource().equals(spriteChooseImageButton)) {
		    		FileChooser fileChooser = new FileChooser();
		    		File file = fileChooser.showOpenDialog(makerStage);
					if (file != null) {
						view.getCurrentlySelectedSprite().getAppearance().setImage(file.toURI().toString());
						view.modifySpriteCommand();
					}
		    	}
		    }

		    @FXML
		    public void spriteColorSelected(ActionEvent event) {
		    	view.getCurrentlySelectedSprite().getAppearance().setColor(spriteColorPicker.getValue());
		    	view.modifySpriteCommand();
		    }

		    @FXML
		    public void usesLevelsSelected(ActionEvent event) {
		    	//TODO uses levels
		    	
		    }

		    @FXML
		    public void usesWallsSelected(ActionEvent event) {
		    	//Spawn four sprites in the walls
		    	view.getController().addWalls();
		    }

		    
		    public void heightSliderChanged() {
		    	view.getCurrentlySelectedSprite().setHeight(spriteHeightSlider.getValue());
		    	view.modifySpriteCommand();
		    }
		    
		    public void widthSliderChanged() {
		    	view.getCurrentlySelectedSprite().setWidth(spriteWidthSlider.getValue());
		    	view.modifySpriteCommand();
		    }
		    
		    public void hitboxWidthSliderChanged() {
		    	view.getCurrentlySelectedSprite().getHitBox().setWidth(hitboxWidthSlider.getValue());
		    	view.modifySpriteCommand();
		    	showHitbox();
		    }
		    
		    public void hitboxHeightSliderChanged() {
		    	view.getCurrentlySelectedSprite().getHitBox().setHeight(hitboxHeightSlider.getValue());
		    	view.modifySpriteCommand();
		    	showHitbox();
		    }
		    
		    public void showHitbox() {
		    	 HitboxView tempHitboxSprite = new HitboxView();
		    	tempHitboxSprite.setWidth(view.getCurrentlySelectedSprite().getHitBox().getWidth());
		    	tempHitboxSprite.setHeight(view.getCurrentlySelectedSprite().getHitBox().getHeight());
		    	tempHitboxSprite.setX(view.getCurrentlySelectedSprite().getHitBox().getX());
		    	tempHitboxSprite.setY(view.getCurrentlySelectedSprite().getHitBox().getY());
		    	tempHitboxSprite.draw(view.getPlayerView().getGameCanvas().getGraphicsContext2D());
		    }
		    
		  //Sets the Sprite Properties pane values to the values of CurrentlySelectedSprite
			public void setPanesForCurrentlySelectedSprite() {
				//Set all labels to corresponding value of the sprite
				spriteIdLabel.setText("" + view.getCurrentlySelectedSprite().getSpriteId());
				spriteXLabel.setText("" + view.getCurrentlySelectedSprite().getX());
				spriteYLabel.setText("" + view.getCurrentlySelectedSprite().getY());
				
				//Set the color selector
				spriteColorPicker.setValue(view.getCurrentlySelectedSprite().getAppearance().getColor());
				
				//Set width and height slider values
				spriteWidthSlider.setValue(view.getCurrentlySelectedSprite().getAppearance().getWidth());
				spriteHeightSlider.setValue(view.getCurrentlySelectedSprite().getAppearance().getHeight());
				hitboxWidthSlider.setValue(view.getCurrentlySelectedSprite().getHitBox().getWidth());
				hitboxHeightSlider.setValue(view.getCurrentlySelectedSprite().getHitBox().getHeight());
				
				
				setCollisionBehaviorsPane();
				setBehaviorsPane();
			}
			
			
	
}