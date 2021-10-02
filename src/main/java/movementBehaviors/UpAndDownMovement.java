//@Author Christian Dummer
package movementBehaviors;

import behaviors.MoveBehavior;
import javafx.geometry.Point2D;
import view.GameObject;

public class UpAndDownMovement extends MoveBehavior{
	private boolean direction;
	
	public UpAndDownMovement(GameObject o) {
		super(o);
	}
	
	public void flipDirection() {
		direction = !direction;
	}
	
	@Override
	public Point2D move() {
		Point2D currentPosition = getSprite().getPosition();
		if(!direction) {
			return new Point2D (currentPosition.getX(), currentPosition.getY() - 20);
		}
		else {
			return new Point2D (currentPosition.getX(), currentPosition.getY() + 20);
		}
	}
}


