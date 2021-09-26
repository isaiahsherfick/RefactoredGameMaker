package controller;

import java.util.ArrayList;

import view.EventMenuItem;

/*
 * Requires a Game Object (Sprite) for which the events will be set.
 */
public class EventsButtonController {

	public static ArrayList<EventMenuItem> selectedEvents;
	
	public EventsButtonController() {
		selectedEvents = new ArrayList<EventMenuItem>();
	}
	
	public static ArrayList<EventMenuItem> getSelectedEvents() {
		return selectedEvents;
	}
	
	public static void remove(EventMenuItem e) {
		selectedEvents.remove(e);
	}

	public static void add(EventMenuItem e) {
		selectedEvents.add(e); 
	}
	
	public void addEventsToObject() {
		//add the events to the GameObject.
	}
}
