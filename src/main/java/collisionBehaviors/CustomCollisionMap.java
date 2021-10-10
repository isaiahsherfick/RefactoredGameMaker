package collisionBehaviors;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import saveandload.Saveable;
import sprite.Sprite;

public class CustomCollisionMap implements Saveable
{
	
	private HashMap<Integer, CollisionBehavior> collisionMap;
	private CollisionBehavior defaultCollisionBehavior;
	
	public CustomCollisionMap()
	{
		collisionMap = new HashMap<>();
		defaultCollisionBehavior = new DoNothingCollisionBehavior();
	}
	
	public int size()
	{
		return collisionMap.size();
	}
	
	public void setDefaultCollisionBehavior(CollisionBehavior newDefault)
	{
		defaultCollisionBehavior = newDefault;
	}
	
	public void put(int spriteId, CollisionBehavior customCollision)
	{
		collisionMap.put(spriteId, customCollision);
	}
	
	public CollisionBehavior get(int spriteId)
	{
		CollisionBehavior result = collisionMap.get(spriteId);
		if (result == null)
		{
			return defaultCollisionBehavior;
		}
		return result;
	}

	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type", "CustomCollisionMap");
		json.put("size", size());
		
		int i  = 0;
		for (Entry e : collisionMap.entrySet())
		{
			CustomCollisionPair currentPair = new CustomCollisionPair(e.getKey(), e.getValue());
			json.put(i++, currentPair.save());
		}
		
		return json;
	}

	public void load(JSONObject saveJSON) 
	{
		// TODO Auto-generated method stub
	}

	//Will be the method actually called by Sprite on a collision
	public void collide(Sprite collidee, int colliderId) 
	{
		//Loop through the ids stored in the map
		for (Integer key : collisionMap.keySet())
		{
			//If we have an entry for the thing we're colliding with
			if (key == colliderId)
			{
				//Call that entry's collide method
				collisionMap.get(key).collide(collidee,colliderId);
			}
		}
	}
}
