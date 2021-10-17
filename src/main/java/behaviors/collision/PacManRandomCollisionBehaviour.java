package behaviors.collision;

import org.json.simple.JSONObject;

import sprite.Sprite;

public class PacManRandomCollisionBehaviour implements CollisionBehavior 
{

	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","PacManRandomCollisionBehaviour");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{

	}

	@Override
	public void collide(Sprite collidee, int colliderId) 
	{
		collidee.changeDirection();
	}
	
	public boolean equals(Object o)
	{
		return o instanceof DoNothingCollisionBehavior;
	}
}
