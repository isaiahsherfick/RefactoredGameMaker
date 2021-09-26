package strategies;

import javafx.geometry.Point2D;
import javafx.scene.media.Media;
import sound.Sound;

public class BlowUp implements Strategy{

	private Sound soundEffect;
	
	public Point2D runMoveBehavior() {
		// TODO Auto-generated method stub
		soundEffect.playSound();
		return null;
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void attachSound(Sound sound) {
		this.soundEffect = sound;
	}

}
