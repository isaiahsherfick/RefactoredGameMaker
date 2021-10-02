//@Author Christian Dummer
package collisionBehaviors;

import java.util.ArrayList;

import behaviors.CollisionBehavior;
import sound.Sound;
import view.GameObject;

//Provides an interface to run multiple collision behaviors 
public class MultipleCollisions extends CollisionBehavior{

	public MultipleCollisions(GameObject o) {
		super(o);
	}

}
