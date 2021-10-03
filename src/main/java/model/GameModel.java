package model;

import java.util.ArrayList;
import java.util.List;

import game.engine.GameObject;

public class GameModel {
	
	private List<GameObject> gameObjects;
	
	public GameModel() {
		gameObjects = new ArrayList<>();
	}
	
	public List<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public void addNewGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}
	
	public void removeGameObject(GameObject gameObject) {
		gameObjects.remove(gameObject);
	}
}
