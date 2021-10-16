package behaviors.event;

import org.json.simple.JSONObject;

import javafx.scene.input.KeyCode;

import sprite.Sprite;

public class PacManStartegy implements EventBehavior {
	 private KeyCode keyCode = KeyCode.UP;
	 private MovementEventBehavior movement=new MoveOnGameTickBehavior();
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
		int currentYVelocity=0;
		int currentXVelocity=0;
		  if (this.keyCode.equals(KeyCode.UP))
	      {
			  currentYVelocity=movement.getYVelocity();
			  movement.setYVelocity(currentYVelocity-1); //add  to constants file
	           
	      }
		  else if (this.keyCode.equals(KeyCode.DOWN)) //add  to constants file
	      {
			  currentYVelocity=movement.getYVelocity();
			  movement.setYVelocity(currentYVelocity+1);//add  to constants file
	           
	      }
		  else if (this.keyCode.equals(KeyCode.RIGHT))
	      {
			  currentYVelocity=movement.getXVelocity();
			  movement.setYVelocity(currentXVelocity+1);//add  to constants file
	           
	      }
		  else if (this.keyCode.equals(KeyCode.LEFT))
	      {
			  currentYVelocity=movement.getXVelocity();
			  movement.setYVelocity(currentXVelocity-1);  //add  to constants file
	      }
		  
	        
	    }


	@Override
	public void onGameTick(Sprite sprite) {
		movement.onGameStart(sprite);

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
