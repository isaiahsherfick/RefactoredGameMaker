package strategies;

import behaviors.KeyBehavior;
import behaviors.MoveBehavior.Direction;
import movementBehaviors.VariableMovement;
import sound.Sound;
import game.engine.GameEngine;
import game.engine.GameObject;

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
