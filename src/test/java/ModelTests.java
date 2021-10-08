import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import constants.Constants;
import sprite.NullSprite;
import sprite.Sprite;
import sprite.SpriteManager;

class ModelTests {

	@Test
	//Test to ensure that the sprite manager is correctly modifying the sprite IDs upon insertion
	//Also tests that it will not insert NullSprites
	public void SpriteManagerTest()
	{
		SpriteManager spriteManager = new SpriteManager();
		Sprite sprite1 = new Sprite();
		Sprite sprite2 = new Sprite();
		Sprite sprite3 = new Sprite();
		Sprite sprite4 = new Sprite();
		
		//make sure that spritemanager updates ids appropriately
		assertEquals(Constants.DEFAULT_SPRITE_ID, sprite1.getSpriteId());
		assertEquals(Constants.DEFAULT_SPRITE_ID, sprite2.getSpriteId());
		assertEquals(Constants.DEFAULT_SPRITE_ID, sprite3.getSpriteId());
		assertEquals(Constants.DEFAULT_SPRITE_ID, sprite4.getSpriteId());

		spriteManager.add(sprite1);
		assertEquals(1,spriteManager.getSize());
		spriteManager.add(sprite2);
		assertEquals(2,spriteManager.getSize());
		spriteManager.add(sprite3);
		assertEquals(3,spriteManager.getSize());
		spriteManager.add(sprite4);
		assertEquals(4,spriteManager.getSize());

		assertEquals(0, sprite1.getSpriteId());
		assertEquals(1, sprite2.getSpriteId());
		assertEquals(2, sprite3.getSpriteId());
		assertEquals(3, sprite4.getSpriteId());
		
		
		NullSprite nullSprite = new NullSprite();
		assertEquals(Constants.NULL_SPRITE_ID, nullSprite.getSpriteId());
		
		int before = spriteManager.getSize();
		spriteManager.add(nullSprite);
		int after = spriteManager.getSize();
		assertEquals(before,after);
		
	}

}
