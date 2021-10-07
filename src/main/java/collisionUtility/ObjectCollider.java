package collisionUtility;

import game.engine.GameObject;

//TODO
//What do these two interfaces do? Can we just remove them?
//-Isaiah
//These interfaces are implemented by sprites for use in the collision detection, however the collision overall needs improvement - Christian
public interface ObjectCollider {

	public void handleObjectCollision(GameObject collider, String collisionDirection);
	
}
