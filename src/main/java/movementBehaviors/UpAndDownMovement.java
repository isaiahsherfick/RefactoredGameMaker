//@Author Christian Dummer
package movementBehaviors;

import behaviors.MoveBehavior;
import javafx.geometry.Point2D;
import view.GameObject;

public class UpAndDownMovement extends MoveBehavior{
	
	public UpAndDownMovement(GameObject o) {
		super(o);
		setDirection(Direction.UP);
	}
	
	public UpAndDownMovement() {
		super();
	}
	
	//Moves either up or down
	@Override
	public Point2D move() {
		Point2D currentPosition = getSprite().getPosition();
		if(getDirection() == Direction.UP) {
			return new Point2D (currentPosition.getX(), currentPosition.getY() - (1*getSpeed()));
		}
		else {
			return new Point2D (currentPosition.getX(), currentPosition.getY() + (1*getSpeed()));
		}
	}
	
	@Override
	public String getName() {
		return "Up and Down Movement";
	}
}


