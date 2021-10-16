package behaviors.collision;

import org.json.simple.JSONObject;

import sprite.Sprite;

public class DoNothingCollisionBehavior implements CollisionBehavior 
{

	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","DoNothingCollisionBehavior");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{

	}

	@Override
	public void collide(Sprite collidee, int colliderId, int directionId) 
	{
		//do nothing
	}
	
	public boolean equals(Object o)
	{
		return o instanceof DoNothingCollisionBehavior;
	}

}
