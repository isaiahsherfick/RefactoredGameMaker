package sprite;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import behaviors.EventBehavior;
import behaviors.EventBehaviorChain;
import behaviors.TimedBehavior;
import collisionBehaviors.BounceOffScreen;
import collisionBehaviors.CollisionBehavior;
import collisionBehaviors.CustomCollisionMap;
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
	protected CustomCollisionMap customCollisionMap;
	


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
		eventBehaviorChain = new EventBehaviorChain();
		customCollisionMap = new CustomCollisionMap();
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

	public void setX(double x) 
	{
		hitBox.setX(x);
		appearance.setX(x);
	}


	public void setY(double y) 
	{
		hitBox.setY(y);
		appearance.setY(y);
	}
	
	public void setWidth(double w)
	{
		hitBox.setWidth(w);
		appearance.setWidth(w);
	}
	public void setHeight(double h)
	{
		hitBox.setHeight(h);
		appearance.setHeight(h);
	}
	
	public void addEventBehavior(EventBehavior e)
	{
		eventBehaviorChain.add(e);
	}
	
	public void addCustomCollision(int colliderSpriteId, CollisionBehavior collisionBehavior)
	{
		customCollisionMap.put(colliderSpriteId, collisionBehavior);
	}
	
	//TODO how should we handle this discrepancy between appearance/hitbox? Should we just reconcile the two? Seems to violate single responsibilty
	public double getX()
	{
		return hitBox.getX();
	}
	
	public double getY()
	{
		return hitBox.getY();
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
		json.put("eventBehaviorChain",eventBehaviorChain.save());
		json.put("customCollisionMap",customCollisionMap.save());

		//TODO keep this growing as more stuff is added to Sprite class
		
		return json;
	}

	//TODO this needs updated as new fields are added to Sprite
	public Sprite copy()
	{
		Sprite copySprite = new Sprite();
		copySprite.setSpriteId(spriteId);
		copySprite.setHitBox(hitBox.copy());
		copySprite.setAppearance(appearance.copy());
		copySprite.setEventBehaviorChain(eventBehaviorChain.copy());
		copySprite.setCustomCollisionMap(customCollisionMap.copy());
		return copySprite;
	}

	private void setCustomCollisionMap(CustomCollisionMap ccm) 
	{
		customCollisionMap = ccm;
	}

	public void setEventBehaviorChain(EventBehaviorChain e) 
	{
		eventBehaviorChain = e;
	}
	
	public int getEventBehaviorChainSize()
	{
		return eventBehaviorChain.size();
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		hitBox = new HitBox();
		hitBox.load((JSONObject)saveJSON.get("hitBox"));
		appearance = new Appearance();
		appearance.load((JSONObject)saveJSON.get("appearance"));
		spriteId = ((Long)saveJSON.get("spriteId")).intValue();
		EventBehaviorChain ebc = new EventBehaviorChain();
		ebc.load((JSONObject)saveJSON.get("eventBehaviorChain"));
		eventBehaviorChain = ebc;
		CustomCollisionMap ccm = new CustomCollisionMap();
		ccm.load((JSONObject)saveJSON.get("customCollisionMap"));
		customCollisionMap = ccm;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Sprite)
		{
			Sprite s = (Sprite)o;
			boolean spriteIdEquals = s.getSpriteId() == spriteId;
			if (!spriteIdEquals)
			{
				System.out.println("Sprite Ids aren't equal");
			}
			boolean hitBoxEquals = s.getHitBox().equals(hitBox);
			if (!hitBoxEquals)
			{
				System.out.println("Hit boxes aren't equal");
			}
			boolean appearanceEquals = s.getAppearance().equals(appearance);
			if (!appearanceEquals)
			{
				System.out.println("appearances aren't equal");
			}
			boolean eventBehaviorChainEquals = s.getEventBehaviorChain().equals(eventBehaviorChain);
			if (!eventBehaviorChainEquals)
			{
				System.out.println("event behavior chains aren't equal");
				//System.out.println(eventBehaviorChain.size());
				//System.out.println(s.getEventBehaviorChain().size());
			}
			boolean customCollisionMapEquals = s.getCustomCollisionMap().equals(customCollisionMap);
			if (!customCollisionMapEquals)
			{
				System.out.println("custom collision maps aren't equal");
			}
			
			return spriteIdEquals && hitBoxEquals && appearanceEquals && eventBehaviorChainEquals && customCollisionMapEquals;
		}
		return false;
	}
	
	private CustomCollisionMap getCustomCollisionMap() 
	{
		return customCollisionMap;
	}

	private EventBehaviorChain getEventBehaviorChain() 
	{
		return eventBehaviorChain;
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

	public void destroy() 
	{
		//TODO
		//probably just set visible to false and play any explosions etc
	}
	
	public void setDefaultCollisionBehavior(CollisionBehavior c)
	{
		customCollisionMap.setDefaultCollisionBehavior(c);
	}
}