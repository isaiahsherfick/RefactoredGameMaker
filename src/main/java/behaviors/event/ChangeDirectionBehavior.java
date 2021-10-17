package behaviors.event;

import org.json.simple.JSONObject;

import javafx.scene.input.KeyCode;
import sprite.Sprite;

public class ChangeDirectionBehavior implements EventBehavior 
{
	private Integer speed;
	private boolean speedSet = false;
	
	public ChangeDirectionBehavior()
	{
		
	}
	
	public ChangeDirectionBehavior(Integer speed, boolean speedSet)
	{
		this.speed = speed;
		this.speedSet = speedSet;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","ChangeDirectionBehavior");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{

	}

	@Override
	public void onMousePress(Sprite sprite) 
	{
	}

	@Override
	public void onGameStart(Sprite sprite) 
	{
	}

	@Override
	public void onMouseMove(Sprite sprite) 
	{
	}

	@Override
	public void onKeyPress(Sprite sprite, KeyCode keyCode) 
	{
		if (!speedSet)
		{
			int xSpeed = Math.abs(sprite.getXVelocity());
			int ySpeed = Math.abs(sprite.getYVelocity());
			speed = Math.max(xSpeed,ySpeed);
			speedSet = true;
		}
		System.out.println(speed);
		
		switch(keyCode)
		{
			case UP:
				sprite.setXVelocity(0);
				sprite.setYVelocity(-1 * speed);
				break;
			case W:
				sprite.setXVelocity(0);
				sprite.setYVelocity(-1 * speed);
				break;
			case LEFT:
				sprite.setXVelocity(speed * -1);
				sprite.setYVelocity(0);
				break;
			case A:
				sprite.setXVelocity(speed * -1);
				sprite.setYVelocity(0);
				break;
			case RIGHT:
				sprite.setXVelocity(speed);
				sprite.setYVelocity(0);
				break;
			case D:
				sprite.setXVelocity(speed);
				sprite.setYVelocity(0);
				break;
			case DOWN:
				sprite.setXVelocity(0);
				sprite.setYVelocity(speed);
				break;
			case S:
				sprite.setXVelocity(0);
				sprite.setYVelocity(speed);
				break;
			default:
				break;
		}
	}

	@Override
	public void onGameTick(Sprite sprite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLevelIncrease(Sprite sprite) {
		// TODO Auto-generated method stub

	}

	@Override
	public EventBehavior copy() 
	{
		return new ChangeDirectionBehavior(speed, speedSet);
	}
	
	public String toString()
	{
		return "Change Direction";
	}

}
