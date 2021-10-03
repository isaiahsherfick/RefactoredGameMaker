//@Author Christian Dummer
package movementBehaviors;
import behaviors.MoveBehavior;
import behaviors.MoveBehavior.Direction;
import javafx.geometry.Point2D;
import game.engine.GameObject;

public class VariableMovement extends MoveBehavior{

	public VariableMovement(GameObject o) {
		super(o);
	}
	
	//Moves based on speed and direction
	@Override
	public Point2D move() {
		Point2D currentPosition = getSprite().getPosition();
		double x = currentPosition.getX();
		double y = currentPosition.getY();
		Point2D newPosition = new Point2D(currentPosition.getX(), currentPosition.getY());
		switch(getDirection()) {
			case UP: newPosition = new Point2D(x, y - (1 * getSpeed())); break;
			case DOWN: newPosition = new Point2D(x, y + (1 * getSpeed())); break;
			case LEFT: newPosition = new Point2D(x - (1* getSpeed()), y); break;
			case RIGHT: newPosition = new Point2D(x + (1* getSpeed()), y); break;
			case TOPLEFT:  newPosition = new Point2D(x - (1*getSpeed()), y - (1*getSpeed())); break;
			case TOPRIGHT: newPosition = new Point2D(x + (1*getSpeed()), y + (1*getSpeed())); break;
			case BOTTOMLEFT: newPosition = new Point2D(x - (1*getSpeed()), y + (1*getSpeed())); break;
			case BOTTOMRIGHT: newPosition = new Point2D(x + (1*getSpeed()), y - (1*getSpeed())); break;
		}
		return newPosition;
	}

	@Override
	public String getName() {
		return "Variable Movement";
	}
}
