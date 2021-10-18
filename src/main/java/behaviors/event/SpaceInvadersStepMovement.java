package behaviors.event;

import org.json.simple.JSONObject;

import constants.Constants;
import javafx.scene.input.KeyCode;
import sprite.Sprite;

public class SpaceInvadersStepMovement implements EventBehavior
{
	
	private int timeInterval;
	private double totalTime = 0;
	private int stepLeftOrRight = Constants.SPACE_INVADERS_STEP_LEFT;
	private boolean previousStepWasHorizontal = false;
	
	public SpaceInvadersStepMovement()
	{
		timeInterval = Constants.SPACE_INVADERS_MS_BETWEEN_STEPS;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type", "SpaceInvadersStepMovement");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		
	}

	@Override
	public void onMousePress(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameStart(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMove(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(Sprite sprite, KeyCode keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameTick(Sprite sprite) 
	{
		if (timeToMoveAgain((int)Constants.MS_BETWEEN_TICKS))
		{
			//move down
			if (previousStepWasHorizontal)
			{
				sprite.setY(sprite.getY() - sprite.getHeight());
			}
			else
			{
				if (stepLeftOrRight == Constants.SPACE_INVADERS_STEP_LEFT)
				{
					horizontalSteps++;
				}
				//step right then down
				else
				{
					
				}
				
			}
		}
	}

	@Override
	public void onLevelIncrease(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean timeToMoveAgain(int dt)
	{
		totalTime += dt;
		if( totalTime >= timeInterval)
		{
			totalTime = 0;
			return true;
		}
		return false;
	}

	@Override
	public EventBehavior copy() 
	{
		return new SpaceInvadersStepMovement();
	}
	
	public String toString()
	{
		return "Space Invaders step movement";
	}

}
