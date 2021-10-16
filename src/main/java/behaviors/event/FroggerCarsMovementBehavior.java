package behaviors.event;

import org.json.simple.JSONObject;

import constants.Constants;
import javafx.scene.input.KeyCode;
import sprite.Sprite;

public class FroggerCarsMovementBehavior implements MovementEventBehavior{

	private int xVelocity;
	
	public FroggerCarsMovementBehavior() {
		xVelocity = Constants.DEFAULT_SPRITE_VELOCITY_X;
	}
	
	public FroggerCarsMovementBehavior(int dx) {
		xVelocity = dx;
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
		int x = (int)sprite.getX();
		sprite.setX(x + xVelocity);
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
	public void setXVelocity(int x) {
		// TODO Auto-generated method stub
		xVelocity = x;
	}

	@Override
	public int getXVelocity() {
		// TODO Auto-generated method stub
		return xVelocity;
	}

	@Override
	public void setYVelocity(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getYVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
