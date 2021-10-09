package behaviors;

import org.json.simple.JSONObject;

import sprite.Sprite;

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
	public void onMousePress(Sprite sprite) 
	{
		//Do nothing
	}

	@Override
	public void onGameStart(Sprite sprite) 
	{
		//Do nothing
	}

	@Override
	public void onMouseMove(Sprite sprite) 
	{
		//Do nothing
	}

	@Override
	public void onKeyPress(Sprite sprite) 
	{
		//Do nothing
	}

	@Override
	public void onGameTick(Sprite sprite) 
	{
		//Do nothing
	}

	@Override
	public void onLevelIncrease(Sprite sprite) 
	{
		//Do nothing
	}
}
