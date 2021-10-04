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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;
import sound.Sound;
import strategies.Strategy;

public class FormLayouts {

	private static final String CHOOSE_SHAPE = "--Choose shape--";
	public static MakerController makerController = new MakerController();
	public static VBox strategyDetails;
	public static Strategy currentlyEditedStrategy;

	public static GridPane getSpriteFormLayout() {
		return createSpriteFormPane();
	}

	public static GridPane getShapeFormLayout(Stage primaryStage) {
		return createShapeFormPane(primaryStage);
	}

	public static GridPane getEventsFormLayout() {
		return createEventFormPane();
	}

	public static GridPane getSoundsFormLayout() {
		return createSoundFormPane();
	}

	private static GridPane createSpriteFormPane() {
		return addSpriteUI(getGridPane());

	}
	
	public static GridPane getStagesFormLayout() {
		return createStageFormPane(getGridPane());
	}

	private static GridPane createShapeFormPane(Stage primaryStage) {
		return addShapeUI(getGridPane(), primaryStage);
	}

	private static GridPane createEventFormPane() {
		return addUIForEvents(getGridPane());
	}

	private static GridPane createSoundFormPane() {
		return addUIForSounds(getGridPane());
	}

	private static GridPane getGridPane() {
		// Instantiate a new Grid Pane
		GridPane gridPane = new GridPane();

		// Position the pane at the center of the screen, both vertically and
		// horizontally
		gridPane.setAlignment(Pos.CENTER);

		// Set a padding of 20px on each side
		gridPane.setPadding(new Insets(40, 40, 40, 40));

		// Set the horizontal gap between columns
		gridPane.setHgap(10);

		// Set the vertical gap between rows
		gridPane.setVgap(10);

		// Add Column Constraints

		// columnOneConstraints will be applied to all the nodes placed in column one.
		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);

