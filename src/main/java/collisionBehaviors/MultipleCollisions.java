//@Author Christian Dummer
package collisionBehaviors;

import java.util.ArrayList;

import behaviors.CollisionBehavior;
import game.engine.Sprite;

//Provides an interface to run multiple collision behaviors 
public class MultipleCollisions extends CollisionBehavior{

    //TODO this doesn't make any sense
    //as it stands, each of these collisions in the array
    //will all contain a reference to the sprite
    //-Isaiah
	//This is redundant with how gameobjects were implement after this was made and doesnt need any references to sprites. 
	//Sprites should just contain a behavior chain for each event rather than need this class. -Christian
	private ArrayList<CollisionBehavior> collisions;
	public MultipleCollisions(Sprite o) {
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
