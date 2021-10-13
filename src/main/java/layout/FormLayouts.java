package layout;

import java.io.File;
import java.util.ArrayList;

import behaviors.ClickBehavior;
import behaviors.CollisionBehavior;
import behaviors.KeyBehavior;
import behaviors.MoveBehavior;
import behaviors.MoveBehavior.Direction;
import behaviors.TimedBehavior;
import controller.EventsButtonController;
import controller.MainController;
import controller.MakerController;
import controller.SoundButtonController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;
import sound.Sound;
import strategies.Strategy;

public class FormLayouts {
	private static VBox moveBehaviorSubUI(MoveBehavior m) {
		VBox moveBehaviorForm = new VBox();
		EventsButtonController btnController = new EventsButtonController();
		Label moveLabel = new Label("Enter details for moveBehavior");
		Label directionLabel = new Label("Enter direction of movement");
		TextField directionField = new TextField();
		Label speedLabel = new Label("Enter speed of movement (how many pixels it moves per tick)");
		TextField speedField = new TextField();
		Button saveButton = new Button("Save");
		
		saveButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				try {
					for(Strategy s: btnController.getEventType(new MoveBehavior())) {
						if(s.getClass() == m.getClass()) {
						MoveBehavior toAdd = m.getClass().newInstance();
						toAdd.setSprite(makerController.getCurrentlySelectedObject());
						toAdd.setSpeed(Double.parseDouble(speedField.getText()));
						toAdd.setDirection(Direction.valueOf(directionField.getText()));
						currentlyEditedStrategy = toAdd;
						}
					}
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				
			}
		});
		moveBehaviorForm.getChildren().addAll(moveLabel, directionLabel, directionField, speedLabel, speedField, saveButton);
		
		return moveBehaviorForm;
	}
	private static VBox addUIForMoveBehavior(MoveBehavior m) {
		VBox moveBehaviorForm = new VBox();
		EventsButtonController btnController = new EventsButtonController();
		Label moveLabel = new Label("Enter details for moveBehavior");
		Label directionLabel = new Label("Enter direction of movement");
		TextField directionField = new TextField();
		Label speedLabel = new Label("Enter speed of movement (how many pixels it moves per tick)");
		TextField speedField = new TextField();
		Button saveButton = new Button("Save");
		
		saveButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				try {
					for(Strategy s: btnController.getEventType(new MoveBehavior())) {
						if(s.getClass() == m.getClass()) {
						MoveBehavior toAdd = m.getClass().newInstance();
						toAdd.setSprite(makerController.getCurrentlySelectedObject());
						toAdd.setSpeed(Double.parseDouble(speedField.getText()));
						toAdd.setDirection(Direction.valueOf(directionField.getText()));
						btnController.addSelectedEvent(toAdd);
						makerController.getCurrentlySelectedObject().addBehavior(toAdd);
						}
					}
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				
			}
		});
		moveBehaviorForm.getChildren().addAll(moveLabel, directionLabel, directionField, speedLabel, speedField, saveButton);
		
		return moveBehaviorForm;
	}
	
	private static VBox addUIForTimeBehavior(TimedBehavior t) {
		VBox timeBehaviorForm = new VBox();
		EventsButtonController btnController = new EventsButtonController();
		Label timeLabel = new Label("Enter details for Timed Behavior");
		Label intervalLabel = new Label("Enter seconds between running of event");
		TextField intervalField = new TextField();
		Button saveButton = new Button("Save");
		
		Label actionSelection = new Label("Select Behavior to run on each interval");
		ComboBox<Strategy> optionsOnTimer = new ComboBox<Strategy>();
		optionsOnTimer.itemsProperty().setValue(FXCollections.observableList(btnController.getTimeableEvents()));
		optionsOnTimer.setConverter(new StringConverter<Strategy>() {
			@Override
			public String toString(Strategy s) {
				if (s != null)
					return s.getName();
				else
					return "";
			}

			@Override
			public Strategy fromString(final String string) {
				return optionsOnTimer.getItems().stream().filter(s -> s.getName().equals(string)).findFirst()
						.orElse(null);
			}
		});
		optionsOnTimer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Strategy Name: " + newValue.getName());
		});
		

		
		TimedBehavior behavior = new TimedBehavior();
		optionsOnTimer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Strategy selected = optionsOnTimer.getSelectionModel().getSelectedItem();
				//Display movement behavior UI before creating the behavior
				if(selected instanceof MoveBehavior) {
					VBox movementUI = moveBehaviorSubUI((MoveBehavior)selected);
					timeBehaviorForm.getChildren().add(movementUI);
				}
				else {
					//Instantiates the right object type and assigns it to the TimedBehavior;
					for(Strategy s: btnController.getTimeableEvents()) {
						if(behavior.getClass() == s.getClass()) {
							try {
								behavior.adjustInterval(Double.parseDouble(intervalField.getText()));
								behavior.addStrategy(currentlyEditedStrategy);
								btnController.addSelectedEvent(behavior);
								behavior.setSprite(makerController.getCurrentlySelectedObject());
								makerController.getCurrentlySelectedObject().addBehavior(behavior);
							}
							catch(Exception ex) {
								System.out.println("Type Conversion Failed");
							}
						}
					}
				}
			}
		});
		
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				//Adds the timed behavior to the current object
				try {
					behavior.addStrategy(currentlyEditedStrategy);
					makerController.getCurrentlySelectedObject().addBehavior(behavior);
					System.out.println("Successfully added behavior");
				}
				catch(Exception ex) {
					
				}
			}
		});
		
		timeBehaviorForm.getChildren().addAll(timeLabel, intervalLabel, intervalField, actionSelection, optionsOnTimer, saveButton);
		return timeBehaviorForm;
	}
	
	
	private static GridPane addUIForSounds(GridPane gridPane) {
		// Add Header
		Label headerLabel = new Label("Sound Form");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

		ComboBox<Strategy> eventComboBox = new ComboBox<>();
		System.out.println("Events size:" +EventsButtonController.getAllSelectedEvents().size());
		eventComboBox.itemsProperty().setValue(FXCollections.observableList(EventsButtonController.getAllSelectedEvents()));
		
		eventComboBox.setConverter(new StringConverter<Strategy>() {
			@Override
			public String toString(Strategy s) {
				if (s != null)
					return s.getName();
				else
					return "";
			}

			@Override
			public Strategy fromString(final String string) {
				return eventComboBox.getItems().stream().filter(sound -> sound.getName().equals(string)).findFirst()
						.orElse(null);
			}
		});

		eventComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Strategy Name: " + newValue.getName());
		});
		
		SoundButtonController soundBtnController = new SoundButtonController();
		ComboBox<Sound> soundComboBox = new ComboBox<>();
		soundComboBox.itemsProperty().setValue(FXCollections.observableList(soundBtnController.getAllSounds()));

		soundComboBox.setConverter(new StringConverter<Sound>() {
			@Override
			public String toString(Sound sound) {
				if (sound != null)
					return sound.getName();
				else
					return "";
			}

			@Override
			public Sound fromString(final String string) {
				return soundComboBox.getItems().stream().filter(sound -> sound.getName().equals(string)).findFirst()
						.orElse(null);
			}
		});

		soundComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Sound Name: " + newValue.getName());
		});
		
		Label eventsLabel = new Label("Selected Events");
		Label soundsLabel = new Label("Sounds to attach");
		Button saveButton = new Button("Save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//Attaches the sound to the strategy
				ArrayList<Strategy> behaviorList = makerController.getCurrentlySelectedObject().getBehaviors();
				makerController.getCurrentlySelectedObject().getBehaviors()
				.get(behaviorList.indexOf(eventComboBox.getSelectionModel().getSelectedItem()))
				.addSound(soundComboBox.getSelectionModel().getSelectedItem());;
			}
		});
		gridPane.add(eventsLabel, 1, 1);
		gridPane.add(eventComboBox, 1, 2);
		gridPane.add(soundsLabel, 1, 3);
		gridPane.add(soundComboBox, 1, 4);
		gridPane.add(saveButton, 1, 5);

		return gridPane;
	}
	
private static GridPane addUIForStage(GridPane gridPane) {
		
		Label headerLabel = new Label("Stage Form");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

		// Add Name Label
		Label nameLabel = new Label("Select Stage :");
		gridPane.add(nameLabel, 0, 1);
		
		ChoiceDialog<String> colorDialog = new ChoiceDialog<String>("WHITE", "RED", "YELLOW", "ORANGERED", "BLUE",
				"LIGHTGREEN","BLACK");
		gridPane.add((GridPane) colorDialog.getDialogPane().getContent(), 1, 2);
		
		Button submitButton = new Button("Save");
		submitButton.setPrefHeight(40);
		submitButton.setDefaultButton(true);
		submitButton.setPrefWidth(100);
		gridPane.add(submitButton, 0, 4, 2, 1);
		GridPane.setHalignment(submitButton, HPos.CENTER);
		GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// customisedInputDialog.get
				MainController.changeBackground(colorDialog.getSelectedItem());
			}
		});
		
		return gridPane;
		
	}
	

	private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

}
