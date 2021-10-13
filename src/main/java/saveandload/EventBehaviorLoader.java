package saveandload;

import org.json.simple.JSONObject;

import behaviors.collision.DestroyCollisionBehavior;
import behaviors.event.DoNothingBehavior;
import behaviors.event.EventBehavior;
import behaviors.event.MoveOnGameTickBehavior;

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
