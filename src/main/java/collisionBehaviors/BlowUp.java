package collisionBehaviors;

import behaviors.CollisionBehavior;
import javafx.geometry.Point2D;
import javafx.scene.media.Media;
import sound.Sound;
import game.engine.GameObject;

public class BlowUp extends CollisionBehavior{

	public BlowUp(GameObject o) {
		super(o);
	}
	
	@Override
	public void run() {
		super.playSound();
		//Blow up the GameObject
		
	}
	
	@Override
	public String getName() {
		return "Blow Up";
	}




}
