package behaviors.event;

import org.json.simple.JSONObject;

import javafx.scene.input.KeyCode;
import sprite.Sprite;

public class FroggerMovementBehavior implements EventBehavior{
	KeyCode up = KeyCode.UP;
	KeyCode down = KeyCode.DOWN;
	KeyCode left = KeyCode.LEFT;
	KeyCode right = KeyCode.RIGHT;
	
	@Override
	public JSONObject save() {
		// TODO Auto-generated method stub
		return null;
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
		if(keyCode == up) {
			sprite.setY(sprite.getY() + sprite.getHitBox().getHeight());
		}
		if (keyCode == left) {
			sprite.setX(sprite.getX() - sprite.getHitBox().getWidth());
		}
		if (keyCode == right) {
			sprite.setX(sprite.getX() + sprite.getHitBox().getWidth());
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
