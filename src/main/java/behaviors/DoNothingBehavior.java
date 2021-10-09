package behaviors;

import org.json.simple.JSONObject;

public class DoNothingBehavior implements EventBehavior
{
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","DoNothingBehavior");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		
	}

	@Override
	public void onMousePress() 
	{
		//Do nothing
	}

	@Override
	public void onGameStart() 
	{
		//Do nothing
	}

	@Override
	public void onMouseMove() 
	{
		//Do nothing
	}

	@Override
	public void onKeyPress() 
	{
		//Do nothing
	}

	@Override
	public void onGameTick() 
	{
		//Do nothing
	}

	@Override
	public void onLevelIncrease() 
	{
		//Do nothing
	}
}
