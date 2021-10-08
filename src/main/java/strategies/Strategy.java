//@Author Christian Dummer
package strategies;

import sound.Sound;
import sprite.Sprite;

//TODO
//This interface is nonsensical and has nothing to do with 
//the strategy pattern
//-Isaiah

//Comments from behaviors stand, this got too generalized to fit the codebase we had, that needs to NOT be the case, 
//as there should be different strategies depending on the event.

//Each of these things needs to have its own "___Strategy" interface; you can't generalize Strategy
//To this degree without making it super bloated 
public interface Strategy {

	public void run();
	
	public void addSound(Sound s);
	
	public void playSound();

	public String getName();
}
