//@Author Christian Dummer
package behaviors;

import sound.Sound;

public interface CollisionBehavior {
	public void handleCollision(Object o);
	
	public void addSound(Sound s);
	
	public void playSound();
}
