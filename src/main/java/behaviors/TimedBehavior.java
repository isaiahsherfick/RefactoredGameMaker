//@Author Christian Dummer
package behaviors;

import sound.NoSound;
import sound.Sound;
import strategies.Strategy;
import view.GameObject;

public abstract class TimedBehavior implements Strategy {
	//Initializes nullObject to avoid NPEs
	private Sound soundEffect = new NoSound();
	private double tickInterval = 0;
	private GameObject sprite;
	
	public TimedBehavior(GameObject o, double interval) {
		this.sprite = o;
		this.tickInterval = interval;
	}
	public void run() {
		
	}
	public void adjustInterval(double interval) {
		this.tickInterval = interval;
	}
	
	public void addSound(Sound s) {
		this.soundEffect = s;
	}
	
	public void playSound() {
		soundEffect.playSound();
	}
}
