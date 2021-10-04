//@Author Christian Dummer
package movementBehaviors;

import behaviors.MoveBehavior;
import behaviors.TimedBehavior;
import javafx.geometry.Point2D;
import javafx.scene.media.*;
import sound.Sound;
import game.engine.GameObject;

public class LeftAndRightMovement extends MoveBehavior {
	
	public LeftAndRightMovement(GameObject o) {
		super(o);
	}
	
	public LeftAndRightMovement() {
		super();
	}
	
	//Moves either left or right
	@Override
	public Point2D move() {
		Point2D currentPosition = getSprite().getPosition();
		if(getDirection() == Direction.LEFT) {
			return new Point2D (currentPosition.getX() - (1*getSpeed()), currentPosition.getY());
		}
		else {
			return new Point2D (currentPosition.getX() + (1*getSpeed()), currentPosition.getY());
		}
	}
	
	@Override
	public String getName() {
		return "Left and Right Movement";
	}
}
