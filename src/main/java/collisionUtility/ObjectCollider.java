package collisionUtility;

import game.engine.GameObject;

//TODO
//What do these two interfaces do? Can we just remove them?
//-Isaiah
public interface ObjectCollider {

	public void handleObjectCollision(GameObject collider, String collisionDirection);
	
}
