//@Author Christian Dummer
package strategies;

import sound.Sound;
import game.engine.GameObject;

//TODO
//This interface is nonsensical and has nothing to do with 
//the strategy pattern
//-Isaiah
public interface Strategy {

	public void run();
	
	public void addSound(Sound s);
	
	public void playSound();

	public String getName();
}
