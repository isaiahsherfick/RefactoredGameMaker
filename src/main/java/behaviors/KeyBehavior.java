//@Author Christian Dummer
package behaviors;


import sound.NoSound;
import sound.Sound;
import strategies.Strategy;
import view.GameObject;

public abstract class KeyBehavior implements Strategy {
	   //Initializes as null object to avoid npes
		private Sound soundEffect = new NoSound();
		private GameObject sprite;
		public KeyBehavior(GameObject o) {
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
