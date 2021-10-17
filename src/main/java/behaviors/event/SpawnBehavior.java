package behaviors.event;

import org.json.simple.JSONObject;

import constants.Constants;
import javafx.scene.input.KeyCode;
import model.Model;
import sprite.NullSprite;
import sprite.Sprite;

public class SpawnBehavior implements EventBehavior{
	
	private int timeInterval;
	private int totalTime = 0;
	private int spawnX = 0;
	private int spawnY = 0;
	private Sprite blueprint;
	
	private Model model;
	
	public SpawnBehavior(Model m)
	{
		model = m;
	}
	
	public SpawnBehavior() {
		//Only for use in dropdown
	}
	
	public Sprite getBlueprint()
	{
		if (blueprint == null)
			return new NullSprite();
		return blueprint;
	}
	
	public SpawnBehavior(Model m, int blueprintSpriteId) {
		timeInterval = Constants.DEFAULT_SPAWN_TIME_INTERVAL;
		model = m;
		blueprint = model.getSprite(blueprintSpriteId);
	}
	
	public SpawnBehavior(int timeInterval, int spawnX, int spawnY, Sprite blueprint, Model m)
	{
		this.timeInterval = timeInterval;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		this.blueprint = blueprint;
		model = m;
	}
	
	public void setBlueprintSprite(int blueprintSpriteId)
	{
		blueprint = model.getSprite(blueprintSpriteId);
	}
	
	public void setSpawnX(int x)
	{
		spawnX = x;
	}
	
	public void setSpawnY(int y)
	{
		spawnY = y;
	}
	
	public int getSpawnX()
	{
		return spawnX;
	}
	
	public int getSpawnY()
	{
		return spawnY;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","SpawnBehavior");
		json.put("timeInterval",timeInterval);
		json.put("spawnX", spawnX);
		json.put("spawnY",spawnY);
		json.put("blueprint",blueprint.save());
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		try
		{
			timeInterval = (int)saveJSON.get("timeInterval");
			spawnX = (int)saveJSON.get("spawnX");
			spawnY = (int)saveJSON.get("spawnY");
		}
		catch(ClassCastException e)
		{
			timeInterval = ((Long)saveJSON.get("timeInterval")).intValue();
			spawnX = ((Long)saveJSON.get("spawnX")).intValue();
			spawnY = ((Long)saveJSON.get("spawnY")).intValue();
		}
		blueprint = new Sprite();
		blueprint.load((JSONObject)saveJSON.get("blueprint"));
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
		if(onTimeElapsed(totalTime,Constants.MS_BETWEEN_TICKS,timeInterval)) {
			Sprite copySprite = sprite.copy();
			copySprite.setX(spawnX);
			copySprite.setY(spawnY);
			model.addSprite(copySprite);
		}
		
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof SpawnBehavior)
		{
			SpawnBehavior s = (SpawnBehavior)o;
			return s.getSpawnX() == spawnX && s.getSpawnY() == spawnY && timeInterval == s.getTimeInterval() && s.getBlueprint().equals(blueprint);
		}
		return false;
	}

	@Override
	public void onLevelIncrease(Sprite sprite) {
		
	}

	@Override
	public EventBehavior copy() {
		return new SpawnBehavior(timeInterval,spawnX,spawnY,blueprint,model);
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
