import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import constants.Constants;
import javafx.geometry.Point2D;
import model.Model;
import saveandload.SaveAndLoadManager;
import sprite.HitBox;
import sprite.NullSprite;
import sprite.Sprite;
import sprite.SpriteManager;

class ModelTests {

	@Test
	//Test to ensure that the sprite manager is correctly modifying the sprite IDs upon insertion
	//Also tests that it will not insert NullSprites
	public void SpriteManagerTest()
	{
		int before, after;
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

		//add sprites to the manager, ensure that the size variable is updating appropriately
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
		
		//remove a sprite that does exist from the manager
		before = spriteManager.getSize();
		spriteManager.remove(2);
		after = spriteManager.getSize();
		assertEquals(3,after);
		
		NullSprite nullSprite = new NullSprite();
		assertEquals(Constants.NULL_SPRITE_ID, nullSprite.getSpriteId());
		
		before = spriteManager.getSize();
		spriteManager.add(nullSprite);
		after = spriteManager.getSize();
		assertEquals(before,after);
		
		//remove a sprite that doesn't exist from the manager
		before = spriteManager.getSize();
		spriteManager.remove(-5);
		after = spriteManager.getSize();
		assertEquals(before,after);
		
		//Test getting the sprites as an arraylist
		ArrayList<Sprite> spriteList = spriteManager.getSpriteList();
		
		//Confirm that there are the same number of sprites in the list
		assertEquals(after,spriteList.size());
	}
	
	@Test
	public void HitBoxSaveAndLoadTest()
	{
		HitBox hitBox = new HitBox();
		double x,y,width,height;
		x = 1;
		y = 2;
		width = 3;
		height = 4;
		
		hitBox.setX(x);
		hitBox.setY(y);
		hitBox.setWidth(width);
		hitBox.setHeight(height);
		
		JSONObject result = hitBox.save();
		
		HitBox loaded = new HitBox();
		loaded.load(result);
		
		assertEquals(hitBox,loaded);
	}
	
	
	@Test
	public void saveAndLoadTest()
	{
		//Make new model and a bunch of sprites
		Model m = new Model();
		Sprite sprite1 = new Sprite();
		Sprite sprite2 = new Sprite();
		Sprite sprite3 = new Sprite();
		Sprite sprite4 = new Sprite();
		
		//change the sprites
		sprite1.setX(5);
		Point2D sprite1Location = sprite1.getLocation();
		sprite2.setY(7);
		Point2D sprite2Location = sprite2.getLocation();
		sprite3.setWidth(-5);
		Point2D sprite3Size = sprite3.getSize();
		sprite4.setHeight(20);
		Point2D sprite4Size = sprite4.getSize();

		//add them to the model
		m.addSprite(sprite1);
		m.addSprite(sprite2);
		m.addSprite(sprite3);
		m.addSprite(sprite4);
		
		//save them
		
		//reset the saveandloadmanager and the spritemanager
		
		//load
		
		//assert that everything is the same 
		
		
	}
}
