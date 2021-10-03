//@Author Christian Dummer
package behaviors;

import sound.NoSound;
import sound.Sound;
import strategies.Strategy;
import game.engine.GameObject;

public class CollisionBehavior implements Strategy {
	//Sets default to no sound to avoid NPEs
	private Sound soundEffect = new NoSound();
	private GameObject sprite;
	public CollisionBehavior() {
		
	}
	public CollisionBehavior(GameObject o) {
		sprite = o;
	}
	
	public GameObject getSprite() {
		return this.sprite;
	}
	
	public void run() {
		
	}
	
	public void addSound(Sound s) {
		this.soundEffect = s;
	}
	
	public void playSound() {
		soundEffect.playSound();
	}
	
	public String getName() {
		return "Collision Behaviour";
	}
}
