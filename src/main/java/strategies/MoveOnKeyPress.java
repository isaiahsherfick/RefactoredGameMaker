//@Author Christian Dummer
package strategies;

import javafx.geometry.Point2D;
import javafx.scene.media.*;
import sound.Sound;

public class MoveOnKeyPress implements Strategy{

	private Sound soundEffect;
	
	public void run(Object gameObject) {
	}

	public void attachSound(Sound sound) {
		this.soundEffect = sound;
	}

	public Point2D runMoveBehavior(Object objectToMove, Point2D currentPosition, double direction) {
		// TODO Auto-generated method stub
		soundEffect.playSound();
		return null;
	}

}
