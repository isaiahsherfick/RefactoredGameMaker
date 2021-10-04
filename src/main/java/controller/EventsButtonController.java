package controller;

import java.util.ArrayList;

import behaviors.*;
import collisionBehaviors.BlowUp;
import collisionBehaviors.Bounce;
import collisionBehaviors.BounceOffScreen;
import collisionBehaviors.FallDown;
import javafx.scene.control.CheckBox;
import movementBehaviors.*;
import strategies.Fire;
import strategies.Strategy;
import game.engine.GameObject;

/*
 * Requires a Game Object (Sprite) for which the events will be set.
 */
public class EventsButtonController {

	CheckBox keyEvent;
	CheckBox moveEvent;
	CheckBox clickEvent;
	CheckBox timeEvent;

	public static ArrayList<Strategy> allEvents;
	// selected events for the gameObject
	public static ArrayList<Strategy> selectedEvents = new ArrayList<Strategy>();
	private GameObject gameObject;	
	private KeyBehavior keyBehaviour;
	private MoveBehavior moveBehaviour;
	private TimedBehavior timeBehaviour;
	
	public EventsButtonController() {
		//gameObject = new GameObject();
		allEvents = new ArrayList<Strategy>();
		//selectedEvents = new ArrayList<Strategy>();
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
	/*
		allEvents.add(keyEvent);
		allEvents.add(moveEvent);
		allEvents.add(clickEvent);
		allEvents.add(timeEvent); */
		allEvents.add(new BlowUp(gameObject));
		allEvents.add(new FallDown(gameObject));
		allEvents.add(new LeftAndRightMovement(gameObject));
		allEvents.add(new UpAndDownMovement(gameObject));
		allEvents.add(new VariableMovement(gameObject));
		allEvents.add(new BounceOffScreen());
		allEvents.add(new Bounce());
		
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}
	
//	public void setGameObject(GameObject o) {
//		this.gameObject = o;
//	} 
	
	public void addSelectedEvent(Strategy s) {
		selectedEvents.add(s);
		System.out.println("Event added to selected events");
	}
	
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
	
	public static ArrayList<Strategy> getAllSelectedEvents() {
		return selectedEvents;
	}

	
	public ArrayList<Strategy> getAllEvents() {
		return allEvents;
	}
	
	public ArrayList<Strategy> getEventType(ClickBehavior c){
		ArrayList<Strategy> events = new ArrayList<Strategy>();
		for(Strategy e: allEvents) {
			if(e instanceof ClickBehavior) {
				events.add(e);
			}
		}
		return events;
	}
	
	public ArrayList<Strategy> getEventType(CollisionBehavior c){
		ArrayList<Strategy> events = new ArrayList<Strategy>();
		for(Strategy e: allEvents) {
			if(e instanceof CollisionBehavior) {
				events.add(e);
			}
		}
		events.add(new CollisionBehavior());
		return events;
	}
	
	public ArrayList<Strategy> getEventType(KeyBehavior k){
		ArrayList<Strategy> events = new ArrayList<Strategy>();
		for(Strategy e: allEvents) {
			if(e instanceof KeyBehavior) {
				events.add(e);
			}
		}
		events.add(new KeyBehavior());
		return events;
	}
	
	public ArrayList<Strategy> getEventType(MoveBehavior m){
		ArrayList<Strategy> events = new ArrayList<Strategy>();
		for(Strategy e: allEvents) {
			if(e instanceof MoveBehavior) {
				events.add(e);
			}
		}
		return events;
	}
	
	public ArrayList<Strategy> getEventType(TimedBehavior t){
		ArrayList<Strategy> events = new ArrayList<Strategy>();
		for(Strategy e: allEvents) {
			if(e instanceof TimedBehavior) {
				events.add(e);
			}
		}
		events.add(t);
		return events;
	}
	
	public ArrayList<Strategy> getTimeableEvents(){
		ArrayList<Strategy> events = new ArrayList<Strategy>();
		for(Strategy e: allEvents) {
			if(e instanceof MoveBehavior) {
				events.add(e);
			}
		}
		events.add(new Fire(gameObject));
		return events;
	}
	
	public ArrayList<Strategy> getKeyEvents(){
		ArrayList<Strategy> events = new ArrayList<Strategy>();
		for(Strategy e: allEvents) {
			if(e instanceof MoveBehavior) {
				events.add(e);
			}
		}
		events.add(new Fire(gameObject));
		return events;
	}
	
}
