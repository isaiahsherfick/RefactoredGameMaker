//@Author Christian Dummer
package behaviors;

import javafx.geometry.Point2D;
import sound.NoSound;
import sound.Sound;
import sprite.Sprite;
import strategies.Strategy;

//TODO
//Not sure why clickbehaviors contain sprites,
//feels like it should be the other way around
//-Isaiah
public class ClickBehavior implements Strategy {
	//Initializes as null object to avoid npes
		private Sound soundEffect = new NoSound();
		private Sprite sprite;
		public ClickBehavior() {
			
		}
		public ClickBehavior(Sprite o) {
			sprite = o;
		}
		
		public Sprite getSprite() {
			return this.sprite;
		}
		
		public void setSprite(Sprite o) {
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
