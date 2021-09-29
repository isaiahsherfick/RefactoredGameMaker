//@Author Christian Dummer
package behaviors;

import javafx.geometry.Point2D;
import sound.Sound;

public interface MoveBehavior {
	public Point2D move();
	
	public void addSound(Sound s);
	
	public void playSound();
}
