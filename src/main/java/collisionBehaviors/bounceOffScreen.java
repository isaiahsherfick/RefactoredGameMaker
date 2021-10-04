//@Author Christian Dummer
package collisionBehaviors;

import behaviors.CollisionBehavior;
import behaviors.MoveBehavior;
import strategies.Strategy;

public class bounceOffScreen extends CollisionBehavior{

	@Override 
	public void run() {
		for(Strategy s: getSprite().getBehaviors()) {
			if(s instanceof MoveBehavior) {
				((MoveBehavior) s).flipDirection();
			}
		}
	}
}
