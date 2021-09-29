package movementBehaviors;

import behaviors.MoveBehavior;
import javafx.geometry.Point2D;
import sound.Sound;

public class NoMove implements MoveBehavior {
	private Object objectReference;
	public NoMove(Object o) {
		//Does nothing
	}
	
	public Point2D move() {
		//Should return object referenced location (no movement)
		return null;
	}

	public void addSound(Sound s) {
		// TODO Auto-generated method stub
		
	}

	public void playSound() {
		// TODO Auto-generated method stub
		
	}

}
