package behaviors.collision;

import org.json.simple.JSONObject;


import sprite.Sprite;

public class MoveDownOnCollisionBehavior implements CollisionBehavior
{

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","MoveDownOnCollisionBehavior");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		
	}

	@Override
	public void collide(Sprite collidee, int colliderId) 
	{
		collidee.setXVelocity(0);
		collidee.setYVelocity(1);
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof MoveDownOnCollisionBehavior);
	}
	
	public String toString() {
		return "Move Down On Collision";
	}

}
