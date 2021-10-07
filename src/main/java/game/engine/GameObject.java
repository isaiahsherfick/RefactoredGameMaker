package game.engine;

import java.util.ArrayList;


import behaviors.*;
import collisionBehaviors.BounceOffScreen;
import collisionUtility.ObjectCollider;
import collisionUtility.ScreenCollider;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import strategies.Strategy;

//Handles only position, velocity, and movement information
public class GameObject extends DrawObject implements ObjectCollider, ScreenCollider  {
	
	private String objectName;

	//TODO 
	//Unsure if we need previousposition and nextposition
	//if we just do movement commands right then we'll be able to restore those by simple undo/redo
	//-Isaiah
	//Many of these fields can be either cleared or reworked, class also needs to have behaviors that are 
	//not as generalized. -Christian
	protected Point2D previousPosition;
	protected Point2D velocity;
	protected Point2D moveDirection;
	protected ArrayList<Strategy> behaviors;
	private Point2D nextPosition;
	private Point2D size;


	public GameObject() {
		super();
		previousPosition = new Point2D(0, 0);
		velocity = new Point2D(0, 0);
		moveDirection = new Point2D(0, 0);
        nextPosition = new Point2D(0, 0);
        size = new Point2D(0, 0);
		behaviors = new ArrayList<Strategy>();
	}

	public GameObject(String name, Drawable drawBehaviour, Color color, Point2D position, Point2D dimensions) {
		super(drawBehaviour, color, position, dimensions);
		this.setObjectName(name);
		this.previousPosition = position;
		this.size = dimensions;
	    this.nextPosition = position;
		this.velocity = new Point2D(0, 0);
		moveDirection = new Point2D(0, 0);
		behaviors = new ArrayList<Strategy>();
	}
	
	public GameObject(String name, Drawable drawBehaviour, Color color, Point2D position, Point2D dimensions, Image image) {
		super(drawBehaviour, color, position, dimensions, image);
		this.setObjectName(name);
		this.previousPosition = position;
		this.size = dimensions;
	    this.nextPosition = position;
		this.velocity = new Point2D(0, 0);
		moveDirection = new Point2D(0, 0);
		behaviors = new ArrayList<Strategy>();
	}

	public Point2D getPreviousPosition() {
		return previousPosition;
	}

	public void setPreviousPosition(Point2D previousPosition) {
		this.previousPosition = previousPosition;
	}

	public Point2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Point2D velocity) {
		this.velocity = velocity;
	}

	public Point2D getMoveDirection() {
		return moveDirection;
	}

	public void setMoveDirection(Point2D moveDirection) {
		this.moveDirection = moveDirection;
	}

	public Point2D getCenter(Point2D position) {
		return new Point2D(position.getX() + dimensions.getX() / 2, position.getY() + dimensions.getY() / 2);
	}

	public Point2D getUpperLeft(Point2D position) {
		return new Point2D(getCenter(position).getX() - dimensions.getX() / 2,
				getCenter(position).getY() - dimensions.getY() / 2);
	}

	public Point2D getLowerLeft(Point2D position) {
		return new Point2D(getCenter(position).getX() - dimensions.getX() / 2,
				getCenter(position).getY() + dimensions.getY() / 2);
	}

	public Point2D getUpperRight(Point2D position) {
		return new Point2D(getCenter(position).getX() + dimensions.getX() / 2,
				getCenter(position).getY() - dimensions.getY() / 2);
	}

	public Point2D getLowerRight(Point2D position) {
		return new Point2D(getCenter(position).getX() + dimensions.getX() / 2,
				getCenter(position).getY() + dimensions.getY() / 2);
	}

	@Override
	public void update(double timeDelta) {
		//ON TICK
		run();
	}
	
	//Runs each strategy
	public void run() {
		for(Strategy s: behaviors) {
			if(!(s instanceof CollisionBehavior) && !(s instanceof TimedBehavior)) {
				s.run();
			}
		}
	}
	
	// Use the drawBehavior strategy object to draw
	// onto the graphics context
	public void performDraw(FlowPane gameFlow) {
		drawBehaviour.draw(this, gameFlow);
	}
	
	public void disable() {
		this.setPosition(new Point2D(0, -2000));
		this.behaviors.clear();
	}
	
	public void addBehavior(Strategy s) {
		behaviors.add(s);
	}
	
	
	public ArrayList<Strategy> getBehaviors() {
		return behaviors;
	}
	
	public String getObjectName() {
		return objectName;
	}

	private void setObjectName(String objectName) {
		this.objectName = objectName;
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

	@Override
	public void handleObjectCollision(GameObject collider, String collisionDirection) {
		behaviors.forEach(strategy -> {
			if (strategy instanceof CollisionBehavior) {
				System.out.println("Collision Behavior Running");
				strategy.run();
			}
		});
	}

	
	public void handleScreenCollision(Point2D oldPosition, Point2D newPosition) {
		if(!(oldPosition.equals(newPosition))) {
			for(Strategy s: behaviors) {
				if(s instanceof BounceOffScreen) {
					nextPosition = newPosition;
					s.run();
				}
			}
		}
		
	}

	@Override
	public void handleScreenCollision(Point2D newPosition) {
		// TODO Auto-generated method stub
		
	}
}
