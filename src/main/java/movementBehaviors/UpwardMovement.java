//@Author Christian Dummer
package movementBehaviors;

import behaviors.MoveBehavior;
import javafx.geometry.Point2D;
import view.GameObject;

public class UpwardMovement extends MoveBehavior {

	public UpwardMovement(GameObject o) {
		super(o);
	}
	
	@Override
	public Point2D move() {
		Point2D currentPosition = getSprite().getPosition();
		return new Point2D((int)currentPosition.getX(), currentPosition.getY() - 20);
	}

}
