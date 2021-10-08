package sprite;

import java.util.HashMap;

import constants.Constants;

public class SpriteManager 
{
	private int currentHighestSpriteId;
	private HashMap<Integer,Sprite> spriteMap;
	private int size;
	
	//default constructor
	public SpriteManager()
	{
		spriteMap = new HashMap<>();
		currentHighestSpriteId = Constants.SPRITE_MANAGER_DEFAULT_ID;
		size = 0;
	}
	
	//Add a new sprite to the spritemanager
	public void add(Sprite sprite)
	{
		//We don't want to insert null sprites
		if (sprite.getSpriteId() == Constants.NULL_SPRITE_ID)
		{
			return;
		}
		
		if (sprite.getSpriteId() < currentHighestSpriteId)
		{
			currentHighestSpriteId++;
			sprite.setSpriteId(currentHighestSpriteId);
		}
		else
		{
			currentHighestSpriteId = sprite.getSpriteId();
		}
		spriteMap.put(sprite.getSpriteId(), sprite);
		size++;
	}
	
	//Remove a sprite from the spritemanager by its spriteid
	public void remove(int spriteId)
	{
		spriteMap.remove(spriteId);
		size--;
	}

	//Get a copy of the sprite at spriteId
	public Sprite get(int spriteId)
	{
		Sprite sprite = spriteMap.get(spriteId);
		if (sprite == null)
		{
			sprite = new NullSprite();
		}
		return sprite.copy();
	}
	
	public int getSize()
	{
		return size;
	}
}
