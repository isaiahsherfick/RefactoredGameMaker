//@Author Christian Dummer
package behaviors;

import javafx.geometry.Point2D;
import sound.NoSound;
import sound.Sound;
import strategies.Strategy;
import game.engine.GameObject;

public class ClickBehavior implements Strategy {
	//Initializes as null object to avoid npes
		private Sound soundEffect = new NoSound();
		private GameObject sprite;
		public ClickBehavior() {
			
		}
		public ClickBehavior(GameObject o) {
			sprite = o;
		}
		
		public GameObject getSprite() {
			return this.sprite;
		}
		
		public void setSprite(GameObject o) {
			this.sprite = o;
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
			return "Click Behaviour";
		}

}
