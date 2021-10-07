package model;

import java.util.ArrayList;
import java.util.List;

import collisionUtility.CollisionDetection;
import game.engine.GameObject;

public class GameModel {
	
	//TODO
	//This model doesn't do anything. It's literally just an arraylist
	//At least it will be easy to build up a good model around it
	//-Isaiah
	private List<GameObject> gameObjects;
	
	public GameModel() {
		gameObjects = new ArrayList<>();
	}
	
	public List<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public void addNewGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
		CollisionDetection.gameObjects.add(gameObject);
	}
	
	public void removeGameObject(GameObject gameObject) {
		gameObjects.remove(gameObject);
		CollisionDetection.gameObjects.remove(gameObject);
	}
}
