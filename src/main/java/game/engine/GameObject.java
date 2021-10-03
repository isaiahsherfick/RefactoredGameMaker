package game.engine;

import java.util.ArrayList;

import collisionUtility.ObjectCollider;
import collisionUtility.ScreenCollider;
import strategies.Strategy;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

//Handles only position, velocity, and movement information
public class GameObject extends DrawObject implements ObjectCollider, ScreenCollider  {
	
	private static double orgSceneX, orgSceneY;
	private static double orgTranslateX, orgTranslateY;
	
	private String objectName;

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
		this.setOnMousePressed(buttonMousePressedEventHandler);
        this.setOnMouseDragged(buttonOnMouseDraggedEventHandler);
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
		// TODO Auto-generated method stub
		
	}
	
	//Runs each strategy
	public void run() {
		for(Strategy s: behaviors) {
			s.run();
		}
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
	
	static EventHandler<MouseEvent> buttonMousePressedEventHandler = new EventHandler<MouseEvent>() {

	    @Override
	    public void handle(MouseEvent t) {
	        orgSceneX = t.getSceneX();
	        orgSceneY = t.getSceneY();
	        orgTranslateX = ((Button) (t.getSource())).getTranslateX();
	        orgTranslateY = ((Button) (t.getSource())).getTranslateY();

	        ((Button) (t.getSource())).toFront();
	    }
	};

	static EventHandler<MouseEvent> buttonOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

	    @Override
	    public void handle(MouseEvent t) {
	        double offsetX = t.getSceneX() - orgSceneX;
	        double offsetY = t.getSceneY() - orgSceneY;
	        double newTranslateX = orgTranslateX + offsetX;
	        double newTranslateY = orgTranslateY + offsetY;

	        ((Button) (t.getSource())).setTranslateX(newTranslateX);
	        ((Button) (t.getSource())).setTranslateY(newTranslateY);
	    }
	};

	@Override
	public void handleObjectCollision(GameObject collider, String collisionDirection) {
		behaviors.forEach(strategy -> {
			if (strategy.getName() == "Collision Behaviour") {
				strategy.run();
			}
		});
	}
}
