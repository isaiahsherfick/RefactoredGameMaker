package collisionUtility;

import javafx.geometry.Point2D;


//TODO
//What do these two interfaces do? Can we just remove them?
//-Isaiah
//These interfaces are implemented by sprites for use in the collision detection, however the collision overall needs improvement - Christian
public interface ScreenCollider { 

	public void handleScreenCollision(Point2D newPosition);
}
