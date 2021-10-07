package strategies;

import behaviors.KeyBehavior;
import behaviors.MoveBehavior.Direction;
import movementBehaviors.VariableMovement;
import sound.Sound;
import game.engine.GameEngine;
import game.engine.GameObject;

//TODO
//What even is this class?
//-Isaiah
//Idea behind this was to be able to shoot projectiles, never got finished and thus is useless, especially since 
//it relies on the garbage "controllers". Needs to be a behavior on key or click event, and be cleaner -Christian
public class Fire extends KeyBehavior {
	
	public Fire(GameObject o) {
		super(o);
	}
	
	public Fire() {
		
	}

	private Sound sound;

	@Override
	public void run() {
		// Something like GameObject.fire()
		//Alternatively, create a gameObject, and then assign it a new move up behavior and collision behavior to blow up'
		GameObject newObject = new GameObject();
		newObject.setPosition(getSprite().getPosition());
		VariableMovement fireBehavior = new VariableMovement();
		fireBehavior.setDirection(Direction.UP);
		fireBehavior.setSpeed(2);
		newObject.addBehavior(fireBehavior);
		GameEngine.sharedInstance.register(newObject);
		sound.playSound();
	}
	
	@Override
	public String getName() {
		return "Fire";
	}

}
