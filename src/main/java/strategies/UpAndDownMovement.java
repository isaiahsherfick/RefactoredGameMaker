//@Author Christian Dummer
package strategies;

import javafx.geometry.Point2D;
import sound.Sound;

public class UpAndDownMovement implements Strategy{
	private Sound soundEffect;
	
	public Point2D runMoveBehavior(Object objectToMove, Point2D currentPosition, double direction) {
		soundEffect.playSound();
		if(currentPosition.getY() > 0 && direction > 0) {
			//TODO Flip object to move's direction
			return new Point2D(currentPosition.getX(), currentPosition.getY() + 25);
			
		}
		//TODO Adjust to actual screen height
		else if(currentPosition.getX() < 200 && direction < 0) {
			//TODO flip object to move's direction
			return new Point2D(currentPosition.getX(), currentPosition.getY() - 25);
		}
		return currentPosition;
	}

	public void run(Object gameObject) {
		// TODO Auto-generated method stub
		
	}

	public void attachSound(Sound sound) {
		// TODO Auto-generated method stub
		
	}

}
