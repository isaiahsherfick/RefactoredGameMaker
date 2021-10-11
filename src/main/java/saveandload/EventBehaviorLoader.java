package saveandload;

import org.json.simple.JSONObject;

import behaviors.DoNothingBehavior;
import behaviors.EventBehavior;
import behaviors.MoveOnGameTickBehavior;

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
				//TODO destroybehavior
			default:
				System.out.println("BehaviorLoader was given something it doesn't have a case for: " + type);
				return new DoNothingBehavior();
		}
	}
}
