//@Author Christian Dummer
package behaviors;

import javafx.geometry.Point2D;
import sound.NoSound;
import sound.Sound;
import strategies.Strategy;
import view.GameObject;

public abstract class MoveBehavior implements Strategy {
	//Initializes as null object to avoid npes
	private Sound soundEffect = new NoSound();
	private GameObject sprite;
	public MoveBehavior(GameObject o) {
		sprite = o;
	}
	
	public void run() {
		Point2D next = move();
		sprite.setPosition((int)next.getX(), (int)next.getY());
	}
	
	public Point2D move() {
		return new Point2D(0, 0);
	}
	
	public void addSound(Sound s) {
		this.soundEffect = s;
	}
	
	public void playSound() {
		soundEffect.playSound();
	}
}
