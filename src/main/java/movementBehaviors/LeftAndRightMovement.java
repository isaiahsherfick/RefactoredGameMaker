//@Author Christian Dummer
package movementBehaviors;

import behaviors.MoveBehavior;
import behaviors.TimedBehavior;
import javafx.geometry.Point2D;
import javafx.scene.media.*;
import sound.Sound;
import view.GameObject;

public class LeftAndRightMovement extends MoveBehavior {

	private boolean direction = false;
	public LeftAndRightMovement(GameObject o) {
		super(o);
	}
	public void flipDirection() {
		direction = !direction;
	}
	
	@Override
	public Point2D move() {
		Point2D currentPosition = getSprite().getPosition();
		if(!direction) {
			return new Point2D (currentPosition.getX() - 20, currentPosition.getY());
		}
		else {
			return new Point2D (currentPosition.getX() + 20, currentPosition.getY());
		}
	}
}
