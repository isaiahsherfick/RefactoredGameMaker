package collisionUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import game.engine.GameObject;

final public class CollisionDetection {

	public static List<GameObject> gameObjects = new ArrayList<>();
	public static CollisionDetection shared = new CollisionDetection();
	public static Scene scene;
	// Hash map for storing the collided objects of type GameObjects.
	private HashMap<GameObject, GameObject> collidedObjects = new HashMap<GameObject, GameObject>();

	private CollisionDetection() {
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void checkForCollision() {
		for (GameObject obj1 : gameObjects) {
			for (GameObject obj2 : gameObjects) {

				// Skip same objects
				if (obj1 == obj2) {
					continue;
				}
				checkObjectCollision(obj1, obj2);
			}
			checkScreenBoundsCollision(obj1);
		}
		// clear collided Objects List
		collidedObjects.clear();
	}

	public void addGameObject(GameObject object) {
		if (!gameObjects.contains(object)) {
			gameObjects.add(object);
		}
	}

	public void removeGameObject(GameObject object) {
		// Ensure object is already in the gameObjects array list
		int objectIndex = gameObjects.indexOf(object);
		if (objectIndex >= 0) {
			gameObjects.remove(object);
		}
	}

	public void clearGameObject() {
		gameObjects.clear();
	}

	private void checkObjectCollision(GameObject object1, GameObject object2) {
		// get the objects position

		// if bounds collide
		if (object1.getUpperRight(object1.getPosition()).getX() >= object2.getUpperLeft(object2.getPosition()).getX()
				&& object1.getUpperLeft(object1.getPosition()).getX() >= object2.getUpperRight(object2.getPosition())
						.getX()
				&& object1.getLowerLeft(object1.getPosition()).getY() >= object2.getUpperLeft(object2.getPosition())
						.getY()
				&& object1.getUpperLeft(object1.getPosition()).getY() <= object2.getLowerLeft(object2.getPosition())
						.getY()) {
			// handle all type of collision
			handleCollision(object1, object2);
		}
	}

	private void handleCollision(GameObject object1, GameObject object2) {
		// Break if we already dealt with this collision
		// Due to the nature of the double loop it is possible to detect a collision
		// twice in one tick()
		if ((collidedObjects.containsKey(object1) && collidedObjects.get(object1) == object2)
				|| (collidedObjects.containsKey(object2) && collidedObjects.get(object2) == object1)) {
			return;
		} else {
			collidedObjects.put(object1, object2);
			String collisionDirection = determineCollisionDirection(object1, object2);
			object1.handleObjectCollision(object2, collisionDirection);
			object2.handleObjectCollision(object1, collisionDirection);
		}
	}

	private String determineCollisionDirection(GameObject object1, GameObject object2) {
		Point2D object1PreviousPosition = object1.getPreviousPosition();
		Point2D object1Position = object1.getNextPosition();
		Point2D object1Dimensions = object1.getSize();
		Point2D object2Position = object2.getPosition();
		Point2D object2Dimensions = object2.getSize();

		if ((object1PreviousPosition.getX() + object1Dimensions.getX()) < object2Position.getX()
				&& (object1Position.getX() + object1Dimensions.getX()) >= object2Position.getX()) {
			return "LEFT";
		}

		if (object1PreviousPosition.getX() >= (object2Position.getX() + object2Dimensions.getX())
				&& object1Position.getX() < (object2Position.getX() + object2Dimensions.getX())) {
			return "RIGHT";
		}

		if ((object1PreviousPosition.getY() + object1Dimensions.getY()) < object2Position.getY()
				&& (object1Position.getY() + object1Dimensions.getY()) >= object2Position.getY()) {
			return "TOP";
		}

		if (object1PreviousPosition.getY() >= (object2Position.getY() + object2Dimensions.getY())
				&& object1Position.getY() < (object2Position.getY() + object2Dimensions.getY())) {
			return "BOTTOM";
		}

		return "ERROR";
	}

	private void checkScreenBoundsCollision(GameObject object) {
		Point2D nextPosition = object.getNextPosition();
		Point2D dimensions = object.getSize();
		double positionX = nextPosition.getX();
		double positionY = nextPosition.getY();
		Point2D velocity = object.getSize();

		// Horizontal Scene Check
		// if moving left and beyond pixel 0 - dont move
		if (velocity.getX() < 0 && nextPosition.getX() < 0) {
			positionX = 0;
		}
		// if moving right and beyond scene width - dont move
		else if (velocity.getX() > 0 && (nextPosition.getX() + dimensions.getX()) >= scene.getWidth()) {
			positionX = scene.getWidth() - dimensions.getX();
		}

		// Vertical Scene Check
		// if moving up and beyond pixel 0 - dont move
		if (velocity.getY() < 0 && nextPosition.getY() < 0) {
			positionY = 0;
		}
		// if moving down and beyond scene height - dont move
		else if (velocity.getY() > 0 && (nextPosition.getY() + dimensions.getY()) >= scene.getHeight()) {
			positionY = scene.getHeight() - dimensions.getY();
		}

		object.handleScreenCollision(new Point2D(positionX, positionY));
	}

}
