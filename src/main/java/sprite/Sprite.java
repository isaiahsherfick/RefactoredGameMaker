package sprite;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import behaviors.*;
import collisionBehaviors.BounceOffScreen;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import saveandload.Saveable;
import strategies.Strategy;
import constants.Constants;
import game.engine.DrawObject;
import game.engine.Drawable;

//Handles only position, velocity, and movement information
public class Sprite extends DrawObject implements Drawable, Saveable {
	
	//TODO
	//New stuff is way down appended at the bottom of the file
	
	private String spriteName;

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
	protected Point2D nextPosition;
	protected Point2D size;

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
		return spriteName;
	}

	private void setObjectName(String objectName) {
		this.spriteName = objectName;
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

	public void handleObjectCollision(Sprite collider, String collisionDirection) {
		behaviors.forEach(strategy -> {
			if (strategy instanceof CollisionBehavior) {
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
	//DEPRECATED : delete after remove from Drawable interface
	public void draw(Sprite sprite, FlowPane gameFlow) {
		// TODO Auto-generated method stub
		
	}

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////	^
	//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ Old stuff ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^	|
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	

	//unique int identifier - will be handled by sprite manager
	protected int spriteId;

	//hitbox for collision handling, also contains x,y location
	protected HitBox hitBox;
	
	//appearance - either shape or image, sprite doesn't care which
	protected Appearance appearance;
	
	//Chain of event behaviors
	protected EventBehaviorChain eventBehaviorChain;
	
	//map of spriteId : collisions against the corresponding sprite
	//protected CollisionMap collisionMap;
	


	public Sprite() 
	{
		super();
		previousPosition = new Point2D(0, 0);
		velocity = new Point2D(0, 0);
		moveDirection = new Point2D(0, 0);
        nextPosition = new Point2D(0, 0);
        size = new Point2D(0, 0);
		behaviors = new ArrayList<Strategy>();
		/////////////////////////////////////
		///////////OLD STUFF/////////^^^/////
		/////////////////////////////////////
		
		
		spriteId = Constants.DEFAULT_SPRITE_ID;

		//initializes hitbox with default x,y,width,height found in Constants.java
		hitBox = new HitBox();
		appearance = new Appearance();
	}


	@Override
	public void draw(GraphicsContext g) 
	{
		// TODO Auto-generated method stub
		
	}

	public int getSpriteId() 
	{
		return spriteId;
	}


	public void setSpriteId(int newId) 
	{
		spriteId = newId;
	}
	


	private void setAppearance(Appearance appearance2) 
	{
		appearance = appearance2;
	}

	private void setHitBox(HitBox h) 
	{
		hitBox = h;
	}

	public void setX(int x) 
	{
		hitBox.setX(x);
		appearance.setX(x);
	}


	public void setY(int y) 
	{
		hitBox.setY(y);
		appearance.setY(y);
	}
	
	public void setWidth(int w)
	{
		hitBox.setWidth(w);
		appearance.setWidth(w);
	}
	public void setHeight(int h)
	{
		hitBox.setHeight(h);
		appearance.setHeight(h);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","Sprite");
		json.put("spriteId",spriteId);
		json.put("hitBox",hitBox.save());
		json.put("appearance",appearance.save());

		//TODO keep this growing as more stuff is added to Sprite class
		
		return json;
	}

	//TODO this needs updated as new fields are added to Sprite
	public Sprite copy()
	{
		Sprite copySprite = new Sprite();
		copySprite.setSpriteId(spriteId);
		copySprite.setHitBox(hitBox);
		copySprite.setAppearance(appearance);
		return copySprite;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		hitBox = new HitBox();
		hitBox.load((JSONObject)saveJSON.get("hitBox"));
		appearance = new Appearance();
		appearance.load((JSONObject)saveJSON.get("appearance"));
		spriteId = ((Long)saveJSON.get("spriteId")).intValue();
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Sprite)
		{
			Sprite s = (Sprite)o;
			return (s.getSpriteId() == spriteId) && s.getHitBox().equals(hitBox) && s.getAppearance().equals(appearance);
		}
		return false;
	}
	
	public String toString()
	{
		return String.format("Sprite#%d, hitbox: %s, appearance: %s",spriteId, hitBox.toString() ,appearance.toString());
	}


	public Appearance getAppearance() 
	{
		return appearance;
	}

	public HitBox getHitBox() 
	{
		return hitBox;
	}

	public void setImage(String path) 
	{
		appearance.setImage(path);
	}

}
