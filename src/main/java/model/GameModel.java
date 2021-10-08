package model;

import java.util.ArrayList;
import java.util.List;

import collisionUtility.CollisionDetection;
import sprite.Sprite;

public class GameModel {
	
	//TODO
	//This model doesn't do anything. It's literally just an arraylist
	//At least it will be easy to build up a good model around it
	//-Isaiah
	//Easy enough to just make a model out of this, since this isn't even a model - Christian
	private List<Sprite> sprites;
	
	public GameModel() {
		sprites = new ArrayList<>();
	}
	
	public List<Sprite> getGameObjects() {
		return sprites;
	}
	
	public void addNewGameObject(Sprite sprite) {
		sprites.add(sprite);
		CollisionDetection.sprites.add(sprite);
	}
	
	public void removeGameObject(Sprite sprite) {
		sprites.remove(sprite);
		CollisionDetection.sprites.remove(sprite);
	}
}
