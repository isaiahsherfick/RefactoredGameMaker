package strategies;

import javafx.geometry.Point2D;
import sound.Sound;

public class Fire implements Strategy {
	
	private Sound sound;

	@Override
	public void run(Object gameObject) {
		// Something like GameObject.fire()
		sound.playSound();
	}

	/*
	 * Attach FireSound to this object.
	 */
	@Override
	public void attachSound(Sound sound) {
		// Attatching a sound to the fire event
		this.sound = sound;
	}

	@Override
	public Point2D runMoveBehavior(Object objectToMove, Point2D currentPosition, double direction) {
		// TODO Auto-generated method stub
		return null;
	}

}
