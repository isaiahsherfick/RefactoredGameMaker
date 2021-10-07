//@Author Christian Dummer
package collisionBehaviors;

import behaviors.CollisionBehavior;
import behaviors.MoveBehavior;
import strategies.Strategy;

//TODO this needs to change to adapt to an overhaul of movement to not be stuck in 8 direction.
public class BounceOffScreen extends CollisionBehavior{

	
	public BounceOffScreen() {
		super();
	}
	@Override 
	public void run() {
		System.out.println("Direction flipped");
		for(Strategy s: getSprite().getBehaviors()) {
			if(s instanceof MoveBehavior) {
				((MoveBehavior) s).flipDirection();
			}
		}
	}
	
	@Override
	public String getName() {
		return("Bounce Off Screen");
	}
}
