import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import command.CreateSpriteCommand;
import command.ModifySpriteCommand;
import constants.Constants;
import controller.Controller;
import model.Model;
import sprite.Sprite;

class ControllerTests 
{ 
	@Test
	void createSpriteCommandTest() 
	{
		Model m = new Model();
		assertEquals(m.getNumberOfSprites(), 0);
		CreateSpriteCommand createCommand = new CreateSpriteCommand(m);
		m.receiveCommand(createCommand);
		assertEquals(m.getNumberOfSprites(), 1);
		m.undo();
		assertEquals(m.getNumberOfSprites(),0);
		m.undo();
	}
	
	@Test
	void ModifySpriteCommandTest()
	{
		Model m = new Model();
		CreateSpriteCommand createCommand = new CreateSpriteCommand(m);
		m.receiveCommand(createCommand);
		assertEquals(m.getNumberOfSprites(), 1);

		Sprite mySprite = m.getSprite(0);
		mySprite.setWidth(200);
		assertNotEquals(mySprite,m.getSprite(0));
		
		ModifySpriteCommand modifyCommand = new ModifySpriteCommand(m, mySprite);
		m.receiveCommand(modifyCommand);
		
		assertEquals(mySprite,m.getSprite(0));
		m.undo();
		assertNotEquals(mySprite,m.getSprite(0));
	}
	
	@Test
	//This is the big test that will ensure the model, spritemanager, saveandloadmanager, and all related systems work in tandem
	//Needs updated as more stuff we want to save gets implemented
	public void saveAndLoadTest()
	{
		//Make new model and a bunch of sprites
		Model m = new Model();
		Controller c = new Controller();
		c.setModel(m);
		
		ArrayList<Sprite> sprites = new ArrayList<>();
		
		for (int i = 0; i < 20; i++)
		{
			c.createSprite();
			sprites.add(m.getSprite(i));
		}

		for (int i = 0; i < 20; i++)
		{
			Sprite current = sprites.get(i);
			current.setX(i*500);
			if (i != 0)
				assertNotEquals(current,c.getSprite(i));
			c.modifySprite(current);
			assertEquals(current,c.getSprite(i));
		}
		
		c.save();
		m = new Model();
		c.setModel(m);
		assertEquals(Constants.LOAD_SUCCESS, c.load());

		for (int i = 0; i < 20; i++)
		{
			Sprite current = sprites.get(i);
			assertEquals(current,c.getSprite(i));
		}
	}
	
}
