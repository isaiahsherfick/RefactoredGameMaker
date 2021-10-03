//@Author Christian Dummer
package behaviors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import sound.NoSound;
import sound.Sound;
import strategies.Strategy;
import view.GameObject;

public class TimedBehavior implements Strategy {
	//Initializes nullObject to avoid NPEs
	private Sound soundEffect = new NoSound();
	private double tickInterval = 0;
	//Nested Strategy within the timedBehavior
	private Strategy runOnInterval;
	private GameObject sprite;
	
	public TimedBehavior() {
		
	}
	
	public TimedBehavior(double interval) {
		this.tickInterval = interval;
	}
	public TimedBehavior(GameObject o, double interval, Strategy toRun) {
		this.sprite = o;
		this.tickInterval = interval;
		this.runOnInterval = toRun;
	}
	
	public GameObject getSprite() {
		return this.sprite;
	}
	
	public void run() {
		Timeline actionInterval = new Timeline(
                new KeyFrame(Duration.seconds(tickInterval), 
                new EventHandler<ActionEvent>() {

	   @Override
	   public void handle(ActionEvent event) {
	       runOnInterval.run();
	   }
       }));
		actionInterval.play();
	}
	
	public void adjustInterval(double interval) {
		this.tickInterval = interval;
	}
	
	public void addSound(Sound s) {
		this.soundEffect = s;
	}
	
	public void addStrategy(Strategy toRun) {
		this.runOnInterval = toRun;
	}
	
	public void playSound() {
		soundEffect.playSound();
	}
	
	public String getName() {
		return "Time Behaviour";
	}
}
