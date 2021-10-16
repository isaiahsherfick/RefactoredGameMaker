package behaviors.collision;

import org.json.simple.JSONObject;


import sprite.Sprite;

public class BounceCollisionBehaviorX implements CollisionBehavior
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
		collidee.flipXVelocity();
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof BounceCollisionBehaviorX);
	}
	
	public String toString() {
		return "Bounce Horizontally";
	}

}
