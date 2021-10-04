//@Author Christian Dummer
package collisionBehaviors;

import behaviors.CollisionBehavior;
import behaviors.MoveBehavior;
import strategies.Strategy;

public class BounceOffScreen extends CollisionBehavior{

	
	public BounceOffScreen() {
		super();
	}
	@Override 
	public void run() {
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
