package behaviors.event;

import org.json.simple.JSONObject;

import input.KeyPolling;
import javafx.scene.input.KeyCode;
import sprite.Sprite;

public class SmoothMoveOnKeyPress implements EventBehavior{
	
	public SmoothMoveOnKeyPress() {
		//Does nothing atm
	}
	
	@Override
	public JSONObject save() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("type","SmoothMoveOnKeyPress");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) {
		// TODO Auto-generated method stub
		
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
	public void onKeyPress(Sprite sprite, KeyCode keyCode) 
	{
		switch (keyCode)
		{
			case W:
				sprite.setY(sprite.getY() - 5);
				break;
			case UP:
				sprite.setY(sprite.getY() - 5);
				break;
			case A:
				sprite.setX(sprite.getX() - 5);
				break;
			case LEFT:
				sprite.setX(sprite.getX() - 5);
				break;
			case D:
				sprite.setX(sprite.getX() + 5);
				break;
			case RIGHT:
				sprite.setX(sprite.getX() + 5);
				break;
		}
	}

	@Override
	public void onGameTick(Sprite sprite) {
	}

	@Override
	public void onLevelIncrease(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EventBehavior copy() {
		// TODO Auto-generated method stub
		return new SmoothMoveOnKeyPress();
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof SmoothMoveOnKeyPress)
		{
			SmoothMoveOnKeyPress m = (SmoothMoveOnKeyPress)o;
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "Smooth move on KeyPress";
	}
	
		

}
