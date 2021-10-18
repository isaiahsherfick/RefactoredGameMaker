package behaviors.event;

import org.json.simple.JSONObject;

import constants.Constants;
import javafx.scene.input.KeyCode;
import model.Model;
import sprite.Sprite;

public class SpawnOnKeyPressBehavior implements EventBehavior
{
	
	private Sprite blueprint = new Sprite(); //default sprite
	
	private Model model;
	
	public SpawnOnKeyPressBehavior(Model m)
	{
		model = m;
	}
	
	public SpawnOnKeyPressBehavior(Model m, Sprite blueprint)
	{
		model = m;
		this.blueprint = blueprint;
	}
	
	public SpawnOnKeyPressBehavior() {
		//Only for use in dropdown
	}
	
	public Sprite getBlueprint()
	{
		return blueprint;
	}
	
	public SpawnOnKeyPressBehavior(Model m, int blueprintSpriteId) 
	{
		model = m;
		blueprint = model.getSprite(blueprintSpriteId);
	}
	
	
	public void setBlueprintSprite(int blueprintSpriteId)
	{
		blueprint = model.getSprite(blueprintSpriteId);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","SpawnOnKeyPressBehavior");
		json.put("blueprint",blueprint.save());
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
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
	public void onKeyPress(Sprite sprite, KeyCode keyCode) 
	{
		if (keyCode == KeyCode.X)
		{
			double x = sprite.getX();
			double y = sprite.getY();
			Sprite newSprite = blueprint.copy();
			newSprite.setX(x);
			newSprite.setY(y - sprite.getHeight());
			model.addSprite(newSprite);
		}
		
	}

	@Override
	public void onGameTick(Sprite sprite) 
	{
		
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof SpawnOnKeyPressBehavior)
		{
			SpawnOnKeyPressBehavior s = (SpawnOnKeyPressBehavior)o;
			return blueprint.equals(s.getBlueprint());
		}
		return false;
	}

	@Override
	public void onLevelIncrease(Sprite sprite) {
		
	}

	@Override
	public EventBehavior copy() 
	{
		return new SpawnOnKeyPressBehavior(model,blueprint);
	}
	
	public String toString() 
	{
		return "Spawn a sprite on X";
	}

}
