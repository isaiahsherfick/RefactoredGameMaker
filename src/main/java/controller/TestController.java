package controller;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.EventMenuItem;
import view.EventsButton;

public class TestController extends Application {
	public static void main(String[] args)  {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		
		EventsButtonController controller = new EventsButtonController();
		
		ArrayList<EventMenuItem> menuItems = new ArrayList<EventMenuItem>();
		menuItems.add(new EventMenuItem("Key Behaviour"));
		menuItems.add(new EventMenuItem("Mouse Behaviour"));
		menuItems.add(new EventMenuItem("Hover Behaviour"));
		EventsButton btn = new EventsButton("Add Events", "DFB951", menuItems);
		root.getChildren().add(btn);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show(); 
	}
}
