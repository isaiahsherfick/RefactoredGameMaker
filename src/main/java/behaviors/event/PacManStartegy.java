package behaviors.event;

import org.json.simple.JSONObject;

import javafx.scene.input.KeyCode;
import sprite.Sprite;

public class PacManStartegy implements EventBehavior {
	 private KeyCode keyCode = KeyCode.SPACE;

	 public KeyCode getKeyCode()
	 {
	        return keyCode;
	 }
	 public void setKeyCode(KeyCode keyCode)
	 {
	        this.keyCode = keyCode;
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
	public void onKeyPress(Sprite sprite,  KeyCode keyCode)
	{
		

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
