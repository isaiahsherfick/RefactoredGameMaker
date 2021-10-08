package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import collisionUtility.CollisionDetection;
import constants.Constants;
import saveandload.SaveAndLoadManager;
import saveandload.Saveable;
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
	private SaveAndLoadManager saveAndLoadManager;
	private String saveFilePath;

	public Model()
	{
		spriteManager = new SpriteManager();
		saveAndLoadManager = new SaveAndLoadManager();
		saveFilePath = Constants.DEFAULT_SAVE_FILE_PATH;
	}
	
	public void addSprite(Sprite sprite)
	{
		spriteManager.add(sprite);
	}
	
	//pass this method a sprite which has been changed by the user
	//since the view never receives a direct reference to the sprite,
	//we now need to overwrite the one which is in the spritemanager with the changed sprite
	public void modifySprite(Sprite sprite)
	{
		spriteManager.modifySprite(sprite);
	}
	
	//Returns a copy of the sprite stored at spriteId in the spritemanager
	public Sprite getSprite(int spriteId)
	{
		return spriteManager.get(spriteId);
	}
	
	//Removes the sprite if the manager contains an entry at its id
	public void removeSprite(Sprite sprite)
	{
		spriteManager.remove(sprite.getSpriteId());
	}
	
	//get an arraylist of all sprites in the manager
	public ArrayList<Sprite> getSpriteList()
	{
		return spriteManager.getSpriteList();
	}
	
	//replace the sprite manager with a new one
	public void resetSpriteManager()
	{
		spriteManager = new SpriteManager();
	}
	
	//replace the save/load manager with a new one
	public void resetSaveAndLoadManager()
	{
		saveAndLoadManager = new SaveAndLoadManager();
	}
	
	//Save all sprites, write them to the file stored at saveFilePath
	//Catch the exception in controller
	public void save() throws IOException
	{
		ArrayList<Sprite> spriteList = spriteManager.getSpriteList();
		ArrayList<Saveable> saveableList = new ArrayList<>();
		for (Sprite s : spriteList)
		{
			saveableList.add((Saveable)s);
		}
		saveAndLoadManager.addAll(saveableList);
		saveAndLoadManager.saveFile(saveFilePath);
	}
	
	//Load all sprites from the file at saveFilePath
	//Catch the exceptions in controller
	//IOExeption : file can't be found
	//ParseException : JSON is bad
	public void load() throws IOException, ParseException
	{
		saveAndLoadManager.loadFile(saveFilePath);
		ArrayList<Sprite> spriteList = saveAndLoadManager.getSprites();
		resetSpriteManager();
		spriteManager.addAll(spriteList);
	}
	
	public String getSaveFilePath()
	{
		return saveFilePath;
	}
	
	public void setSaveFilePath(String path)
	{
		saveFilePath = path;
	}

	//Return the number of sprites in the system
	public int getNumberOfSprites()
	{
		return spriteManager.getSize();
	}
}