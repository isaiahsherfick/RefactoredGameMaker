package behaviors;

import org.json.simple.JSONObject;

import constants.Constants;
import sprite.Sprite;

public class MoveOnGameTickBehavior implements EventBehavior
{
	private double deltaX;
	private double deltaY;
	
	public MoveOnGameTickBehavior()
	{
		deltaX = Constants.DEFAULT_SPRITE_VELOCITY_X;
		deltaY = Constants.DEFAULT_SPRITE_VELOCITY_Y;
	}
	
	public MoveOnGameTickBehavior(double dx, double dy)
	{
		deltaX = dx;
		deltaY = dy;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","MoveOnGameTickBehavior");
		json.put("deltaX",deltaX);
		json.put("deltaY",deltaY);
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		deltaX = (double)saveJSON.get("deltaX");
		deltaY = (double)saveJSON.get("deltaY");
	}

	@Override
	public void onMousePress(Sprite sprite) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameStart(Sprite sprite) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMove(Sprite sprite) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(Sprite sprite) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameTick(Sprite sprite) 
	{
		double x = sprite.getX();
		double y = sprite.getY();
		sprite.setX(x + deltaX);
		sprite.setY(y + deltaY);
	}

	@Override
	public void onLevelIncrease(Sprite sprite) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public EventBehavior copy() 
	{
		return new MoveOnGameTickBehavior(deltaX, deltaY);
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof MoveOnGameTickBehavior)
		{
			MoveOnGameTickBehavior m = (MoveOnGameTickBehavior)o;
			return deltaX == m.getDeltaX() && deltaY == m.getDeltaY();
		}
		return false;
	}

	private double getDeltaY() 
	{
		return deltaY;
	}

	private double getDeltaX() 
	{
		return deltaX;
	}
}
