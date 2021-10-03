package movementBehaviors;

import behaviors.MoveBehavior;
import javafx.geometry.Point2D;
import sound.Sound;
import view.GameObject;

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
