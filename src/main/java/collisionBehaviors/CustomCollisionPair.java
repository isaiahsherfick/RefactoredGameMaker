package collisionBehaviors;

import org.json.simple.JSONObject;

import constants.Constants;
import saveandload.Saveable;

public class CustomCollisionPair implements Saveable
{
	private Integer spriteId;
	private CollisionBehavior collisionBehavior;
	
	public CustomCollisionPair(int sid, CollisionBehavior cb)
	{
		spriteId = sid;
		collisionBehavior = cb;
	}
	
	public CustomCollisionPair() 
	{
		spriteId = Constants.DEFAULT_SPRITE_ID;
		collisionBehavior = new DoNothingCollisionBehavior();
	}
	
	int getSpriteId()
	{
		return spriteId;
	}
	
	CollisionBehavior getCollisionBehavior()
	{
		return collisionBehavior;
	}

	@SuppressWarnings("unchecked")
	public JSONObject save()
	{
		JSONObject json = new JSONObject();
		json.put("type","CustomCollisionPair");
		json.put("spriteId",spriteId);
		json.put("collisionBehavior",collisionBehavior.save());
		return json;
	}

	public void load(JSONObject json)
	{
		spriteId = ((Long)json.get("spriteId")).intValue();
		collisionBehavior = CollisionBehaviorLoader.load((JSONObject)json.get("collisionBehavior"));
	}
}
