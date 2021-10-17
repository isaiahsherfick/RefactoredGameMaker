package behaviors.collision;

import org.json.simple.JSONObject;


import sprite.Sprite;

public class BounceCollisionBehaviorXY implements CollisionBehavior
{

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","BounceCollisionBehaviorXY");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		
	}

	@Override
	public void collide(Sprite collidee, int colliderId) 
	{
		collidee.flipBothVelocities();
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof BounceCollisionBehaviorXY);
	}
	
	public String toString() {
		return "Bounce Diagonally";
	}

}
