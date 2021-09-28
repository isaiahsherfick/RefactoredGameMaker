package behaviours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.Scene;

final public class CollisionDetection {
	
	private List<String> gameObjects = new ArrayList<>();
	public static CollisionDetection shared = new CollisionDetection();
	private static Scene scene;
	// Hash map for storing the collided objects of type GameObjects.
	private HashMap<String, String> collidedObjects = new HashMap<String, String>();
	
	private CollisionDetection() {}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public void checkForCollision() {
		for (String obj1: gameObjects) {
			for (String obj2: gameObjects) {
				
				// Skip same objects
				if (obj1 == obj2) {
					continue;
				}
				checkObjectCollision(obj1, obj2);
			}
		}
	}
	
	public void addGameObject(String object) {
		if(!gameObjects.contains(object)) {
			gameObjects.add(object);
		}
    }
    
    public void removeGameObject(String object) {
		// Ensure DrawBehavior is already registered
		int objectIndex = gameObjects.indexOf(object);
		if (objectIndex >= 0) {
			gameObjects.remove(object);
		}
    }
    
    
    public void clearGameObject() {
    	gameObjects.clear();
    }
    
	
	private void checkObjectCollision(String object1, String object2) {
		// get the objects position
		
		// if bounds collide
		
		handleCollision();
		
		// clear collided Objects List
		collidedObjects.clear();
	}
	
	private void handleCollision() {
		
	}
	

}
