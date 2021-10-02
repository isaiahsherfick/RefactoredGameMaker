//@Author Christian Dummer
package behaviors;

import javafx.geometry.Point2D;
import sound.NoSound;
import sound.Sound;
import strategies.Strategy;
import view.GameObject;

public abstract class ClickBehavior implements Strategy {
	//Initializes as null object to avoid npes
		private Sound soundEffect = new NoSound();
		private GameObject sprite;
		public ClickBehavior(GameObject o) {
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
