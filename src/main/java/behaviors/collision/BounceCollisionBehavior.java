package behaviors.collision;

import org.json.simple.JSONObject;

import sprite.Sprite;

public class BounceCollisionBehavior implements CollisionBehavior
{

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","BounceCollisionBehavior");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		
	}

	@Override
	public void collide(Sprite collidee, int colliderId) 
	{
		//TODO
		//Some kind of switch statement here to determine if we're dealing with top left, top center, top right, bottom left, etc so we can properly flip velocities
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof BounceCollisionBehavior);
	}

}
