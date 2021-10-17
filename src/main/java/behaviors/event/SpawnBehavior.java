package behaviors.event;

import org.json.simple.JSONObject;

import constants.Constants;
import javafx.scene.input.KeyCode;
import model.Model;
import sprite.Sprite;

public class SpawnBehavior implements EventBehavior{
	
	private int timeInterval;
	private int totalTime = 0;
	
	private Model model = new Model();
	
	public SpawnBehavior() {
		timeInterval = Constants.DEFAULT_SPAWN_TIME_INTERVAL;
	}
	
	public SpawnBehavior(int dt) {
		timeInterval = dt;
	}
	
	//instance of model
	//add getter function to get model in controller
	
	@Override
	public JSONObject save() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("type","MoveOnGameTickBehavior");
		json.put("timeInterval",timeInterval);
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) {
		// TODO Auto-generated method stub
		timeInterval = (int)saveJSON.get("timeInterval");
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
		if(onTimeElapsed(totalTime,Constants.MS_BETWEEN_TICKS,timeInterval)) {
			Sprite copySprite = sprite.copy();
			model.addSprite(copySprite);
			copySprite.setHeight(copySprite.getHitBox().getHeight()*10);
			copySprite.setWidth(copySprite.getHitBox().getWidth()*10);
			copySprite.setX(0);
			copySprite.setY(0);
			model.modifySprite(sprite);
		}
		
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

	public int getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}
	
	private boolean onTimeElapsed(double totalTime, double dt, int timeInterval) {
		totalTime = totalTime + dt;
		if((totalTime) > timeInterval) {
			totalTime = 0;
			return true;
		}
		else {
			return false;
		}
		
	}

}
