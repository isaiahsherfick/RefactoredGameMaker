package behaviors.collision;

import org.json.simple.JSONObject;

import constants.Constants;
import sprite.Sprite;

public class StopCollisionBehavior implements CollisionBehavior
{
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","StopCollisionBehavior");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		
	}

	@Override
	public void collide(Sprite collidee, int colliderId) 
	{
		double collideeX = collidee.getX();
		double collideeY = collidee.getY();
		//Going right
		if (collidee.getXVelocity() > 0)
		{
			collidee.setX(collideeX - Constants.SPRITE_COLLISION_WARP_DISTANCE*2);
		}
		//Going left
		else if (collidee.getXVelocity() < 0)
		{
			collidee.setX(collideeX + Constants.SPRITE_COLLISION_WARP_DISTANCE*2);
		}
		//Going down
		else if (collidee.getYVelocity() > 0)
		{

			collidee.setX(collideeY - Constants.SPRITE_COLLISION_WARP_DISTANCE*2);
		}
		//going up
		else if (collidee.getYVelocity() < 0)
		{
			collidee.setX(collideeX + Constants.SPRITE_COLLISION_WARP_DISTANCE*2);
		}
		collidee.setXVelocity(0);
		collidee.setYVelocity(0);
	}
	
	public boolean equals(Object o)
	{
		return o instanceof StopCollisionBehavior;
	}
	
	public String toString()
	{
		return "Stop moving";
	}

}
