package controller;

import java.util.ArrayList;

import javafx.scene.control.CheckBox;
import view.EventMenuItem;

/*
 * Requires a Game Object (Sprite) for which the events will be set.
 */
public class EventsButtonController {

	CheckBox keyEvent;
	CheckBox mouseEvent;
	CheckBox timeEvent;
	public static ArrayList<CheckBox> allEvents;
	public static ArrayList<CheckBox> selectedEvents;
	
	public EventsButtonController() {
		allEvents = new ArrayList<CheckBox>();
		selectedEvents = new ArrayList<CheckBox>();
		
		keyEvent = new CheckBox("Key Behaviour");
		keyEvent.setOnAction(e -> handleKeyEvent());
		
		mouseEvent = new CheckBox("Mouse Behaviour");
		mouseEvent.setOnAction(e -> handleMouseEvent());
		
		timeEvent = new CheckBox("Time Behaviour");
		timeEvent.setOnAction(e -> handleTimeEvent());
	
		allEvents.add(keyEvent);
		allEvents.add(mouseEvent);
		allEvents.add(timeEvent);
	}
	
	public static ArrayList<CheckBox> getSelectedEvents() {
		return selectedEvents;
	}
	
	public static void remove(EventMenuItem e) {
//		selectedEvents.remove(e);
	}

	public static void add(EventMenuItem e) {
//		selectedEvents.add(e); 
	}
	
	public void addEventsToObject() {
		//add the events to the GameObject.
	}
	
	private void handleKeyEvent() {
		if(!selectedEvents.contains(keyEvent)) {
			selectedEvents.add(keyEvent);
		} else {
			selectedEvents.remove(keyEvent);
		}
	}
	
	private void handleMouseEvent() {
		if(!selectedEvents.contains(mouseEvent)) {
			selectedEvents.add(mouseEvent);
		} else {
			selectedEvents.remove(mouseEvent);
		}
	}
	
	private void handleTimeEvent() {
		if(!selectedEvents.contains(timeEvent)) {
			selectedEvents.add(timeEvent);
		} else {
			selectedEvents.remove(timeEvent);
		}
	}
	
	public static ArrayList<CheckBox> getAllSelectedEvents() {
		return selectedEvents;
	}

	
	public static ArrayList<CheckBox> getAllEvents() {
		return allEvents;
	}

}
