package controller;

import java.util.ArrayList;

import behaviors.KeyBehavior;
import behaviors.MoveBehavior;
import behaviors.TimedBehavior;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import strategies.Strategy;
import view.GameObject;

/*
 * Requires a Game Object (Sprite) for which the events will be set.
 */
public class EventsButtonController {

	CheckBox keyEvent;
	CheckBox moveEvent;
	CheckBox clickEvent;
	CheckBox timeEvent;

	public static  ArrayList<CheckBox> allEvents;
	
	// selected events for the gameObject
	public static ArrayList<Strategy> selectedEvents;
	private GameObject gameObject;	
	private KeyBehavior keyBehaviour;
	private MoveBehavior moveBehaviour;
	private TimedBehavior timeBehaviour;
	
	public EventsButtonController() {
		//gameObject = new GameObject();
		allEvents = new ArrayList<CheckBox>();
		selectedEvents = new ArrayList<Strategy>();
		//keyBehaviour = new KeyBehavior(gameObject);
		//moveBehaviour = new MoveBehavior(gameObject);
		//timeBehaviour = new TimedBehavior(gameObject);
		
		keyEvent = new CheckBox("Key Behaviour");
		keyEvent.setOnAction(e -> handleKeyEvent());
		
		moveEvent = new CheckBox("Move Behaviour");
		moveEvent.setOnAction(e -> handleMoveEvent());
		
		clickEvent = new CheckBox("Click Behaviour");
		//clickEvent.setOnAction(e -> handleClickEvent());
		
		timeEvent = new CheckBox("Timed Behaviour");
		//timeEvent.setOnAction(e -> handleTimeEvent());
	
		allEvents.add(keyEvent);
		allEvents.add(moveEvent);
		allEvents.add(clickEvent);
		allEvents.add(timeEvent);
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}
	
//	public void setGameObject(GameObject o) {
//		this.gameObject = o;
//	} 
	
	
	public static ArrayList<Strategy> getSelectedEvents() {
		return selectedEvents;
	}
	
	private void handleKeyEvent() {
		if(!selectedEvents.contains(keyBehaviour)) {
			selectedEvents.add(keyBehaviour);
		} else {
			selectedEvents.remove(keyBehaviour);
		}
	}
	
	private void handleMoveEvent() {
		if(!selectedEvents.contains(moveBehaviour)) {
			selectedEvents.add(moveBehaviour);
		} else {
			selectedEvents.remove(moveBehaviour);
		}
	}
	
	private void handleTimeEvent() {
		if(!selectedEvents.contains(timeBehaviour)) {
			selectedEvents.add(timeBehaviour);
		} else {
			selectedEvents.remove(timeBehaviour);
		}
	}
	
	public ArrayList<Strategy> getAllSelectedEvents() {
		return selectedEvents;
	}

	
	public  ArrayList<CheckBox> getAllEvents() {
		return allEvents;
	}

}
