//@Author Christian Dummer
package behaviors;


import sound.NoSound;
import sound.Sound;
import strategies.Strategy;

import java.util.ArrayList;

import game.engine.Sprite;
import input.KeyPolling;
import javafx.scene.input.KeyCode;


//TODO
//Same thing I said for the other behaviors
//-Isaiah
public class KeyBehavior implements Strategy {
	   //Initializes as null object to avoid npes
		private Sound soundEffect = new NoSound();
		private Strategy behaviorToExecute;
		private Sprite sprite;
		private ArrayList<KeyCode> keys;
		public KeyBehavior() {
			
		}
		public KeyBehavior(Sprite o) {
			sprite = o;
		}
		
		public Sprite getSprite() {
			return this.sprite;
		}
		
		public void setSprite(Sprite o) {
			this.sprite = o;
		}
		
		public void run() {
			for(KeyCode key: keys) {
				if(KeyPolling.shared.isDown(key)) {
					runCommand();
				}
			}
		}
		
		public void runCommand() {
			behaviorToExecute.run();
			System.out.println("Correct key was pressed");
		}
		public void addKeyCode(KeyCode k) {
			keys.add(k);
		}
		public void addBehavior(Strategy s) {
			behaviorToExecute = s;
		}
		public void addSound(Sound s) {
			this.soundEffect = s;
		}
		
		public void playSound() {
			soundEffect.playSound();
		}
		
		public String getName() {
			return "Key Behavior";
		}
}
