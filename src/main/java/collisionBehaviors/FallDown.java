package collisionBehaviors;

import behaviors.CollisionBehavior;
import game.engine.GameObject;

//TODO
//I'm guessing this is a gravity standin?
//A lot of these collisionbehaviors will not require all too much modification
//most of the changes will be in the parent class and gameobject
//isaiah
public class FallDown extends CollisionBehavior{

	public FallDown(GameObject o) {
		super(o);
	}
	
	@Override
	public String getName() {
		return "Fall Down";
	}

}
