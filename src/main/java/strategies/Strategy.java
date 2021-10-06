//@Author Christian Dummer
package strategies;

import sound.Sound;
import game.engine.GameObject;

//TODO
//This interface is nonsensical and has nothing to do with 
//the strategy pattern
//-Isaiah

//Each of these things needs to have its own "___Strategy" interface; you can't generalize Strategy
//To this degree without making it super bloated 
public interface Strategy {

	public void run();
	
	public void addSound(Sound s);
	
	public void playSound();

	public String getName();
}
