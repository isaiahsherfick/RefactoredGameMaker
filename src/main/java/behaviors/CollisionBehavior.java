//@Author Christian Dummer
package behaviors;

import sound.NoSound;
import sound.Sound;
import strategies.Strategy;
import view.GameObject;

public abstract class CollisionBehavior implements Strategy {
	//Sets default to no sound to avoid NPEs
	private Sound soundEffect = new NoSound();
	private GameObject sprite;
	public CollisionBehavior(GameObject o) {
		sprite = o;
	}
	
	public void run() {
		
	}
	
	public void addSound(Sound s) {
		this.soundEffect = s;
	}
	
	public void playSound() {
		soundEffect.playSound();
	}
	
}
