package behaviors.collision;

import org.json.simple.JSONObject;

public class CollisionBehaviorLoader 
{
	public static CollisionBehavior load(JSONObject json)
	{
		String type = (String)json.get("type");
		switch(type)
		{
			case "DoNothingCollisionBehavior":
				return new DoNothingCollisionBehavior();
				
			case "BounceCollisionBehaviorX":
				return new BounceCollisionBehaviorX();
			case "BounceCollisionBehaviorY":
				return new BounceCollisionBehaviorY();
			case "BounceCollisionBehaviorXY":
				return new BounceCollisionBehaviorXY();
				
			case "DestroyCollisionBehavior":
				return new DestroyCollisionBehavior();
				
			case "PacManRandomCollisionBehaviour":
				return new PacManRandomCollisionBehaviour();
				
			case "StopCollisionBehavior":
				return new StopCollisionBehavior();
				
			case "MoveDownOnCollisionBehavior":
				return new MoveDownOnCollisionBehavior();
			case "MoveHorizontalOnCollisionBehavior":
				return new MoveHorizontalOnCollisionBehavior();
			case "MoveLeftOnCollisionBehavior":
				return new MoveLeftOnCollisionBehavior();
			case "MoveRightOnCollisionBehavior":
				return new MoveRightOnCollisionBehavior();

			default:
				System.out.println("CollisionBehaviorLoader was given something it doesn't know how to load : " + type);
				return new DoNothingCollisionBehavior();
		}
	}
}
