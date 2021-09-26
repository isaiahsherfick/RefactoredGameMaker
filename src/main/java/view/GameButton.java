package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public abstract class GameButton extends MenuButton {

	String backgroundColorHex;
	ArrayList<EventMenuItem> menuItems;

	public GameButton(String backgroundColorHex, ArrayList<EventMenuItem> items) {
		this.backgroundColorHex = backgroundColorHex;
		this.menuItems = items;

		addAllMenuItems();
		setupTapListener();
		adjustStyle();
	}
	
	public void addAllMenuItems() {
		for(int i = 0; i<menuItems.size();i++) {
			this.getItems().add(menuItems.get(i));
		}
	} 

	private void adjustStyle() {
		// Change button Appearance
		this.setStyle("-fx-background-color: #"+backgroundColorHex+"; -fx-background-radius: 15px;	-fx-text-fill: #006464; -fx-border-radius: 20;   -fx-background-radius: 20; -fx-padding: 5;");
	}

	private void didTapGameButton() {
		
	}

	private void setupTapListener() {
		this.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				didTapGameButton();
			}
		});
	}
}
