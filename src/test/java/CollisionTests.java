import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import behaviors.collision.BounceCollisionBehavior;
import behaviors.event.MoveOnGameTickBehavior;
import controller.Controller;
import controller.GameClock;
import model.Model;
import sprite.Sprite;

class CollisionTests {

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
		s0.addCustomCollision(1, new BounceCollisionBehavior()); //make the sprite bounce off s1
		s0.addEventBehavior(new MoveOnGameTickBehavior(-1,0)); //make the sprite move left so it collides with s1
		c.modifySprite(s0);
		GameClock g = c.getClock(); 
		for (int i = 0; i < 100; i++)
		{
			g.tick();
		}
		
		s0 = c.getSprite(0);
		Sprite s1 = c.getSprite(1);
		
		assertTrue(s0.getX() > s1.getX()); //will only happen if s1 successfully bounced off of s0
	}
}
