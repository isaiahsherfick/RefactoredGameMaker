//@Author Christian Dummer
package collisionBehaviors;

import behaviors.CollisionBehavior;
import game.engine.GameObject;

public class BlowUp extends CollisionBehavior{

	public BlowUp(GameObject o) {
		super(o);
	}
	
	public BlowUp() {
		super();
	}
	
	@Override
	public void run() {
		super.getSprite().disable();
		super.playSound();
	}
	
	@Override
	public String getName() {
		return "Blow Up";
	}




}
