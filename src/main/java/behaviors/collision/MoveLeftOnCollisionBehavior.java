package behaviors.collision;

import org.json.simple.JSONObject;


import sprite.Sprite;

public class MoveLeftOnCollisionBehavior implements CollisionBehavior
{

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","MoveLeftOnCollisionBehavior");
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
		collidee.setXVelocity(-2);
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof MoveLeftOnCollisionBehavior);
	}
	
	public String toString() {
		return "Move Left On Collision";
	}

}
