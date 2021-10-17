package behaviors.event;

import org.json.simple.JSONObject;

import input.KeyPolling;
import javafx.scene.input.KeyCode;
import sprite.Sprite;

public class FroggerMovementBehavior implements EventBehavior{
	
	public FroggerMovementBehavior() {
		//Does nothing atm
	}
	
	@Override
	public JSONObject save() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("type","FroggerMovementBehavior");
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
	public void onKeyPress(Sprite sprite, KeyCode keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameTick(Sprite sprite) {
		// TODO Auto-generated method stub
		if(KeyPolling.shared.isDown(KeyCode.W) || KeyPolling.shared.isDown(KeyCode.UP)) {
			sprite.setY(sprite.getY() - sprite.getHitBox().getHeight());
		}
		if (KeyPolling.shared.isDown(KeyCode.A)  || KeyPolling.shared.isDown(KeyCode.LEFT)) {
			sprite.setX(sprite.getX() - sprite.getHitBox().getWidth());
		}
		if (KeyPolling.shared.isDown(KeyCode.D)  || KeyPolling.shared.isDown(KeyCode.RIGHT)) {
			sprite.setX(sprite.getX() + sprite.getHitBox().getWidth());
		}
	}

	@Override
	public void onLevelIncrease(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EventBehavior copy() {
		// TODO Auto-generated method stub
		return new FroggerMovementBehavior();
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof FroggerMovementBehavior)
		{
			FroggerMovementBehavior m = (FroggerMovementBehavior)o;
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "Move on Arrow Keys";
	}
	
		

}
