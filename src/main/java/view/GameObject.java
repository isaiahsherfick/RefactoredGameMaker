package view;

import behaviours.ObjectCollider;
import behaviours.ScreenCollider;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

// Sprite Object
public abstract class GameObject implements ScreenCollider, ObjectCollider {
 
	private Point2D position;
	private Point2D nextPosition;
	private Point2D previousPosition;
	private Point2D size;

	public GameObject() {
		previousPosition = new Point2D(0, 0);
        position = new Point2D(0, 0);
        nextPosition = new Point2D(0, 0);
        size = new Point2D(0, 0);
	}
	
	public GameObject(int locationX, int locationY, int width, int height, Color color) {
		this.position = new Point2D(locationX, locationY);
		this.size = new Point2D(width, height);
		this.previousPosition = new Point2D(locationX, locationY);
	    this.nextPosition = new Point2D(locationX, locationY);
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(int locX, int locY) {
		this.position = new Point2D(locX, locY);
	}
	
	public Point2D getPreviousPosition() {
		return previousPosition;
	}
	
	public Point2D getNextPosition() {
		return nextPosition;
	}
	
	public void setSize(int width, int height) {
		this.size = new Point2D(width, height);
	}
	
	public Point2D getSize() {
		return size;
	}

	public Point2D getUpperLeft(Point2D position) {
		return new Point2D(getCenter(position).getX() - size.getX() / 2, getCenter(position).getY() - size.getY() / 2);
	}

	public Point2D getLowerLeft(Point2D position) {
		return new Point2D(getCenter(position).getX() - size.getX() / 2, getCenter(position).getY() + size.getY() / 2);
	}

	public Point2D getUpperRight(Point2D position) {
		return new Point2D(getCenter(position).getX() + size.getX() / 2, getCenter(position).getY() - size.getY() / 2);
	}

	public Point2D getLowerRight(Point2D position) {
		return new Point2D(getCenter(position).getX() + size.getX() / 2, getCenter(position).getY() + size.getY() / 2);
	}
	
	private Point2D getCenter(Point2D position) {
		return new Point2D(position.getX() + size.getX() / 2, position.getY() + size.getY() / 2);
	}
	
}
