//@Author Christian Dummer
package behaviors;

import javafx.geometry.Point2D;
import sound.NoSound;
import sound.Sound;
import strategies.Strategy;
import game.engine.GameObject;

//Once again, makes no sense for this to contain a sprite and not the other way around
//TODO
//-Isaiah
public class MoveBehavior implements Strategy {
	//Initializes as null object to avoid npes
	private Sound soundEffect = new NoSound();
	private GameObject sprite;
	private double speed = 1.0;
	private Direction currentDirection = Direction.RIGHT;
	public enum Direction {
		UP, DOWN, LEFT, RIGHT, TOPLEFT, TOPRIGHT, BOTTOMLEFT, BOTTOMRIGHT
	}
	public MoveBehavior() {
		
	}
	public MoveBehavior(GameObject o) {
		sprite = o;
	}
	
	public GameObject getSprite() {
		return this.sprite;
	}
	
	public void setSprite(GameObject o) {
		this.sprite = o;
	}
	
	public Direction getDirection() {
		return this.currentDirection;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public void run() {
		Point2D next = move();
		sprite.setPosition((int)next.getX(), (int)next.getY());
		playSound();
	}
	
	public Point2D move() {
		//By Default, no movement, this is overwritten by child behaviors;
		Point2D currentPosition = sprite.getPosition();
		return currentPosition;
	}
	
	public void setSpeed(double newSpeed) {
		speed = newSpeed;
	}
	
	//By default, flip to the opposite direction
	public void flipDirection() {
		switch(currentDirection) {
			case UP: currentDirection = Direction.DOWN; break;
			case DOWN: currentDirection = Direction.UP; break;
			case LEFT: currentDirection = Direction.RIGHT; break;
			case RIGHT: currentDirection = Direction.LEFT; break;
			case TOPLEFT: currentDirection = Direction.BOTTOMRIGHT; break;
			case TOPRIGHT: currentDirection = Direction.BOTTOMLEFT; break;
			case BOTTOMLEFT: currentDirection = Direction.TOPRIGHT; break;
			case BOTTOMRIGHT: currentDirection = Direction.TOPLEFT; break;
		}
	}
	
	public void setDirection(Direction newDirection) {
		this.currentDirection = newDirection;
	}
	
	public void addSound(Sound s) {
		this.soundEffect = s;
	}
	
	public void playSound() {
		
		soundEffect.playSound();
	}
	
	public String getName() {
		return "Move Behaviour";
	}
}
