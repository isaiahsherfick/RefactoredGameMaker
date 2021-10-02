//@Author Christian Dummer
package timedBehaviors;

import behaviors.TimedBehavior;
import view.GameObject;

public class NoTime extends TimedBehavior{

	public NoTime(GameObject o, double interval) {
		super(o, interval);
	}
	
	@Override
	public void run() {
		//nothing
	}
	

}
