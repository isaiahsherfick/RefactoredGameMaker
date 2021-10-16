package sprite;


import org.json.simple.JSONObject;

import behaviors.collision.CollisionBehavior;
import behaviors.collision.CustomCollisionMap;
import behaviors.event.EventBehavior;
import behaviors.event.EventBehaviorChain;
import javafx.scene.canvas.GraphicsContext;
import saveandload.Saveable;
import constants.Constants;

public class Sprite implements Drawable, Saveable 
{
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
		appearance.draw(g);
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
	
	public void collide(int colliderId)
	{
		customCollisionMap.collide(this, colliderId);
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
				//System.out.println("Sprite Ids aren't equal");
			}
			boolean hitBoxEquals = s.getHitBox().equals(hitBox);
			if (!hitBoxEquals)
			{
				//System.out.println("Hit boxes aren't equal");
			}
			boolean appearanceEquals = s.getAppearance().equals(appearance);
			if (!appearanceEquals)
			{
				//System.out.println("appearances aren't equal");
			}
			boolean eventBehaviorChainEquals = s.getEventBehaviorChain().equals(eventBehaviorChain);
			if (!eventBehaviorChainEquals)
			{
				//System.out.println("event behavior chains aren't equal");
				//System.out.println(eventBehaviorChain.size());
				//System.out.println(s.getEventBehaviorChain().size());
			}
			boolean customCollisionMapEquals = s.getCustomCollisionMap().equals(customCollisionMap);
			if (!customCollisionMapEquals)
			{
				//System.out.println("custom collision maps aren't equal");
			}
			
			return spriteIdEquals && hitBoxEquals && appearanceEquals && eventBehaviorChainEquals && customCollisionMapEquals;
		}
		return false;
	}
	
	public CustomCollisionMap getCustomCollisionMap() 
	{
		return customCollisionMap;
	}

	public EventBehaviorChain getEventBehaviorChain() 
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

	public void flipYVelocity() 
	{
		eventBehaviorChain.flipYVelocity();
	}
	
	public void flipXVelocity()
	{
		eventBehaviorChain.flipXVelocity();
	}
	
	public int getXVelocity()
	{
		return eventBehaviorChain.getMaxXVelocity();
	}
	
	public int getYVelocity()
	{
		return eventBehaviorChain.getMaxYVelocity();
	}

	public void flipBothVelocities() 
	{
		flipXVelocity();
		flipYVelocity();
	}


	public void onGameTick() 
	{
		eventBehaviorChain.onGameTick(this);
	}
}