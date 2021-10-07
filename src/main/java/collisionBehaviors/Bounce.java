package collisionBehaviors;

import behaviors.CollisionBehavior;

//TODO this needs to do anything and just reflect on collision. Needs actual collision events to run as a result of -Christian
public class Bounce extends CollisionBehavior{

	public Bounce() {
		
	}
	
	
	@Override
	public String getName() {
		return "Bounce";
	}
}
