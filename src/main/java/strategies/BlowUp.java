package strategies;

import javafx.geometry.Point2D;
import javafx.scene.media.Media;
import sound.Sound;

public class BlowUp implements Strategy{

	private Sound soundEffect;

	public void run(Object gameObject) { //other behavior is void
		// TODO Auto-generated method stub
		soundEffect.playSound();
		
	}

	public void attachSound(Sound sound) {
		this.soundEffect = sound;
	}

	public Point2D runMoveBehavior(Object objectToMove, Point2D currentPosition, double direction) {
		// TODO Auto-generated method stub
		return null;
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

}
