//@Author Christian Dummer
package strategies;

import javafx.geometry.Point2D;
import sound.Sound;

public interface Strategy {
	
	//Runs the move algorithm specified by implemented strategies
	public Point2D runMoveBehavior(Object objectToMove, Point2D currentPosition, double direction);
	
	//Runs more generalized strategies
	public void run(Object gameObject);
	
	//Attaches a sound effect to given action
	public void attachSound(Sound sound);

}
