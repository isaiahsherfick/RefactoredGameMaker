package movementBehaviors;

import behaviors.MoveBehavior;
import javafx.geometry.Point2D;
import sound.Sound;
import game.engine.GameObject;

//TODO
//Admittedly good use of nullobject throughout the codebase
//Not much will need changed here
//Isaiah
public class NoMove extends MoveBehavior {
	private Object objectReference;
	public NoMove(GameObject o) {
		//Does nothing
		super(o);
	}
	
	public NoMove() {
		super();
	}

	
	@Override
	public String getName() {
		return "No Move Behavior";
	}
}
