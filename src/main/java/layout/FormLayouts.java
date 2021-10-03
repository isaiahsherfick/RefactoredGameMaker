package layout;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import behaviors.ClickBehavior;
import behaviors.CollisionBehavior;
import behaviors.KeyBehavior;
import behaviors.MoveBehavior;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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

		return gridPane;
		
		
		/*saveBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// For now just print the event
				MainController.getEventsController().getGameObject().setStrategies(MainController.getEventsController().getAllSelectedEvents());
			}
		});
		return gridPane;*/
	}

	private static GridPane addUIForSounds(GridPane gridPane) {
		// Add Header
		Label headerLabel = new Label("Sound Form");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

		// Add Name Label
		Label nameLabel = new Label("Select Sound : ");
		gridPane.add(nameLabel, 0, 1);

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

		gridPane.add(soundComboBox, 1, 1);

		return gridPane;
	}

	private static GridPane addUIControls(GridPane gridPane) {
		// Add Header
		Label headerLabel = new Label("Registration Form");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

		// Add Name Label
		Label nameLabel = new Label("Full Name : ");
		gridPane.add(nameLabel, 0, 1);

		// Add Name Text Field
		TextField nameField = new TextField();
		nameField.setPrefHeight(40);
		gridPane.add(nameField, 1, 1);

		// Add Email Label
		Label emailLabel = new Label("Email ID : ");
		gridPane.add(emailLabel, 0, 2);

		// Add Email Text Field
		TextField emailField = new TextField();
		emailField.setPrefHeight(40);
		gridPane.add(emailField, 1, 2);

		// Add Password Label
		Label passwordLabel = new Label("Password : ");
		gridPane.add(passwordLabel, 0, 3);

		// Add Password Field
		PasswordField passwordField = new PasswordField();
		passwordField.setPrefHeight(40);
		gridPane.add(passwordField, 1, 3);

		// Add Submit Button
		Button submitButton = new Button("Submit");
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
							"Please enter your name");
					return;
				}
				if (emailField.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter your email id");
					return;
				}
				if (passwordField.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter a password");
					return;
				}

				showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!",
						"Welcome " + nameField.getText());
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
