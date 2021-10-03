//@Author Christian Dummer
package strategies;

import sound.Sound;
import view.GameObject;

public interface Strategy {

	public void run();
	
	public void addSound(Sound s);
	
	public void playSound();

	public String getName();
}
