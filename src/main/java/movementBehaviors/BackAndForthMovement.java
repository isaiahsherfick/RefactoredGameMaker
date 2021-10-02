//@Author Christian Dummer
package movementBehaviors;

import behaviors.MoveBehavior;
import behaviors.TimedBehavior;
import javafx.geometry.Point2D;
import javafx.scene.media.*;
import sound.Sound;
import view.GameObject;

public class BackAndForthMovement extends TimedBehavior {

	public BackAndForthMovement(GameObject o, double interval) {
		super(o, interval);
	}
}
