package saveandload;

import org.json.simple.JSONObject;

import collisionBehaviors.DestroyCollisionBehavior;
import eventBehaviors.DoNothingBehavior;
import eventBehaviors.EventBehavior;
import eventBehaviors.MoveOnGameTickBehavior;

public class EventBehaviorLoader 
{
	public static EventBehavior load(JSONObject json)
	{
		String type = (String)json.get("type");
		
		switch(type)
		{
			case "DoNothingBehavior":
				return new DoNothingBehavior();
			case "MoveOnGameTickBehavior":
				MoveOnGameTickBehavior moveOnGameTickBehavior = new MoveOnGameTickBehavior();
				moveOnGameTickBehavior.load(json);
				return moveOnGameTickBehavior;
			default:
				System.out.println("BehaviorLoader was given something it doesn't have a case for: " + type);
				return new DoNothingBehavior();
		}
	}
}
