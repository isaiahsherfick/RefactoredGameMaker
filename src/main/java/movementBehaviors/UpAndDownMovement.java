//@Author Christian Dummer
package movementBehaviors;

import behaviors.MoveBehavior;
import behaviors.TimedBehavior;
import javafx.geometry.Point2D;
import sound.Sound;
import view.GameObject;

public class UpAndDownMovement extends TimedBehavior{

	public UpAndDownMovement(GameObject o, double interval) {
		super(o, interval);
	}

}
