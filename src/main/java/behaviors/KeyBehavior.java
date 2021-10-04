//@Author Christian Dummer
package behaviors;


import sound.NoSound;
import sound.Sound;
import strategies.Strategy;

import java.util.ArrayList;

import game.engine.GameObject;
import input.KeyPolling;
import javafx.scene.input.KeyCode;

public class KeyBehavior implements Strategy {
	   //Initializes as null object to avoid npes
		private Sound soundEffect = new NoSound();
		private Strategy behaviorToExecute;
		private GameObject sprite;
		private ArrayList<KeyCode> keys;
		public KeyBehavior() {
			
		}
		public KeyBehavior(GameObject o) {
			sprite = o;
		}
		
		public GameObject getSprite() {
			return this.sprite;
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
			return "Key Behaviour";
		}
}
