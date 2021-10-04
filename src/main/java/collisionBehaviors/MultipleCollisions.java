//@Author Christian Dummer
package collisionBehaviors;

import java.util.ArrayList;

import behaviors.CollisionBehavior;
import game.engine.GameObject;

//Provides an interface to run multiple collision behaviors 
public class MultipleCollisions extends CollisionBehavior{

	private ArrayList<CollisionBehavior> collisions;
	public MultipleCollisions(GameObject o) {
		super(o);
		collisions = new ArrayList<CollisionBehavior>();
	}
	
	@Override
	public void run() {
		for(CollisionBehavior c: collisions) {
			c.run();
		}
	}
	
	public void addCollisionBehavior(CollisionBehavior c) {
		collisions.add(c);
	}

}
