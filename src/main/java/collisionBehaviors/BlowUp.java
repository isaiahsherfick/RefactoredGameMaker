package collisionBehaviors;

import behaviors.CollisionBehavior;
import javafx.geometry.Point2D;
import javafx.scene.media.Media;
import sound.Sound;
import view.GameObject;

public class BlowUp extends CollisionBehavior{

	public BlowUp(GameObject o) {
		super(o);
	}
	
	@Override
	public void run() {
		super.playSound();
		//Blow up the GameObject
		
	}




}
