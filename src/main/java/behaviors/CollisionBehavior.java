//@Author Christian Dummer
package behaviors;

import sound.NoSound;
import sound.Sound;
import sprite.Sprite;
import strategies.Strategy;

public class CollisionBehavior implements Strategy {
	//Sets default to no sound to avoid NPEs
	private Sound soundEffect = new NoSound();
	
	//TODO CollisionBehaviors should not contain sprites, they should be contained BY sprites
	//-Isaiah
	private Sprite sprite;
	public CollisionBehavior() {
		
	}
	public CollisionBehavior(Sprite o) {
		sprite = o;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public void setSprite(Sprite o) {
		this.sprite = o;
	}
	
	public void run() {
		//TODO
		//So behaviors contain sprites which contain behaviors which contain sprites which....
		//That definitely calls for something to change
		//Similar to what Maazin talked about in one of his recent presentations; circular structure -> not good
		for(Strategy s: getSprite().getBehaviors()) {
			if(s instanceof MoveBehavior) {
				((MoveBehavior) s).flipDirection();
			}
		}
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
