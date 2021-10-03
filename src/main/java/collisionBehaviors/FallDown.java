package collisionBehaviors;

import behaviors.CollisionBehavior;
import game.engine.GameObject;

public class FallDown extends CollisionBehavior{

	public FallDown(GameObject o) {
		super(o);
	}
	
	@Override
	public String getName() {
		return "Fall Down";
	}

}
