//@Author Christian Dummer
package collisionBehaviors;

import java.util.ArrayList;

import behaviors.CollisionBehavior;

//Provides an interface to run multiple collision behaviors 
public class MultipleCollisions implements CollisionBehavior{

	private ArrayList<CollisionBehavior> multipleBehaviors;
	
	public MultipleCollisions() {
		multipleBehaviors = new ArrayList<CollisionBehavior>();
	}
	
	public void handleCollision(Object o) {
		for(CollisionBehavior c: multipleBehaviors) {
			c.handleCollision(o);
		}
	}
	
	public void addBehavior(CollisionBehavior c) {
		multipleBehaviors.add(c);
	}

}
