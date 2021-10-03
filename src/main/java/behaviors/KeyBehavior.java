//@Author Christian Dummer
package behaviors;


import sound.NoSound;
import sound.Sound;
import strategies.Strategy;
import view.GameObject;

public class KeyBehavior implements Strategy {
	   //Initializes as null object to avoid npes
		private Sound soundEffect = new NoSound();
		private GameObject sprite;
		public KeyBehavior() {
			
		}
		public KeyBehavior(GameObject o) {
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
			return "Key Behaviour";
		}
}
