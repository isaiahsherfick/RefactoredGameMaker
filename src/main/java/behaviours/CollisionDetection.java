package behaviours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.Scene;

final public class CollisionDetection {
	
	private List<Object> gameObjects = new ArrayList<>();
	public static CollisionDetection shared = new CollisionDetection();
	private static Scene scene;
	// Hash map for storing the collided objects of type GameObjects.
	private HashMap<Object, Object> collidedObjects = new HashMap<Object, Object>();
	
	private CollisionDetection() {}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public void checkForCollision() {
		for (Object obj1: gameObjects) {
			for (Object obj2: gameObjects) {
				
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
    
	
	private void checkObjectCollision(Object object1, Object object2) {
		// get the objects position
		
		// if bounds collide
		
		handleCollision(object1,object2);
		
		// clear collided Objects List
		collidedObjects.clear();
	}
	
	private void handleCollision(Object object1, Object object2) {
		// Break if we already dealt with this collision
    	// Due to the nature of the double loop it is possible to detect a collision twice in one tick()
    	if((collidedObjects.containsKey(object1) && collidedObjects.get(object1) == object2) ||
    			(collidedObjects.containsKey(object2) && collidedObjects.get(object2) == object1))
    	{
    		return;
    	} else {
        	collidedObjects.put(object1, object2);
        	String collisionDirection = determineCollisionDirection(object1, object2);
        	//object1.handleObjectCollision(object2, collisionDirection);
        	//object2.handleObjectCollision(object1, collisionDirection);
    	}
	}
	
	 private String determineCollisionDirection(Object object1, Object object2) {
		return "";

	 }
	    
	
	
	

}
