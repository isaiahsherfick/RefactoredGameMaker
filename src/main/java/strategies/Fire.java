package strategies;

import behaviors.KeyBehavior;
import javafx.geometry.Point2D;
import sound.Sound;
import view.GameObject;

public class Fire extends KeyBehavior {
	
	public Fire(GameObject o) {
		super(o);
	}

	private Sound sound;

	@Override
	public void run() {
		// Something like GameObject.fire()
		//Alternatively, create a gameObject, and then assign it a new move up behavior and collision behavior to blow up
		sound.playSound();
	}

}
