//@Author Christian Dummer
package behaviors;

import sound.Sound;

public interface TimedBehavior {
	public void tick();
	
	public void adjustInterval(double interval);
	
	public void addSound(Sound s);
	
	public void playSound();
}
