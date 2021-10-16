import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import behaviors.collision.BounceCollisionBehaviorX;
import behaviors.event.MoveOnGameTickBehavior;
import constants.Constants;
import controller.Controller;
import controller.GameClock;
import model.Model;
import sprite.Sprite;

class CollisionTests {
	
	@Test
	void flipVelocityTest()
	{
		Sprite s = new Sprite();
		s.addEventBehavior(new MoveOnGameTickBehavior());
		assertEquals(Constants.DEFAULT_SPRITE_VELOCITY_X, s.getXVelocity());
		assertEquals(Constants.DEFAULT_SPRITE_VELOCITY_Y, s.getYVelocity());
		s.flipXVelocity();
		assertEquals(Constants.DEFAULT_SPRITE_VELOCITY_X * -1, s.getXVelocity());
		s.flipYVelocity();
		assertEquals(Constants.DEFAULT_SPRITE_VELOCITY_Y * -1, s.getYVelocity());
		s.flipBothVelocities();
		assertEquals(Constants.DEFAULT_SPRITE_VELOCITY_X, s.getXVelocity());
		assertEquals(Constants.DEFAULT_SPRITE_VELOCITY_Y, s.getYVelocity());
	}
	
	@Test
	void gameTickBehaviorTest()
	{
		Model m = new Model();
		Controller c = new Controller();
		c.setModel(m);
		GameClock g = c.getClock();
		c.createSprite();
		Sprite s = c.getSprite(0);
		s.addEventBehavior(new MoveOnGameTickBehavior(1,1));
		c.modifySprite(s);
		assertEquals(s.getX(),Constants.DEFAULT_SPRITE_X);
		assertEquals(s.getY(),Constants.DEFAULT_SPRITE_Y);
		assertEquals(g.getTicks(), 0);
		g.tick();
		assertEquals(g.getTicks(), 1);
		s = c.getSprite(0);
		assertEquals(s.getX(),Constants.DEFAULT_SPRITE_X + 1);
		assertEquals(s.getY(),Constants.DEFAULT_SPRITE_Y + 1);
	}
	
	

	@Test
	void collisionTest() 
	{
		Model m = new Model();
		Controller c = new Controller();
		c.setModel(m);
		
		c.createSprite();
		c.createSprite();
		
		Sprite s0 = c.getSprite(0);
		s0.setX(20);
		s0.addCustomCollision(1, new BounceCollisionBehaviorX()); //make the sprite bounce off s1
		s0.addEventBehavior(new MoveOnGameTickBehavior(-5,0)); //make the sprite move left so it collides with s1
		c.modifySprite(s0);
		GameClock g = c.getClock(); 
		for (int i = 0; i < 100; i++)
		{
			g.tick();
			System.out.println("Clock ticked " + i);
		}
		
		s0 = c.getSprite(0);
		Sprite s1 = c.getSprite(1);
		
		System.out.println("s0 x" + s0.getX());
		System.out.println("s1 x" + s1.getX());
		assertTrue(s0.getX() > s1.getX()); //will only happen if s1 successfully bounced off of s0
	}
}
