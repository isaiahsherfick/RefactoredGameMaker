package behaviors.event;

import org.json.simple.JSONObject;

import javafx.scene.input.KeyCode;
import sprite.Sprite;

public class ChangeDirectionBehavior implements EventBehavior 
{
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
		int xSpeed = Math.abs(sprite.getXVelocity());
		int ySpeed = Math.abs(sprite.getYVelocity());
		
		switch(keyCode)
		{
			case UP:
				sprite.setXVelocity(0);
				sprite.setYVelocity(-1 * ySpeed);
				break;
			case W:
				sprite.setXVelocity(0);
				sprite.setYVelocity(-1 * ySpeed);
				break;
			case LEFT:
				sprite.setXVelocity(xSpeed * -1);
				sprite.setYVelocity(0);
				break;
			case A:
				sprite.setXVelocity(xSpeed * -1);
				sprite.setYVelocity(0);
				break;
			case RIGHT:
				sprite.setXVelocity(xSpeed);
				sprite.setYVelocity(0);
				break;
			case D:
				sprite.setXVelocity(xSpeed);
				sprite.setYVelocity(0);
				break;
			case DOWN:
				sprite.setXVelocity(0);
				sprite.setYVelocity(ySpeed);
				break;
			case S:
				sprite.setXVelocity(0);
				sprite.setYVelocity(ySpeed);
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
	public EventBehavior copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
