package behaviors.collision;

import org.json.simple.JSONObject;

import sprite.Sprite;

public class DestroyCollisionBehavior implements CollisionBehavior 
{

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","DestroyCollisionBehavior");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{

	}

	@Override
	public void collide(Sprite collidee, int colliderId, int directionId) 
	{
		collidee.destroy();
	}

	public boolean equals(Object o)
	{
		return (o instanceof DestroyCollisionBehavior);
	}
}
