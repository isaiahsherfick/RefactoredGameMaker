package behaviors.collision;

import org.json.simple.JSONObject;

import constants.Constants;
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
	//collidee 
	public void collide(Sprite collidee, int colliderId, int directionId) 
	{
		switch(directionId)
		{
			case Constants.TOP_CENTER:
				collidee.flipYVelocity();
				break;
			case Constants.TOP_LEFT:
				collidee.flipBothVelocities();
				break;
			case Constants.TOP_RIGHT:
				collidee.flipBothVelocities();
				break;
			case Constants.CENTER_LEFT:
				collidee.flipXVelocity();
				break;
			case Constants.CENTER_RIGHT:
				collidee.flipXVelocity();
				break;
			case Constants.BOTTOM_LEFT:
				collidee.flipBothVelocities();
				break;
			case Constants.BOTTOM_CENTER:
				collidee.flipYVelocity();
				break;
			case Constants.BOTTOM_RIGHT:
				collidee.flipBothVelocities();
				break;
			default:
				System.out.println(String.format("DirectionId problem in BounceCollisionBehavior for sprite #%d colliding with sprite #%d",collidee.getSpriteId(), colliderId));
		}
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof BounceCollisionBehavior);
	}

}
