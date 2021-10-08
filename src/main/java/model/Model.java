package model;

import java.util.ArrayList;
import java.util.List;

import collisionUtility.CollisionDetection;
import sprite.Sprite;
import sprite.SpriteManager;

public class Model {
	
	private List<Sprite> sprites;
	
//	public Model() {
//		sprites = new ArrayList<>();
//	}
	
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
	
	//OLD STUFF ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^////
	
	
	private SpriteManager spriteManager;
	private String saveFilePath;

	public Model()
	{
		spriteManager = new SpriteManager();
	}
	
	public void addSprite(Sprite sprite)
	{
		spriteManager.add(sprite);
	}
	
	public Sprite getSprite(int spriteId)
	{
		return spriteManager.get(spriteId);
	}
	
	public void removeSprite(Sprite sprite)
	{
		spriteManager.remove(sprite.getSpriteId());
	}
	
	public ArrayList<Sprite> getSpriteList()
	{
		return spriteManager.getSpriteList();
	}
	
	public void resetSpriteManager()
	{
		spriteManager = new SpriteManager();
	}
	
	public void save()
	{
		//TODO
	}
	
	public void load()
	{
		//TODO
	}
	
	public String getSaveFilePath()
	{
		return saveFilePath;
	}
	public void setSaveFilePath(String path)
	{
		saveFilePath = path;
	}
}