package behaviors.collision;

import org.json.simple.JSONObject;


import sprite.Sprite;

public class MoveHorizontalOnCollisionBehavior implements CollisionBehavior
{

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","MoveHorizontalOnCollisionBehavior");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		
	}

	@Override
	public void collide(Sprite collidee, int colliderId) 
	{
		collidee.setYVelocity(0);
		collidee.setXVelocity(colliderId);
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof MoveHorizontalOnCollisionBehavior);
	}
	
	public String toString() {
		return "Move Horizontal On Collision";
	}

}
