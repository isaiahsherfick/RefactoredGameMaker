package view;


import controller.EventsButtonController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;

/*
 *  EventMenutItem should be instantiated with a name and Strategy
 */
public class EventMenuItem extends CheckMenuItem {
	
	String name;
	public EventMenuItem(String name) {
		super(name);
		this.name = name;
		setup();
	}
	
	private void setup() {
		this.setOnAction(new EventHandler() {

			public void handle(Event event) {
				handleEventSelection();
			}
			
		});
	}
	
	private void handleEventSelection() {
		if( isSelected()) {
			// add the event to the controller 
			EventsButtonController.add(this);
			
			//TODO: execute the strategy
		} 
		
		if (!isSelected()) {
			EventsButtonController.remove(this);
		}
	}
}
