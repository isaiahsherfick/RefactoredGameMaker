//@Author Christian Dummer
package strategies;

import javafx.geometry.Point2D;
import javafx.scene.media.*;
import sound.Sound;

public class BackAndForthMovement implements Strategy {

	private Sound soundEffect;
	
	public void run(Object gameObject) {
		// Nothing currently
	}

	public void attachSound(Sound sound) {
		this.soundEffect = sound;
	}

	//TODO Change Object to be defined gameObject type
	public Point2D runMoveBehavior(Object objectToMove, Point2D currentPosition, double direction) {
		soundEffect.playSound();
		if(currentPosition.getX() < 0 && direction < 0) {
			//TODO Flip object to move's direction
			return new Point2D(currentPosition.getX() + 25, currentPosition.getY());
			
		}
		//Adjust to screen bounds
		else if(currentPosition.getX() > 200 && direction > 0) {
			//TODO flip object to move's direction
			return new Point2D(currentPosition.getX() - 25, currentPosition.getY());
		}
		return currentPosition;
	
	}

}