		// columnTwoConstraints will be applied to all the nodes placed in column two.
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);

		gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
		return gridPane;
	}

	private static GridPane addSpriteUI(GridPane gridPane) {
		// Add Header
		Label headerLabel = new Label("Sprite Form");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

		// Add Name Label
		Label nameLabel = new Label("Sprite Name : ");
		gridPane.add(nameLabel, 0, 1);

		// Add Name Text Field
		TextField nameField = new TextField();
		nameField.setPrefHeight(40);
		gridPane.add(nameField, 1, 1);

		// Add Submit Button
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
				if (nameField.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter sprite name");
					return;
				}

				showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "",
						nameField.getText() + " Created");
			}
		});

		return gridPane;
	}

	private static GridPane addShapeUI(GridPane gridPane, Stage primaryStage) {
		// Add Header
		Label headerLabel = new Label("Shape Form");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

		// Add Name Label
		Label nameLabel = new Label("Select Shape :");
		gridPane.add(nameLabel, 0, 1);

		// ChoiceDialog for the user to select the default shapes
		ChoiceDialog<String> customisedInputDialog = new ChoiceDialog<String>(CHOOSE_SHAPE, "SQUARE", "CIRCLE",
				"RECTANGLE");

		gridPane.add((GridPane) customisedInputDialog.getDialogPane().getContent(), 1, 1);

		FileChooser fileChooser = new FileChooser();

		Button openButton = new Button("Choose Image");
		ImageView imageView = new ImageView();
		openButton.setOnAction((final ActionEvent e) -> {
			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				Image image = new Image(file.toURI().toString());
				imageView.setImage(image);
				openButton.setText(file.getName());
			}
		});

		Label orLabel = new Label("OR");
		gridPane.add(orLabel, 2, 1);

		gridPane.add(openButton, 3, 1);

		// Add Name Label
		Label name = new Label("Sprite Name : ");
		gridPane.add(name, 0, 2);

		// Add Name Text Field
		TextField nameField = new TextField();
		gridPane.add(nameField, 1, 2);

		// Add Color Label
		Label color = new Label("Sprite Color : ");
		gridPane.add(color, 0, 3);

		// ChoiceDialog for the user to select the default shapes
		ChoiceDialog<String> colorDialog = new ChoiceDialog<String>("WHITE", "RED", "YELLOW", "ORANGERED", "BLUE",
				"LIGHTGREEN");
		gridPane.add((GridPane) colorDialog.getDialogPane().getContent(), 1, 3);

		// Add Submit Button
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
				if ((customisedInputDialog.getSelectedItem().equals(CHOOSE_SHAPE) && imageView.getImage() == null)
						|| nameField.getText() == null)
					showAlert(Alert.AlertType.WARNING, gridPane.getScene().getWindow(), "", "Fields cannot be empty");
				else {
					makerController.addNewGameObject(customisedInputDialog.getSelectedItem(), imageView,
							nameField.getText(), colorDialog.getSelectedItem());
				}
			}
		});

		return gridPane;
	}

	private static GridPane addUIForEvents(GridPane gridPane) {
		strategyDetails = new VBox();
		Button saveBtn = new Button("SAVE");
		saveBtn.setPrefHeight(40);
		saveBtn.setDefaultButton(true);
		saveBtn.setPrefWidth(100);
		gridPane.add(saveBtn, 0, 10, 2, 1);
		GridPane.setHalignment(saveBtn, HPos.CENTER);
		GridPane.setMargin(saveBtn, new Insets(20, 0, 20, 0));

		EventsButtonController BtnController = new EventsButtonController();
		ComboBox<Strategy> ClickComboBox = new ComboBox<>();
		ComboBox<Strategy> CollisionComboBox = new ComboBox<>();
		ComboBox<Strategy> KeyComboBox = new ComboBox<>();
		ComboBox<Strategy> MoveComboBox = new ComboBox<>();
		ComboBox<Strategy> TimedComboBox = new ComboBox<> ();
		ClickComboBox.itemsProperty().setValue(FXCollections.observableList(BtnController.getEventType(new ClickBehavior())));
		CollisionComboBox.itemsProperty().setValue(FXCollections.observableList(BtnController.getEventType(new CollisionBehavior())));
		KeyComboBox.itemsProperty().setValue(FXCollections.observableList(BtnController.getEventType(new KeyBehavior())));
		MoveComboBox.itemsProperty().setValue(FXCollections.observableList(BtnController.getEventType(new MoveBehavior())));
		TimedComboBox.itemsProperty().setValue(FXCollections.observableList(BtnController.getEventType(new TimedBehavior())));
	
		ClickComboBox.setConverter(new StringConverter<Strategy>() {
			@Override
			public String toString(Strategy s) {
				if (s != null)
					return s.getName();
				else
					return "";
			}

			@Override
			public Strategy fromString(final String string) {
				return ClickComboBox.getItems().stream().filter(s -> s.getName().equals(string)).findFirst()
						.orElse(null);
			}
		});
		ClickComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Strategy Name: " + newValue.getName());
		});
		ClickComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(ClickComboBox.getSelectionModel().getSelectedItem() instanceof ClickBehavior) {
					clearStrategiesSubView(gridPane);
					strategyDetails = addUIForClickBehavior((ClickBehavior)ClickComboBox.getSelectionModel().getSelectedItem());
					gridPane.add(strategyDetails, 5, 1);
				}
			}
		});
		
		CollisionComboBox.setConverter(new StringConverter<Strategy>() {
			@Override
			public String toString(Strategy s) {
				if (s != null)
					return s.getName();
				else
					return "";
			}

			@Override
			public Strategy fromString(final String string) {
				return ClickComboBox.getItems().stream().filter(s -> s.getName().equals(string)).findFirst()
						.orElse(null);
			}
		});
		CollisionComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Strategy Name: " + newValue.getName());
		});
		CollisionComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(CollisionComboBox.getSelectionModel().getSelectedItem() instanceof CollisionBehavior) {
					clearStrategiesSubView(gridPane);
					strategyDetails = addUIForCollisionBehavior((CollisionBehavior)CollisionComboBox.getSelectionModel().getSelectedItem());
					gridPane.add(strategyDetails, 5, 1);
				}
			}
		});
		
		KeyComboBox.setConverter(new StringConverter<Strategy>() {
			@Override
			public String toString(Strategy s) {
				if (s != null)
					return s.getName();
				else
					return "";
			}

			@Override
			public Strategy fromString(final String string) {
				return ClickComboBox.getItems().stream().filter(s -> s.getName().equals(string)).findFirst()
						.orElse(null);
			}
		});
		KeyComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Strategy Name: " + newValue.getName());
		});
		KeyComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(KeyComboBox.getSelectionModel().getSelectedItem() instanceof KeyBehavior) {
					clearStrategiesSubView(gridPane);
					strategyDetails = addUIForKeyBehavior((KeyBehavior)KeyComboBox.getSelectionModel().getSelectedItem());
					gridPane.add(strategyDetails, 5, 1);
				}
			}
		});
		
		MoveComboBox.setConverter(new StringConverter<Strategy>() {
			@Override
			public String toString(Strategy s) {
				if (s != null)
					return s.getName();
				else
					return "";
			}

			@Override
			public Strategy fromString(final String string) {
				return ClickComboBox.getItems().stream().filter(s -> s.getName().equals(string)).findFirst()
						.orElse(null);
			}
		});
		MoveComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Strategy Name: " + newValue.getName());
		});
		
		MoveComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(MoveComboBox.getSelectionModel().getSelectedItem() instanceof MoveBehavior) {
					clearStrategiesSubView(gridPane);
					strategyDetails = addUIForMoveBehavior((MoveBehavior)MoveComboBox.getSelectionModel().getSelectedItem());
					gridPane.add(strategyDetails, 5, 1);
				}
			}
		});
		
		TimedComboBox.setConverter(new StringConverter<Strategy>() {
			@Override
			public String toString(Strategy s) {
				if (s != null)
					return s.getName();
				else
					return "";
			}

			@Override
			public Strategy fromString(final String string) {
				return ClickComboBox.getItems().stream().filter(s -> s.getName().equals(string)).findFirst()
						.orElse(null);
			}
		});
		TimedComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Strategy Name: " + newValue.getName());
		});
		
		TimedComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(TimedComboBox.getSelectionModel().getSelectedItem() instanceof TimedBehavior) {
					clearStrategiesSubView(gridPane);
					strategyDetails = addUIForTimeBehavior((TimedBehavior)TimedComboBox.getSelectionModel().getSelectedItem());
					gridPane.add(strategyDetails, 5, 1);
				}
			}
		});
		
		Label clickLabel = new Label("Click Events");
		Label collisionLabel = new Label("Collision Events");
		Label keyLabel = new Label("Key Events");
		Label moveLabel = new Label("Move Events");
		Label timeLabel = new Label("Timed Events");

		gridPane.add(clickLabel, 1, 0);
		gridPane.add(ClickComboBox, 1, 1);
		
		gridPane.add(collisionLabel, 1, 2);
		gridPane.add(CollisionComboBox, 1, 3);
		
		gridPane.add(keyLabel, 1, 4);
		gridPane.add(KeyComboBox, 1, 5);

		gridPane.add(moveLabel, 1, 6);
		gridPane.add(MoveComboBox, 1, 7);

		gridPane.add(timeLabel, 1, 8);
		gridPane.add(TimedComboBox, 1, 9);
		
		
		saveBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			}
		});
		return gridPane;
	}
	
	private static void clearStrategiesSubView(GridPane gridPane) {
		if(gridPane.getChildren().contains(strategyDetails)) {
			gridPane.getChildren().remove(strategyDetails);
		}
	}
	
	private static VBox addUIForClickBehavior(ClickBehavior c) {
		VBox clickBehaviorForm = new VBox();
		Button saveButton = new Button("Save");
		EventsButtonController btnController = new EventsButtonController();
		saveButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				try {
					for(Strategy s: btnController.getEventType(new ClickBehavior())) {
						if(s.getClass() == c.getClass()) {
						ClickBehavior toAdd = c.getClass().newInstance();
						currentlyEditedStrategy = toAdd;
						}
					}
				}
				catch(Exception ex) {
					System.out.println("Conversion failed");
				}
				
			}
		});
		
		clickBehaviorForm.getChildren().add(saveButton);
		return clickBehaviorForm;
	}

	private static VBox addUIForCollisionBehavior(CollisionBehavior c) {
		VBox collisionBehaviorForm = new VBox();
		Button saveButton = new Button("Save");
		EventsButtonController btnController = new EventsButtonController();
		saveButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				try {
					for(Strategy s: btnController.getEventType(new CollisionBehavior())) {
						if(s.getClass() == c.getClass()) {
						CollisionBehavior toAdd = c.getClass().newInstance();
						btnController.addSelectedEvent(toAdd);
						makerController.getCurrentlySelectedObject().addBehavior(toAdd);
						}
					}
				}
				catch(Exception ex) {
					System.out.println("Conversion failed");
				}
				
			}
		});
		collisionBehaviorForm.getChildren().add(saveButton);
		return collisionBehaviorForm;
	}
	
	private static VBox addUIForKeyBehavior(KeyBehavior k) {
		VBox keyBehaviorForm = new VBox();
		EventsButtonController btnController = new EventsButtonController();
		Label keySelection = new Label("Enter which key will execute behavior");
		TextField enterKeys = new TextField();
		Label actionSelection = new Label("Select Behavior to run on keyPress");
		Button saveButton = new Button("Save");
		
		ComboBox<Strategy> optionsOnTimer = new ComboBox<Strategy>();
		optionsOnTimer.itemsProperty().setValue(FXCollections.observableList(btnController.getKeyEvents()));
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
		
		KeyBehavior behavior = new KeyBehavior();
		optionsOnTimer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Strategy selected = optionsOnTimer.getSelectionModel().getSelectedItem();
				//Display movement behavior UI before creating the behavior
				if(selected instanceof MoveBehavior) {
					VBox movementUI = moveBehaviorSubUI((MoveBehavior)selected);
					keyBehaviorForm.getChildren().add(movementUI);
				}
			}
		});
		
		saveButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
					//Instantiates the right object type and assigns it to the TimedBehavior;
					for(Strategy s: btnController.getTimeableEvents()) {
						if(behavior.getClass() == s.getClass()) {
							try {
								behavior.addBehavior(currentlyEditedStrategy);
								btnController.addSelectedEvent(behavior);
								makerController.getCurrentlySelectedObject().addBehavior(behavior);
							}
							catch(Exception ex) {
								System.out.println("Type Conversion Failed");
							}
						}
					}
				}
			});
		
		keyBehaviorForm.getChildren().addAll(keySelection, enterKeys, actionSelection, optionsOnTimer, saveButton);
		return keyBehaviorForm;
	}
	
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


		EventsButtonController eventBtnController = new EventsButtonController();
		ComboBox<Strategy> eventComboBox = new ComboBox<>();
		eventComboBox.itemsProperty().setValue(FXCollections.observableList(eventBtnController.getAllSelectedEvents()));
		
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
		ObservableList<Sound> soundList = FXCollections.observableList(soundBtnController.getAllSounds());
		soundComboBox.itemsProperty().setValue(soundList);

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

		return gridPane;
	}
	
	private static GridPane createStageFormPane(GridPane gridPane) {
		//TODO
		
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
